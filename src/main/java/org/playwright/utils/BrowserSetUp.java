package org.playwright.utils;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.List;

public  class BrowserSetUp {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;


    public Page initBrowser(String browserName) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 768));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 768));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 768));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 728));
                break;
            default:
                System.out.println("Invalid Browser");
                break;
        }
        page = context.newPage();
        return page;

    }

    public Page initBrowserForVideos(String browserName) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 728)
                        .setRecordVideoDir(Paths.get("MyVideo/")));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 768));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setArgs(List.of("--window-position=-8,-0")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 768));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
                        .setHeadless(false).setArgs(List.of("--window-position=-7,-2")));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1400, 728));
                break;
            default:
                System.out.println("Invalid Browser");
                break;

        }
        page = context.newPage();
        return page;
    }

}
