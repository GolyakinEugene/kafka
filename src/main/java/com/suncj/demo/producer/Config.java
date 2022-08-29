package com.suncj.demo.producer;

public class Config {
	public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";

	public static final String RETRIES_CONFIG = "group.id";

	public static final String BATCH_SIZE_CONFIG = "enable.auto.commit";

	public static final String LINGER_MS_CONFIG = "auto.commit.interval.ms";

	public static final String BUFFER_MEMORY_CONFIG = "session.timeout.ms";

	public static final String KEY_SERIALIZER_CLASS_CONFIG = "key.serializer";

	public static final String VALUE_SERIALIZER_CLASS_CONFIG = "value.serializer";
}
