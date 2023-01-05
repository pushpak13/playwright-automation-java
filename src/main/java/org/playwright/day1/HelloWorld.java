package org.playwright.day1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HelloWorld {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Page page;
            try (Browser browser = playwright.chromium().launch()) {
                page = browser.newPage();
            }
            page.navigate("http://playwright.dev");
            System.out.println(page.title());
        }
    }
}
