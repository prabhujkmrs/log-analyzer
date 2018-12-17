package com.test.prabhuj.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.prabhuj.data.HSQLDBClient;
import com.test.prabhuj.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogProcessorServiceImpl implements LogProcessorService {
    private static final Logger logger= LoggerFactory.getLogger(LogProcessorServiceImpl.class);

    @Autowired
    HSQLDBClient hsqldbClient;

    @Override
    public void process(MultipartFile file) throws IOException {
        ByteArrayInputStream stream = new  ByteArrayInputStream(file.getBytes());
        InputStreamReader input = new InputStreamReader(stream);
        BufferedReader buf = new BufferedReader(input);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Event> eventMap = new HashMap<>();
        String line;
        while((line=buf.readLine()) != null){
            Event event = objectMapper.readValue(line, Event.class);
            if(!eventMap.containsKey(event.getId())){
                eventMap.put(event.getId(),event);
            }
            else {
                Long startTime = eventMap.get(event.getId()).getTimestamp();
                Long endTime = event.getTimestamp();
                long timeDiff = Math.abs(endTime - startTime);
                if (timeDiff > 4) {
                    event.setAlert(true);
                    save(event);
                    logger.info("Event id: "+event.getId() +" saved to db as it took " + timeDiff + " ms");
                }
            }
        }
    }

    @Override
    public void save(Event event) {
        hsqldbClient.save(event);
    }
}
