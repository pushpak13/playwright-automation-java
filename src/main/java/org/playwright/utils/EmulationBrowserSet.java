package org.playwright.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class EmulationBrowserSet {

    Playwright playwright;
    Browser browser;


    public Browser intBrowser(String browserName){
        playwright = Playwright.create();
        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                break;
        }

        return browser;
    }

}
