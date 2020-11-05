package com.citi.test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ScanLines {

	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        String s = scan.next();
String [] sArray = s.split(" ");
double d = Double.parseDouble(sArray[0]);
        // Write your code here.
        scan.close();
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
	}

}
