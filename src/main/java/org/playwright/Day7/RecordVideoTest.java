package org.playwright.Day7;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SauceDemoLoginPageInputsService;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RecordVideoTest {
    Page page;
    BrowserSetUp setUp;
    SauceDemoLoginPageInputsService sauceDemoLoginPageInputsService;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowserForVideos(Constants.browser_Name);
        sauceDemoLoginPageInputsService = new SauceDemoLoginPageInputsService(page);

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @DisplayName("Login to Sauce-Demo site")
    public void LoginSauceDemoTest() {
        page.navigate(EnvConfigs.sauceDemo_Url);
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page).hasTitle(Constants.title_SauceDemo);
        sauceDemoLoginPageInputsService.enterUsername(Constants.sauceDemo_Username);
        sauceDemoLoginPageInputsService.enterPassword(Constants.sauceDemo_Password);
        sauceDemoLoginPageInputsService.clickLoginButton();
        Assertions.assertTrue(page.url().contains(Constants.homePage_UrlText));

    }

}
