package com.example.demo;

import com.example.demo.services.HttpClientService;
import com.example.demo.services.HttpClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by leizhang on 5/2/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientServiceTest {

    @Autowired
    HttpClientService httpClientService;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void shouldReturnResponseDoGetString() throws URISyntaxException {
        ResponseEntity<String> response =  new ResponseEntity<String>(
                                                    "1234",
                                                    HttpStatus.OK
                                            );
        given(restTemplate.getForEntity(new URI("https://test.ok.com"),String.class)).willReturn(response);
        assertThat(httpClientService.doGetString("https://test.ok.com"), is(response));
    }

    @Test
    public void shouldReturnResponse404DoGetString() throws URISyntaxException {
        given(restTemplate.getForEntity(new URI("https://test.404.com"),String.class)).willThrow(ResourceAccessException.class);
        assertThat(httpClientService.doGetString("https://test.404.com").getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
