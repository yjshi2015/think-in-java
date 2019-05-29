package com.syj.demo.thread;

public class FibonacciTest {

    public static void main(String[] args) {
        int result = recursiveCal(7);
        System.out.println("result==============" + result);
    }

    private static int recursiveCal(int n) {

        if (n <= 1) {
            return 1;
        } else {
           return recursiveCal(n-1) + recursiveCal(n-2);
        }
    }
}
