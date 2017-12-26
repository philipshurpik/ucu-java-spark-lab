package football.validators;

import football.config.Conf;
import football.models.FootballGameItem;
import lombok.SneakyThrows;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static football.config.Const.DEV;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Conf.class)
@ActiveProfiles(DEV)
public class FootballEventTimeValidatorTest {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private FootballEventTimeValidator footballEventTimeValidator;
    private SimpleDateFormat formatEvent = new SimpleDateFormat("mm:ss");

    @SneakyThrows
    private FootballGameItem getItem(String value) {
        return FootballGameItem.builder().eventTime(new Timestamp(formatEvent.parse(value).getTime())).build();
    }

    @Test
    public void testCorrectEventTime() throws Exception {
        List<FootballGameItem> list = Arrays.asList(
                getItem("1:15"), getItem("44:59"), getItem("44:99"), getItem("89:59")
        );
        JavaRDD<FootballGameItem> itemsRDD = sc.parallelize(list);
        Assert.assertArrayEquals(
                itemsRDD.map(footballEventTimeValidator::validate).collect().toArray(),
                new Boolean[] {true, true, true, true});
    }

    @Test
    public void testWrongEventTime() throws Exception {
        List<FootballGameItem> list = Arrays.asList(
                getItem("-1:01"), getItem("89:99"), getItem("92:00"), getItem("101:00")
        );

        JavaRDD<FootballGameItem> itemsRDD = sc.parallelize(list);
        Assert.assertArrayEquals(
                itemsRDD.map(footballEventTimeValidator::validate).collect().toArray(),
                new Boolean[] {false, false, false, false});
    }
}
