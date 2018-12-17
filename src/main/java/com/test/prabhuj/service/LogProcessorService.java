package com.test.prabhuj.service;

import com.test.prabhuj.domain.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface LogProcessorService {
    /**
     * Read and process log file;
     */
    public void process(MultipartFile file) throws IOException;

    /**
     * Save the logs to DB
     */
    public void save(Event event);

}
