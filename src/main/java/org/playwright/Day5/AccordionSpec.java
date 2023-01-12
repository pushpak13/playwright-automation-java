package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AccordionSpec {
    Page page;
    BrowserSetUp setUp;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        page.navigate(EnvConfigs.sandbox_Url + Constants.link_Accordion);
        assertThat(page).hasTitle(Constants.title_AccordionPage);
    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should show the text inside the accordion when expanded")
    public void verifyIfTheTextInsideTheAccordionIsVisible() {
        page.getByText(Constants.text_clickAccordion).click();
        Locator actualAccordionText = page.getByText(Constants.text_AccordionItem);
        assertThat(actualAccordionText).isVisible();
    }

    @Test
    @DisplayName("should not show the text inside the accordion when contracted")
    public void verifyThatTheWhenClickedTwiceTheAccordionShouldContract() {
        // Clicking to expand the accordion
        page.getByText(Constants.text_clickAccordion).click();
        Locator actualAccordionText = page.getByText(Constants.text_AccordionItem);
        assertThat(actualAccordionText).isVisible();
        // Clicking again to contract the accordion
        page.getByText(Constants.text_clickAccordion).click();
        assertThat(actualAccordionText).not().isVisible();
    }


}
