package io.karthik.cepengine.config;

import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class CepConfig {

  private static final Logger log = LoggerFactory.getLogger(CepConfig.class);

  @Value("${cep-properties.schema-location}")
  private String fileLoc;

  @Bean
  public Schema getSchema() throws IOException {
    log.info("Loading output schema from: {}", fileLoc);
    return new Schema.Parser().parse(new File(fileLoc));
  }
}
