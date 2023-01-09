package org.playwright.Day4;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.TablesService;

import java.util.List;

public class TableSpec {
    Page page;
    TablesService tablesService;
    Playwright playwright;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        tablesService = new TablesService(page);
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should check if the contents searched in search box are visible in the search results of the table")
    public void verifyTheContentSearchedInTextBoxArePresentInTheTable() {
        page.navigate(baseURL + "/tables/");
        this.tablesService.clickOnSearchButton();
        this.tablesService.enterSearchTextInSearchBox("ind");
        List<ElementHandle> elementHandles = page.querySelectorAll("tr:has-text(\"Ind\")");
        elementHandles.forEach(elementHandle -> {
            Assertions.assertTrue(elementHandle.textContent().contains("Ind"));
            System.out.println("elementHandle = " + elementHandle.textContent());
        });
    }
}
