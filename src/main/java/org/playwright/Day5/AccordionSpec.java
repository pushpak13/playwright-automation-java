package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AccordionSpec {
    Page page;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate(baseURL + "/accordions/");
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should show the text inside the accordion when expanded")
    public void verifyIfTheTextInsideTheAccordionIsVisible() {
        page.getByText("Click to see more").click();
        Locator actualAccordionText = page.getByText("This is an accordion item.");
        assertThat(actualAccordionText).isVisible();
    }

    @Test
    @DisplayName("should not show the text inside the accordion when contracted")
    public void verifyThatTheWhenClickedTwiceTheAccordionShouldContract() {
        // Clicking to expand the accordion
        page.getByText("Click to see more").click();
        Locator actualAccordionText = page.getByText("This is an accordion item.");
        assertThat(actualAccordionText).isVisible();
        // Clicking again to contract the accordion
        page.getByText("Click to see more").click();
        assertThat(actualAccordionText).not().isVisible();
    }


}
