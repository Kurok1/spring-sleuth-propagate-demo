package com.example.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.ScopedSpan;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:maimengzzz@gmail.com">韩超</a>
 * @since 1.0.0
 */
@Service
public class BizService {

    private static final Logger logger = LoggerFactory.getLogger(BizService.class);

    @Autowired
    private Tracer tracer;

    @Transactional
    public void newTraceWithTransaction() {
        ScopedSpan scopedSpan = tracer.startScopedSpan("new-trace");
        logger.info("has create new trace : {}", scopedSpan.context().traceId());
    }

}
