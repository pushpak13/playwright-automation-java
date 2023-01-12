package org.playwright.Day1;

import org.junit.jupiter.api.Assertions;
import org.playwright.utils.Constants;

public class HelloPlaywright {
    public static void main(String[] args) {
        String message = "Hello Playwright!";
        Assertions.assertEquals(message, Constants.msg_Playwright);
    }
}
