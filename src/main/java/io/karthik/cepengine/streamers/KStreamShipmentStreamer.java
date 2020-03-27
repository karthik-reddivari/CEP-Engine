package io.karthik.cepengine.streamers;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "cep-properties.processors.KStreamShipmentStreamer.enabled", havingValue = "true")
@EnableBinding(KafkaStreamsIOInterface.class)
@Component
public class KStreamShipmentStreamer {

  private final Logger log = LoggerFactory.getLogger(KStreamShipmentStreamer.class);

  @StreamListener(KafkaStreamsIOInterface.INPUT)
  public void processStream(KStream<GenericRecord, GenericRecord> inputStream) {
    inputStream.foreach((k,v) -> log.info("Key: {}, value: {}", k, v));
  }
}
