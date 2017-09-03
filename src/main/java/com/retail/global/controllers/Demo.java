package com.retail.global.controllers;

import javax.validation.constraints.Digits;
import java.util.Random;

public class Demo{
    public static void main(String[] args) {
        Random random = new Random();
        int otp = 0;
        int length = 0;
        while (length != 4) {
            otp = random.nextInt(9990);
            length = (int) (Math.log10(otp)+1);
        }
        System.out.printf("otp ::" +otp);
    }
}
