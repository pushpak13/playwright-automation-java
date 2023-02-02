package org.playwright.Day7;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.NetworkResponsePage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

public class NetworkTest {
    Page page;
    BrowserSetUp setUp;
    NetworkResponsePage networkResponsePage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        networkResponsePage = new NetworkResponsePage(page);
    }

    @Test
    public void verifyNetworkResponses() {
        networkResponsePage.getResponseForNetworkEvents();
        networkResponsePage.getResponseForNetworkAfterClick();

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }
}
