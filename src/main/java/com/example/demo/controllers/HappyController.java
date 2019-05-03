package com.example.demo.controllers;

import com.example.demo.utils.HappyUtils;
import com.example.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leizhang on 5/2/19.
 */
@RestController()
@RequestMapping(value = {"/v1/"})
public class HappyController {
    @Value("${randomServiceUrl}")
    private String randomServiceUrl;

    @GetMapping(value = {"/happyprime"})
    public
    @ResponseBody
    ResponseEntity<Map<String, Object>> isHappyPrime() {
        ResponseEntity<String> response = WebUtils.doGetString(randomServiceUrl);
        if (response.getStatusCode() == HttpStatus.OK){
            int number = Integer.parseInt(response.getBody().trim());
            boolean isHappy = HappyUtils.isHappy(number);
            boolean isPrime = HappyUtils.isPrime(number);
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("number", number);
                        put("isHappy", isHappy);
                        put("isPrime", isPrime);
                        put("isHappyPrime", isHappy && isPrime);
                    }},
                    HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("error", response.getBody());
                    }},
                    response.getStatusCode()
            );
        }
    }

    @GetMapping(value = {"/happyprime/{number}"})
    public
    @ResponseBody
    ResponseEntity<Map<String, Object>> isHappyPrime(@PathVariable(value = "number") int number) {
        boolean isHappy = HappyUtils.isHappy(number);
        boolean isPrime = HappyUtils.isPrime(number);
        return new ResponseEntity<>(
                new HashMap<String, Object>() {{
                    put("number", number);
                    put("isHappy", isHappy);
                    put("isPrime", isPrime);
                    put("isHappyPrime", isHappy && isPrime);
                }},
                HttpStatus.OK
        );
    }
}
