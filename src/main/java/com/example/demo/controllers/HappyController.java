package com.example.demo.controllers;

import com.example.demo.services.RandomNumberService;
import com.example.demo.utils.HappyUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RandomNumberService randomNumberService;

    @GetMapping(value = {"/happyprime"})
    public
    @ResponseBody
    ResponseEntity<Map<String, Object>> isHappyPrime() {
        int number = randomNumberService.getRandomNumber();
        if (number != 0){
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
                        put("error", "Error getting random number.");
                    }},
                    HttpStatus.INTERNAL_SERVER_ERROR
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
