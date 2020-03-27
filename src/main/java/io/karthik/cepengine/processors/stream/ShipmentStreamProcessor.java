package io.karthik.cepengine.processors.stream;

import io.karthik.cepengine.processors.KafkaStreamsIOInterface;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(KafkaStreamsIOInterface.class)
@ConditionalOnProperty(name = "cep-properties.processors.kstream-shipment-streamer.enabled", havingValue = "true")
public class ShipmentStreamProcessor {

  private final Logger log = LoggerFactory.getLogger(ShipmentStreamProcessor.class);

  @StreamListener(KafkaStreamsIOInterface.INPUT)
  public void processStream(KStream<GenericRecord, GenericRecord> inputStream) {
    inputStream.foreach((k,v) -> log.info("Key: {}, value: {}", k, v));
  }
}
