package com.suncj.demo.producer;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import com.suncj.demo.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	private static Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Value("${topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping(value = "write/data",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Object sendKafka(@RequestBody Message message) {
		try {
			logger.info("send kafka message: {}", message.getMessage());
			kafkaTemplate.send(topicName, UUID.randomUUID().toString(), message.getMessage());
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			logger.error("kafka", e);
			return Collections.singletonMap("result", "Error");
		}
	}
}
