package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // given array will have either digit or operand in it
        // first element is always the mainOperation
        String[] givenArray = new String[]{"+", "2", "5","*","4","3", "/", "1","5", "4", "-", "5", "3", "8"};


        System.out.println( calculate(givenArray) );

    }

    static double calculate(String[] arr){

        String mainOperation = arr[0];
        String operation = "";

        HashMap<String, Double> hm = new HashMap<>();
        hm.put("+", 0d);// will add the following elements
        hm.put("-", 0d);// will subtract the following elements
        hm.put("*", 1d);// will multiply the following elements
        hm.put("/", 1d);// will divide the following elements

        for(String i : arr){
            //add the elements which follows + operand
            if(i.equals("+")) operation ="+";
            if(operation.equals("+") && isNumeric(i)) hm.put("+", hm.get("+") + Double.parseDouble(i));

            //multiply the elements which follows * operand
            if(i.equals("*")) operation ="*";
            if(operation.equals("*") && isNumeric(i)) hm.put("*", hm.get("*") * Double.parseDouble(i));

            //divide the elements which follows / operand
            if(i.equals("/")) operation ="/";
            if(operation.equals("/") && isNumeric(i)) hm.put("/", hm.get("/") / Double.parseDouble(i));

            //subtract the elements which follows - operand
            if(i.equals("-")) operation ="-";
            if(operation.equals("-") && isNumeric(i)) hm.put("-", hm.get("-") - Double.parseDouble(i));
        }

        //this will print the hm key value pairs the result is defined for the given example
        System.out.println(hm); //{*=12.0, +=7.0, -=-16.0, /=0.05}

        double result = 0;
        if(mainOperation.equals("+")) {
            for (Double i : hm.values()) { result += i; }
        }

        if(mainOperation.equals("-")) {
            for (Double i : hm.values()) { result -= i; }
        }

        //to avoid result 0 all the time result set to 1
         if(mainOperation.equals("*")) {
            result = 1;
            for (Double i : hm.values()) { result -= i; }
        }

        //to avoid result 0 all the time time set to 1
        if(mainOperation.equals("/")) {
            result = 1;
            for (Double i : hm.values()) { result /= i;}
        }

        return result;

    }

    static boolean isNumeric(String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

}
