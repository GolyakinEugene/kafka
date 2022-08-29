package com.suncj.demo.consumer;

import com.suncj.demo.models.DateFromKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	private ReadMessage readMessage = new ReadMessage();

	@GetMapping(value = "read/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public DateFromKafka sendKafka(@RequestParam("count") int count) {
		DateFromKafka date = new DateFromKafka();
		try {
			long time_get = Instant.now().getEpochSecond();
			logger.info("Get messages");
			date.setMessagesFromKafka(readMessage.readMessage(count));
			long time_send = Instant.now().getEpochSecond();
			long timeWork = time_send - time_get;
			date.setTime_get(time_get);
			date.setTime_send(time_send);
			date.setTime_work(timeWork);
			return date;
		} catch (Exception e) {
			logger.error("kafka", e);
			return null;
		}
	}
}
