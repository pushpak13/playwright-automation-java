package org.playwright.Day4;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SandBoxHoverPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HoverSpec {
    Page page;
    BrowserSetUp setUp;
    SandBoxHoverPage hoverPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        hoverPage = new SandBoxHoverPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        assertThat(page).hasURL(EnvConfigs.sandbox_Url);
    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should check for the changed status on hover event")
    public void verifyTheChangedTextOnHoverEvent() {
        hoverPage.clickHoverLink();
        assertThat(page).hasTitle(Constants.title_HoverPage);
        hoverPage.hover();

    }


}
