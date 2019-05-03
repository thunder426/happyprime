package com.example.demo.utils;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by leizhang on 5/2/19.
 */
public class WebUtils {

    /**
     * Returns false if a 404 is returned as status code,
     * else returns true.
     *
     * @param baseurl
     * @return The ResponseEntity<String>
     */
    public static ResponseEntity<String> doGetString(String baseurl) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = null;
        try {
            uri = new URI(baseurl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        return response;
    }
}
