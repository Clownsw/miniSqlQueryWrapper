# miniSqlQueryWrapper
一个非常简单的sql构建器, 灵感来自MybatisPlus


# 例子
```java
Wrapper queryWrapper = new QueryWrapper()
		.eq("user_name", "xuda")
		.eq("pass_word", 123123)
		.orderByDesc("create_time")
		.limit(5L, 10L);

System.out.println(queryWrapper.buildSql());
```

```sql
WHERE user_name = xuda AND pass_word = 123123 ORDER BY DESC create_time LIMIT 5, 10
```
