package com.mn.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mn.model.Member;
import com.mn.util.KafkaConstants;

@Configuration
public class KafkaProducerConfig {
	/**
	 * This method is used to Kafka Producer Config details
	 * @return
	 */

	@Bean
	public ProducerFactory<String, Member> producerFactory() {
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory(configProps);
	}

	/**
	 * This method is used to create KafkaTemplate bean obj
	 * @return
	 */
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, Member> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
