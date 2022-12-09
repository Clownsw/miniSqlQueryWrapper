package cn.smilex;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author smilex
 */
public class LambdaQueryWrapper<T> extends AbstractWrapper {

    public LambdaQueryWrapper() {
        super(null, new LinkedList<>());
    }

    public LambdaQueryWrapper(String baseSql) {
        super(baseSql, new LinkedList<>());
    }

    public LambdaQueryWrapper<T> eq(SFunction<T, Object> function, Object value) {
        String fieldName;
        if (!WrapperConfig.EMPTY_STRING.equals(fieldName = getFieldNameInFunction(function))) {
            super.eq(fieldName, value);
        }

        return this;
    }

    public LambdaQueryWrapper<T> orderByAsc(SFunction<T, Object> function) {
        String fieldName;
        if (!WrapperConfig.EMPTY_STRING.equals(fieldName = getFieldNameInFunction(function))) {
            super.orderByAsc(fieldName);
        }

        return this;
    }

    public LambdaQueryWrapper<T> orderByDesc(SFunction<T, Object> function) {
        String fieldName;
        if (!WrapperConfig.EMPTY_STRING.equals(fieldName = getFieldNameInFunction(function))) {
            super.orderByDesc(fieldName);
        }

        return this;
    }

    public LambdaQueryWrapper<T> filter(Supplier<Boolean> filter, Consumer<LambdaQueryWrapper<T>> handler) {
        if (filter.get()) {
            handler.accept(this);
        }

        return this;
    }

    @SneakyThrows
    private String getFieldNameInFunction(Function<T, Object> function) {
        SerializedLambda serializedLambda = WrapperUtil.lambdaToSerializedLambda(function);
        String methodName = serializedLambda.getImplMethodName();

        if (methodName.startsWith(WrapperConfig.GET)) {
            String fieldName = methodName.substring(methodName.indexOf(WrapperConfig.GET) + WrapperConfig.GET.length());
            Field field = ReflectionUtil.searchField(Class.forName(serializedLambda.getImplClass().replace('/', '.')), WrapperUtil.toLowerCaseFirstOne(fieldName));
            WrapperField wrapperField;
            if (field != null && (wrapperField = field.getDeclaredAnnotation(WrapperField.class)) != null) {
                return wrapperField.value();
            }
            return fieldName.toLowerCase();
        }

        return WrapperConfig.EMPTY_STRING;
    }
}
