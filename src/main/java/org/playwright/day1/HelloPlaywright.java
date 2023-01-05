package org.playwright.day1;



public class HelloPlaywright {
    public static void main(String[] args) {
        String message = "Hello Playwright!";
        if(message.contains("Playwright")) {
            System.out.println("Text matches");
        }
        else {
            System.out.println("Text does not matches");
        }
        System.out.println(message);
    }

}
