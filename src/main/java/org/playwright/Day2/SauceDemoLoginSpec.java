package org.playwright.Day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SauceDemoLoginPageInputsService;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SauceDemoLoginSpec {
    Page page;
    BrowserSetUp setUp;
    private SauceDemoLoginPageInputsService sauceDemoLoginPageInputsService;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        sauceDemoLoginPageInputsService = new SauceDemoLoginPageInputsService(page);
        page.navigate(EnvConfigs.sauceDemo_Url);
        assertThat(page).hasURL(EnvConfigs.sauceDemo_Url);
    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should login to saucedemo")
    public void verifyLoginSuccessInSauceDemo() {
        sauceDemoLoginPageInputsService.enterUsername(Constants.sauceDemo_Username);
        sauceDemoLoginPageInputsService.enterPassword(Constants.sauceDemo_Password);
        sauceDemoLoginPageInputsService.clickLoginButton();
        Assertions.assertTrue(page.url().contains(Constants.homePage_UrlText));
    }

    @Test
    @DisplayName("should display login error message when username and password is empty and login button is clicked")
    public void verifyLoginFailureInSauceDemo() {
        sauceDemoLoginPageInputsService.clickLoginButton();
        Assertions.assertTrue(sauceDemoLoginPageInputsService.getErrorMessageHeadingIsVisible());
    }
}
