package io.karthik.cepengine.processors.event;

import io.karthik.cepengine.processors.KafkaEventIOInterface;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(KafkaEventIOInterface.class)
@ConditionalOnProperty(name = "cep-properties.processors.shipment-event-transformer.enabled", havingValue="true")
public class ShipmentEventTransformer {

  private final Logger log = LoggerFactory.getLogger(ShipmentEventTransformer.class);

  private Schema schema;
  // Injecting necessary dependencies
  public ShipmentEventTransformer (@Qualifier("ShipmentEventOutputSchema") Schema schema) {
    this.schema = schema;
  }

  @Transformer(inputChannel = KafkaEventIOInterface.INPUT, outputChannel = KafkaEventIOInterface.OUTPUT)
  public Message<GenericRecord> transformStream(Message<GenericRecord> event) {
    // TODO: Recode this section
    log.info("Event received with Id: {}", event.getPayload().get("id"));
    GenericRecord payload = event.getPayload();
    // Creating a new message payload
    GenericRecord inferedEvent = new GenericData.Record(this.schema);
    inferedEvent.put("id", payload.get("id"));
    inferedEvent.put("price", payload.get("transaction_amount"));
    inferedEvent.put("quantity", payload.get("transaction_quantity"));
    inferedEvent.put("country", payload.get("country"));
    inferedEvent.put("product", payload.get("product"));
    // Detecting Anomaly
    inferedEvent.put("anomaly", (Float) payload.get("transaction_amount") > 75000);
    // Constructing the output message
    final Message<GenericRecord> outputMessage = MessageBuilder.withPayload(inferedEvent)
      .copyHeaders(event.getHeaders())
      .build();
    return outputMessage;
  }
}
