package io.karthik.cepengine.processors.event;

import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(name = "cep-properties.processors.shipment-event-transformer.enabled", havingValue="true")
public class ShipmentEventConfig {

  private static final Logger log = LoggerFactory.getLogger(ShipmentEventConfig.class);

  @Value("${cep-properties.processors.shipment-event-transformer.output-topic-schema-location}")
  private String fileLoc;

  @Bean
  @Qualifier("ShipmentEventOutputSchema")
  public Schema getSchema() throws IOException {
    log.info("Loading output schema from: {}", fileLoc);
    return new Schema.Parser().parse(new File(fileLoc));
  }
}
