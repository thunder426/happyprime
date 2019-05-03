package com.example.demo;

import com.example.demo.utils.HappyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by leizhang on 5/2/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {


    @Test
    public void shouldReturnCorrectSquareSumPositive() {
        assertThat(HappyUtils.squaresum(1), is(1));
        assertThat(HappyUtils.squaresum(2), is(4));
        assertThat(HappyUtils.squaresum(10), is(1));
        assertThat(HappyUtils.squaresum(123), is(14));
    }

    @Test
    public void shouldReturnCorrectSquareSumNegative() {
        assertThat(HappyUtils.squaresum(-1), is(0));
    }

    @Test
    public void shouldReturnCorrectSquareSumZero() {
        assertThat(HappyUtils.squaresum(0), is(0));
    }

    @Test
    public void shouldReturnCorrectIsHappyPositive() {
        assertThat(HappyUtils.isHappy(1), is(true));
        assertThat(HappyUtils.isHappy(100), is(true));
        assertThat(HappyUtils.isHappy(7), is(true));
        assertThat(HappyUtils.isHappy(167), is(true));
        assertThat(HappyUtils.isHappy(2), is(false));
        assertThat(HappyUtils.isHappy(20000), is(false));
        assertThat(HappyUtils.isHappy(1234567890), is(false));
    }

    @Test
    public void shouldReturnCorrectIsHappyNegative() {
        assertThat(HappyUtils.isHappy(-1), is(false));
    }

    @Test
    public void shouldReturnCorrectIsHappyZero() {
        assertThat(HappyUtils.isHappy(0), is(false));
    }

    @Test
    public void shouldReturnCorrectIsPrimePositive() {
        assertThat(HappyUtils.isPrime(1), is(false));
        assertThat(HappyUtils.isPrime(2), is(true));
        assertThat(HappyUtils.isPrime(7), is(true));
        assertThat(HappyUtils.isPrime(167), is(true));
        assertThat(HappyUtils.isPrime(20000), is(false));
        assertThat(HappyUtils.isPrime(1234567890), is(false));
    }

    @Test
    public void shouldReturnCorrectIsPrimeNegative() {
        assertThat(HappyUtils.isPrime(-1), is(false));
    }

    @Test
    public void shouldReturnCorrectIsPrimeZero() {
        assertThat(HappyUtils.isPrime(0), is(false));
    }
}
