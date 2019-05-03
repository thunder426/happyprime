package com.example.demo;

import com.example.demo.services.HttpClientService;
import com.example.demo.services.RandomNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

/**
 * Created by leizhang on 5/2/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomNumberServiceTest {

    @Autowired
    RandomNumberService randomNumberService;

    @MockBean
    HttpClientService httpClientService;

    @Test
    public void shouldReturnZeroIfNotFound() {
        ResponseEntity<String> response =  new ResponseEntity<String>(
                                                    "Resource not found.",
                                                    HttpStatus.NOT_FOUND
                                            );
        given(httpClientService.doGetString("https://www.random.org/integers/?num=1&min=1&max=1000000&col=1&base=10&format=plain&rnd=new")).willReturn(response);
        assertThat(randomNumberService.getRandomNumber(), is(0));
    }

    @Test
    public void shouldReturnCorrectNumber() {
        ResponseEntity<String> response =  new ResponseEntity<String>(
                "1234",
                HttpStatus.OK
        );
        given(httpClientService.doGetString("https://www.random.org/integers/?num=1&min=1&max=1000000&col=1&base=10&format=plain&rnd=new")).willReturn(response);
        assertThat(randomNumberService.getRandomNumber(), is(1234));
    }

    @Test
    public void shouldReturnCorrectNumberWithTrailingSpace() {
        ResponseEntity<String> response =  new ResponseEntity<String>(
                "1234    ",
                HttpStatus.OK
        );
        given(httpClientService.doGetString("https://www.random.org/integers/?num=1&min=1&max=1000000&col=1&base=10&format=plain&rnd=new")).willReturn(response);
        assertThat(randomNumberService.getRandomNumber(), is(1234));
    }
}
