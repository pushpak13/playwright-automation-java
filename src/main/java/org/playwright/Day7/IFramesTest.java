package org.playwright.Day7;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.IFramesPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IFramesTest {
    Page page;
    BrowserSetUp setUp;
    IFramesPage iFramesPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        iFramesPage = new IFramesPage(page);
    }

    @Test
    public void iFrameTest() {
        page.navigate(EnvConfigs.globalSQA_Url);
        page.waitForLoadState();
        assertThat(page).hasURL(Pattern.compile(Constants.link_Frames));
        iFramesPage.clickIframeLink();
        iFramesPage.navigateToIframe();

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }



}
