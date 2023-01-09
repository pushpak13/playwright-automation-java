package org.playwright.Day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SauceDemoLoginPageInputsService;

public class SauceDemoLoginSpec {
    Page page;
    String baseURL = "https://www.saucedemo.com/";

    private SauceDemoLoginPageInputsService sauceDemoLoginPageInputsService;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(/*new BrowserType.LaunchOptions().setHeadless(false)*/);
        page = browser.newPage();
        sauceDemoLoginPageInputsService = new SauceDemoLoginPageInputsService(page);
        page.navigate(baseURL);
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should login to saucedemo")
    public void verifyLoginSuccessInSauceDemo() {
        sauceDemoLoginPageInputsService.enterUsername("standard_user");
        sauceDemoLoginPageInputsService.enterPassword("secret_sauce");
        sauceDemoLoginPageInputsService.clickLoginButton();
        Assertions.assertTrue(page.url().contains("inventory"));
    }

    @Test
    @DisplayName("should display login error message when username and password is empty and login button is clicked")
    public void verifyLoginFailureInSauceDemo() {
        sauceDemoLoginPageInputsService.clickLoginButton();

        Assertions.assertTrue(sauceDemoLoginPageInputsService.getErrorMessageHeadingIsVisible());
    }
}
