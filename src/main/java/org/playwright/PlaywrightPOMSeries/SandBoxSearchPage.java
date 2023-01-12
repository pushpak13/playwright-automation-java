package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;
import org.playwright.utils.Constants;
import org.playwright.utils.Locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SandBoxSearchPage {
    Page page;

    //Locators
    private String searchLink = "text=Search Boxes";
    private String searchInput = "#wp-block-search__input-1";
    private String searchButton = "//button[@class='wp-block-search__button wp-element-button']";
    private String searchPageTitle = "//h2[@class='entry-title'] //a";

    //Constructor
    public SandBoxSearchPage(Page page) {
        this.page = page;

    }

    public void clickSearchLink() {
        this.page.click(searchLink);

    }

    public void enterInputData(String InputData) {
        this.page.fill(searchInput, InputData);
        this.page.click(searchButton);

    }

    public void assertSearchPage() {
        this.page.waitForLoadState();
        assertThat(page.locator(searchPageTitle)).containsText(Constants.text_Search);
    }


}
