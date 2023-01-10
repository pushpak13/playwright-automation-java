package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchSpec {
    Page page;


    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate(baseURL + "/search-box/");
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should display the searched content when the search button is clicked")
    public void verifyTheSearchedContentWhenTheSearchButtonIsClicked() {
        page.locator("#wp-block-search__input-1").fill("cypress");
        page.click("//button[@class='wp-block-search__button wp-element-button']");
        assertThat(page.locator("//h2[@class='entry-title'] //a")).containsText("Cypress");
    }

}
