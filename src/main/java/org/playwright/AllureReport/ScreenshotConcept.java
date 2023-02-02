package org.playwright.AllureReport;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners({TestAllureListener.class})
public class ScreenshotConcept {
    public BrowserContext browserContext;
    public Page page;
    BrowserSetUp setUp;

    @BeforeMethod // this method will be executed before every @test method
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
    }
    @Test(priority = 1, description = "verifying login page title test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    public void Verifyscreenshot(){
        page.navigate(EnvConfigs.DEMO_URL);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        page.screenshot
                (new Page.ScreenshotOptions().setPath(Paths.get(Constants.SCREENSHOT_PATH)).setFullPage(true));
        page.screenshot
                (new Page.ScreenshotOptions().setPath(Paths.get(Constants.SCREENSHOT_PATH)).setFullPage(false));

    }
    @AfterMethod // --this method will be executed after every test method
    public void tearDown() {
        page.context().browser().close();
    }
}
