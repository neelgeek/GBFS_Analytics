package org.gbfsanalytics.kafka.producer;

import com.google.gson.JsonArray;

import java.util.function.Consumer;

public interface GBFS_Kafka_producer {

    void publishData(JsonArray data);
}
