package com.example.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;

@SpringBootApplication
public class SleuthDemoApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(SleuthDemoApplication.class);

	@Autowired
	private BizService bizService;
	@Autowired
	private Tracer tracer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("start new trace");
		this.bizService.newTraceWithTransaction();

		//get current trace context
		CurrentTraceContext currentTraceContext = tracer.currentTraceContext();
		TraceContext context = null;
		if (currentTraceContext != null)
			context = currentTraceContext.context();

		if (context == null)
			logger.error("current trace not found ");
		else
			logger.info("current trace [id={}]", context.traceId());
	}

	public static void main(String[] args) {
		SpringApplication.run(SleuthDemoApplication.class, args);
	}

}
