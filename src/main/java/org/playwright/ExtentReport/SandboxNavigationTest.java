package org.playwright.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.playwright.PlaywrightPOMSeries.SandboxNavigationPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SandboxNavigationTest {
    Page page;
    PlaywrightFactory pf;
    SandboxNavigationPage sandboxNavigationPage;
    public ExtentReports reports;
    public ExtentTest test;

    @BeforeTest
    @Parameters("browser")
    public void setUp() {
        pf = new PlaywrightFactory();
        Browser browser = pf.initBrowser(Constants.browser_Name);
        sandboxNavigationPage = new SandboxNavigationPage(page);
    }

    @Test(description = "verifying Sandbox page title test")
    public void verifySandboxPageTitleTest() throws IOException {
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        sandboxNavigationPage.navigateToSandboxPage();

    }

    @AfterMethod // --this method will be executed after every test method
    public void tearDown()
    {
        page.context().browser().close();
        if(reports!=null)
            reports.flush();
    }
}

