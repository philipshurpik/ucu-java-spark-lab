package football.utils;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UdfRegistratorApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udf1Objects = context.getBeansWithAnnotation(RegisterUDF1.class).values();
        Collection<Object> udf2Objects = context.getBeansWithAnnotation(RegisterUDF2.class).values();
        for (Object udfObject : udf1Objects) {
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF1<?, ?>) udfObject, DataTypes.StringType);
        }
        for (Object udfObject : udf2Objects) {
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF2<?, ?, ?>) udfObject, DataTypes.StringType);
        }
    }
}
