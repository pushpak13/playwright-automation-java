package org.playwright.Day5;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SandBoxBrokenImagesPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

public class BrokenImagesSpec {

    Page page;
    BrowserSetUp setUp;
    SandBoxBrokenImagesPage brokenImagesPage;
    APIRequestContext request;
    Playwright playwright;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        brokenImagesPage = new SandBoxBrokenImagesPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        Assertions.assertTrue(page.url().equals(EnvConfigs.sandbox_Url));
        request = playwright.request().newContext();
    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }

    @Test
    @DisplayName("Test to check for broken images")
    public void verifyAllTheImagesInThePageAreBrokenOrNot() {
        brokenImagesPage.clickBrokenImagesLink();
        assertThat(page).hasTitle(Constants.title_BrokenImagesPage);
        List<String> images = page.querySelectorAll(Locators.locator_imageElements).stream()
                .map(elementHandle -> elementHandle.getAttribute(Constants.text_SRC)).toList();
        images.stream().filter(img -> img.contains(Constants.text_HTTP))
                .forEach(image -> {
                    APIResponse apiResponse = request.get(image);
                    Assertions.assertTrue(apiResponse.ok());
                });
    }

}
