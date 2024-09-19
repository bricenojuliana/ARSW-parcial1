/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) {
        System.out.println(bytesToHex(PiDigits.getDigits(0, 10)));
//        String num1 = bytesToHex(PiDigits.getDigits(1, 100));
        System.out.println(bytesToHex(PiDigits.getDigits(1, 100)));

//        System.out.println(bytesToHex(PiDigits.getDigits(1, 1000000)));

        PiDigitsThread digit1 = new PiDigitsThread(1, 20);
        PiDigitsThread digit2 = new PiDigitsThread(21, 80);
        PiDigitsThread digit3 = new PiDigitsThread(81, 100);

        digit1.start();
        digit2.start();
        digit3.start();

        try {
            digit1.join();
            digit2.join();
            digit3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(bytesToHex(digit1.getDigits()));
        System.out.print(bytesToHex(digit2.getDigits()));
        System.out.print(bytesToHex(digit3.getDigits()));

//        String num2 = bytesToHex(digit1.getDigits()) + bytesToHex(digit2.getDigits()) + bytesToHex(digit3.getDigits());

//        System.out.println(num1.equals(num2));
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
