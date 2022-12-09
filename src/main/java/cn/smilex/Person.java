package cn.smilex;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author YangLuJia
 * @date 2022/12/9/14:18
 * @since 1.0
 */
@Getter
@Setter
public class Person {
    private String name;
    private String age;

    @WrapperField("create_time")
    private Date createTime;
}
