package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SandBoxSearchPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchSpec {
    Page page;
    BrowserSetUp setUp;
    SandBoxSearchPage searchPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        searchPage = new SandBoxSearchPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        Assertions.assertTrue(page.url().contains(Constants.homePage_SandBoxUrlText));
    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should display the searched content when the search button is clicked")
    public void verifyTheSearchedContentWhenTheSearchButtonIsClicked() {
        searchPage.clickSearchLink();
        Assertions.assertTrue(page.url().contains(Constants.searchPage_UrlText));
        searchPage.enterInputData(Constants.text_Search);
        searchPage.assertSearchPage();

    }

}
