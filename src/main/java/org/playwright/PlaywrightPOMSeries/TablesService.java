package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;

public class TablesService {
    Page page;
    TablesService tablesService;

    public TablesService(Page page) {
        this.page = page;
    }

    public void clickOnSearchButton() {
        this.page.getByLabel("Search:").click();
    }

    public void enterSearchTextInSearchBox(String searchText) {
        this.page.getByLabel("Search:").fill(searchText);
    }
}
