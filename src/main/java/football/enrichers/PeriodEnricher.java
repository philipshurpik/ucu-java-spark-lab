package football.enrichers;

import football.utils.RegisterUDF1;
import org.apache.spark.sql.api.java.UDF1;

import java.util.Date;

@RegisterUDF1
public class PeriodEnricher implements UDF1<Object, String> {

    private static final int HALF_TIME = 45;

    @Override
    public String call(Object eventTime) throws Exception {
        Date event = (Date)eventTime;
        return (event.getHours() * 60 + event.getMinutes() < HALF_TIME) ? "First" : "Second";
    }
}