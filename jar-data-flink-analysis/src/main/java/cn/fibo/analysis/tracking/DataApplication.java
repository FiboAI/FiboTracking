package cn.fibo.analysis.tracking;

import cn.fibo.analysis.tracking.constants.ConfigServerAddressConstants;
import cn.fibo.analysis.tracking.db.ClickhouseImpl;
import cn.fibo.analysis.tracking.model.BaseIdentitiesEntity;
import cn.fibo.analysis.tracking.model.BaseLibEntity;
import cn.fibo.analysis.tracking.model.BasePropertiesEntity;
import cn.fibo.analysis.tracking.model.DataEntity;
import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import javax.swing.*;
import java.util.Properties;

/**
 * @author lisw
 * @program jar-data-analysis
 * @description
 * @createDate 2022-05-10 10:25:28
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class DataApplication {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //checkpoint配置
        env.enableCheckpointing(5000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
        env.getCheckpointConfig().setCheckpointTimeout(60000);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // 设置失败重启作业3次 触发机制 失败3秒 作业延迟 2秒
        env.setRestartStrategy(RestartStrategies.failureRateRestart(3, Time.seconds(2), Time.seconds(2)));

        //设置并行度
        env.setParallelism(1);

        ParameterTool params = ParameterTool.fromArgs(args);
        //设置EventTime
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //连接kafka
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", ConfigServerAddressConstants.KAFKA_SERVER_URL);//kafka的地址
        if(ConfigServerAddressConstants.IS_PROD){
            properties.setProperty("group.id", ConfigServerAddressConstants.KAFKA_PRO_GROUPID);
        }else{
            properties.setProperty("group.id", ConfigServerAddressConstants.KAFKA_TEST_GROUPID);
        }
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //获取kafka生产者消息
        DataStreamSource stream = env.addSource(new FlinkKafkaConsumer011<>("tracking", new SimpleStringSchema(), properties));
        stream.print();
        stream.map(line -> {
            DataEntity dataEntity = new DataEntity();
            try {
                dataEntity = JSON.parseObject(line.toString(), DataEntity.class);
            } catch (Exception ex) {
                System.out.println("JSON格式化失败");
                ex.printStackTrace();
            }
            if(dataEntity.getProperties() == null){
                dataEntity.setProperties(new BasePropertiesEntity());
            }
            if(dataEntity.getIdentities() == null){
                dataEntity.setIdentities(new BaseIdentitiesEntity());
            }
            if(dataEntity.getLib() == null){
                dataEntity.setLib(new BaseLibEntity());
            }
            return dataEntity;
        }).addSink(new ClickhouseImpl());
        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
