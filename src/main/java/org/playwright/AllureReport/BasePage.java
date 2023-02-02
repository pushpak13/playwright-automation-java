package org.playwright.AllureReport;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ColorScheme;
import org.playwright.ExtentReport.PlaywrightFactory;
import org.playwright.utils.Constants;

import java.nio.file.Paths;
import java.util.Properties;


public class BasePage {
    public static Page page;
    public BrowserContext browserContext;
    public Properties prop;

    public void initialize() {
        PlaywrightFactory pf = new PlaywrightFactory();
        Browser browser = pf.initBrowser(Constants.browser_Name);
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setColorScheme(ColorScheme.DARK));
        page = browserContext.newPage();
    }

    public static synchronized Page getPage() {
        return page;
    }


}
