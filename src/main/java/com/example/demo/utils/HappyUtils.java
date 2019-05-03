package com.example.demo.utils;

import java.util.HashSet;

/**
 * Created by leizhang on 5/2/19.
 */
public class HappyUtils {
    public static boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<Integer>();
        while(!set.contains(n)){
            set.add(n);
            n = squaresum(n);
            if (n == 1){
                return true;
            }
        }
        return false;
    }

    private static int squaresum(int n){
        int sum = 0;
        while(n > 0){
            sum += (n%10)*(n%10);
            n = n/10;
        }
        return sum;
    }

    public static boolean isPrime(int n){
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= n/2; i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }
}
