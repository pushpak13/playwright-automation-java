package org.playwright.Day7;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MouseTest {
    Page page;
    BrowserSetUp setUp;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
    }

    @Test
    public void mouseActions () throws InterruptedException {
        page.navigate(EnvConfigs.DEMO_URL);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        page.mouse().move(900, 40);
        page.waitForTimeout(2000);
        page.mouse().click(900, 33);
        page.waitForLoadState();
        assertThat(page.locator(Locators.heading)).containsText(Constants.title_Training);
        page.mouse().dblclick(900,42);
        page.waitForTimeout(000);

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }
}
