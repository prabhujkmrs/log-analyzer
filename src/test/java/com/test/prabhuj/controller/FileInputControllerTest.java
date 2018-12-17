package com.test.prabhuj.controller;

import com.test.prabhuj.service.LogProcessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
public class FileInputControllerTest {

    @Mock
    LogProcessorService logProcessorService;

    @InjectMocks
    private FileInputController fileInputController = new FileInputController();


    @Test
    public void processAndSaveTest() throws Exception {
        MockMultipartFile jsonFile = new MockMultipartFile("test.json", "", "application/json", "{\"id\":\"scsmbstgrc\", \"state\":\"STARTED\", \"timestamp\":1491377495210}".getBytes());
        ResponseEntity<String> response = fileInputController.processAndSave(jsonFile);
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertEquals(response.getBody(),"Log file uploaded for analysis");
    }

}
