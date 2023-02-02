package org.playwright.AllureReport;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.playwright.PlaywrightPOMSeries.SandboxNavigationPage;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.playwright.AllureReport.BasePage.page;

@Listeners({TestAllureListener.class})
public class AllureSandboxNavigationTest {

    BasePage basePage;
    //SandboxNavigationPage sandboxNavigationPage;

    @BeforeTest
    public void setUp() {
        basePage = new BasePage();
        basePage.initialize();
        //sandboxNavigationPage = new SandboxNavigationPage(page);
    }

    @Test(priority = 1, description = "verifying Sandbox page title test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify Sandbox page title test on Sandbox page")
    @Story("Story Name: To check Sandbox page title")
    public void verifySandboxPageTitleTest() {
        /*basePage.getPage().navigate(EnvConfigs.DEMO_URL);
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        sandboxNavigationPage.navigateToSandboxPage();
*/
        basePage.getPage().navigate("https://automatenow.io/");
        Locator sandbox = basePage.getPage().locator("text = Sandbox");
        sandbox.hover();
        sandbox.click();
        Assert.assertEquals(1,6);
    }

    @AfterMethod // --this method will be executed after every test method
    public void tearDown()
    {
        basePage.getPage().context().browser().close();
    }
}
