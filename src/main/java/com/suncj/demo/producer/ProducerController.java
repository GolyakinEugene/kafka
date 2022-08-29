package com.suncj.demo.producer;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import com.suncj.demo.models.Message;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProducerController {
	private static Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Value("${topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@ApiOperation(value = "Отправить данные в топик kafka", notes = "Отправить данные в топик Kafka")
	@ApiImplicitParam(name = "message", value = "Сообщение в топик", required = true, dataType = "Message")
	@PostMapping (value = "write/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object sendKafka(@RequestBody Message message, HttpServletRequest request) {
		String ipAddress =  request.getRemoteAddr();
		try {
			logger.info("Send kafka message: {} , client {} ", message.getMessage(), ipAddress);
			kafkaTemplate.send(topicName, UUID.randomUUID().toString(), message.getMessage());
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			logger.error("Send kafka message: {} , client {} finished with exception {}", message.getMessage(), ipAddress, e);
			return Collections.singletonMap("result", "Error");
		}
	}
}
