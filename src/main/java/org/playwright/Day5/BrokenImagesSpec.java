package org.playwright.Day5;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.List;

public class BrokenImagesSpec {
    private static final String xPathForImageElements = "//div[@class='wp-block-columns is-layout-flex wp-container-4']//div[@class='wp-block-column is-layout-flow']//img";
    Page page;

    APIRequestContext request;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
        request = playwright.request().newContext();
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("Test to check for broken images")
    public void verifyAllTheImagesInThePageAreBrokenOrNot() {
        page.navigate(baseURL + "/broken-images/");
        List<String> images = page.querySelectorAll(xPathForImageElements).stream().map(elementHandle -> elementHandle.getAttribute("src")).toList();
        images.stream().filter(img -> img.contains("http"))
                .forEach(image -> {
                    APIResponse apiResponse = request.get(image);
                    Assertions.assertTrue(apiResponse.ok());
                });
    }

}
