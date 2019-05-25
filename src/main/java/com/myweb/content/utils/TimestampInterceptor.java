package com.myweb.content.utils;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.myweb.content.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class TimestampInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {

        Object parameterObj = invocation.getArgs()[1];
        SqlCommandType type = ((MappedStatement) invocation.getArgs()[0]).getSqlCommandType();
        if (parameterObj instanceof BaseEntity) {
            populateDate(parameterObj, type);
        }

        if (parameterObj instanceof Map) {
            Map mapObj = (Map) parameterObj;
            if (mapObj.containsKey("collection")) {
                if (mapObj.get("collection") instanceof List) {
                    List<Object> objectList = (List) mapObj.get("collection");
                    for (Object obj : objectList) {
                        populateDate(obj, type);
                    }
                }
            }

            if (mapObj.containsKey("list")) {
                if (mapObj.get("list") instanceof List) {
                    List<Object> objectList = (List) mapObj.get("list");
                    for (Object obj : objectList) {
                        populateDate(obj, type);
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void populateDate(Object obj, SqlCommandType commandType) {
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            if (SqlCommandType.INSERT == commandType) {
                entity.populateCreation();
            } else if (SqlCommandType.UPDATE == commandType) {
                entity.populateUpdate();
            }
        }
    }

}

