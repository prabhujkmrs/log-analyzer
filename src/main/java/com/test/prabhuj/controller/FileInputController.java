package com.test.prabhuj.controller;

import com.test.prabhuj.service.LogProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/upload")
public class FileInputController {
    private static final Logger logger= LoggerFactory.getLogger(FileInputController.class);

    @Autowired
    LogProcessorService logProcessorService;

    @PostMapping
    public ResponseEntity<String> processAndSave(@RequestParam("file") MultipartFile file) throws Exception{
        logger.info("Starting - file processing...");
        logProcessorService.process(file);
        logger.info("Finished - file processing..");
        return new ResponseEntity<>("Log file uploaded for analysis", HttpStatus.OK);
    }


}
