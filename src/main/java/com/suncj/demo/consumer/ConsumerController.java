package com.suncj.demo.consumer;

import com.suncj.demo.models.DateFromKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@RestController
public class ConsumerController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	private ReadMessage readMessage = new ReadMessage();

	@GetMapping(value = "read/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public DateFromKafka sendKafka(@RequestParam("count") int count, HttpServletRequest request) {
		String ipAddress =  request.getRemoteAddr();
		DateFromKafka date = new DateFromKafka();
		logger.info("Reading data from {} started", ipAddress);
		try {
			long time_get = Instant.now().getEpochSecond();
			date.setMessagesFromKafka(readMessage.readMessage(count));
			long time_send = Instant.now().getEpochSecond();
			long timeWork = time_send - time_get;
			date.setTime_get(time_get);
			date.setTime_send(time_send);
			date.setTime_work(timeWork);
			return date;
		} catch (Exception e) {
			logger.info("Reading data from {} finished with exception {}", ipAddress, e);
			return null;
		}
	}
}
