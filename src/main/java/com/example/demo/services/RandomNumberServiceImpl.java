package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by leizhang on 5/2/19.
 */
@Service
public class RandomNumberServiceImpl implements RandomNumberService {

    @Value("${randomServiceUrl}")
    private String randomServiceUrl;

    @Autowired
    HttpClientService httpClientService;

    public int getRandomNumber(){
        ResponseEntity<String> response = httpClientService.doGetString(randomServiceUrl);
        try{
            int number = Integer.parseInt(response.getBody().trim());
            return number;
        }
        catch (NumberFormatException e){
            return 0;
        }
    }
}
