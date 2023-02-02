package org.playwright.Day6;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.EmulationPage;
import org.playwright.utils.*;

import java.util.Arrays;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EmulationTest {
    Page page;
    BrowserContext context;
    EmulationBrowserSet set;
    EmulationPage emulationPage;

    @BeforeEach
    public void setUp() {
        set = new EmulationBrowserSet();
        Browser browser = set.intBrowser(Constants.browser_Name);
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 1024));
        context.grantPermissions(Arrays.asList(Constants.txt_Notification),
                new BrowserContext.GrantPermissionsOptions().setOrigin(EnvConfigs.DEMO_URL));
        page = context.newPage();
        emulationPage = new EmulationPage(page);
    }

    @Test
    public void emulationTest() {
        page.emulateMedia(new Page.EmulateMediaOptions().setColorScheme(ColorScheme.DARK));
        page.navigate(EnvConfigs.DEMO_URL);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        emulationPage.verifyEmulation();

    }

    @AfterEach
    public void tearDown() {
        context.clearPermissions();
        page.context().browser().close();
    }
}
