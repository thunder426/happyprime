package com.example.demo.services;

import org.springframework.http.ResponseEntity;

/**
 * Created by leizhang on 5/2/19.
 */
public interface HttpClientService {
    ResponseEntity<String> doGetString(String baseurl);
}
