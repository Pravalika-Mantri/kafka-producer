package com.kafka.producer.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class KafkaConfig {
	
	@Bean
	public NewTopic producer() {
		return new NewTopic("Topic7", 5, (short) 1);
	}
	
	@Bean
	public NewTopic cusotmer() {
		return new NewTopic("Customer-Topic11", 4, (short) 1);
	}
	
	
	@Bean
	public Map<String,Object> producerConfig(){
		Map<String,Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}
	
	@Bean
	public DefaultKafkaProducerFactory<String, Object> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}

}
