package io.karthik.cepengine.streamers;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface KafkaStreamsIOInterface {

  String INPUT = "shipment";

  @Input(INPUT)
  KStream<?, ?> input();
}
