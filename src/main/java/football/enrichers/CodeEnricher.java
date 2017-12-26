package football.enrichers;

import football.config.CodesConfig;
import football.utils.AutowiredBroadcast;
import football.utils.RegisterUDF1;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

@RegisterUDF1
public class CodeEnricher implements UDF1<Object, String> {

    @AutowiredBroadcast
    private Broadcast<CodesConfig> codesConfig;

    @Override
    public String call(Object code) throws Exception {
        return this.codesConfig.value().codes.getOrDefault((code).toString(), "");
    }
}