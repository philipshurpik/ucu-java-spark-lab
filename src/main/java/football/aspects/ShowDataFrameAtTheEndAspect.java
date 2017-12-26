package football.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


import static football.config.Const.DEV;

@Component
@Aspect
@Profile(DEV)
@EnableAspectJAutoProxy
public class ShowDataFrameAtTheEndAspect {

    @AfterReturning(value = "@annotation(ShowDataFrameAtTheEnd)", returning = "dataFrame")
    public void ShowDataFrameAtTheEnd(JoinPoint jp, Dataset<Row> dataFrame) {
        System.out.println("Debug... ShowDataFrameAtTheEnd starting");
        dataFrame.show();
        System.out.println("Debug... ShowDataFrameAtTheEnd finishing");
    }
}
