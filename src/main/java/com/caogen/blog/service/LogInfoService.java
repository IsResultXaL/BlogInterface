package com.caogen.blog.service;

import com.caogen.blog.dto.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(LogInfo logInfo){
        logger.info(logInfo.toString());
        return true;
    }

}
