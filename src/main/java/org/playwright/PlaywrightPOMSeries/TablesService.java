package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;

public class TablesService {
    Page page;
    TablesService tablesService;

    //String Locator
    private String link_tables = "text=Tables";

    public TablesService(Page page)
    {
        this.page = page;
    }

    public void clickTablesLink() {
        this.page.click(link_tables);
    }

    public void clickOnSearchButton()
    {
        this.page.getByLabel("Search:").click();
    }

    public void enterSearchTextInSearchBox(String searchText)
    {
        this.page.getByLabel("Search:").fill(searchText);
    }
}
