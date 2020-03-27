package io.karthik.cepengine.processors;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface KafkaStreamsIOInterface {

  String INPUT = "kstream-input-topic";
  String OUTPUT = "kstream-output-topic";

  @Input(INPUT)
  KStream<?, ?> input();

//  @Output(OUTPUT)
//  KStream<?, ?> output();
}
