package org.playwright.Day4;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.TablesService;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;
import java.util.List;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class TableSpec {
    Page page;
    BrowserSetUp setUp;
    TablesService tablesService;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        tablesService = new TablesService(page);
        page.navigate(EnvConfigs.sandbox_Url);
        assertThat(page).hasURL(EnvConfigs.sandbox_Url);
    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should check if the contents searched in search box are visible in the search results of the table")
    public void verifyTheContentSearchedInTextBoxArePresentInTheTable() {
        this.tablesService.clickTablesLink();
        assertThat(page).hasTitle(Constants.title_TablesPage);
        this.tablesService.clickOnSearchButton();
        this.tablesService.enterSearchTextInSearchBox(Constants.tables_SearchText);
        List<ElementHandle> elementHandles = page.querySelectorAll(Locators.locator_selectedText);
        elementHandles.forEach(elementHandle -> {
            Assertions.assertTrue(elementHandle.textContent().contains(Constants.tables_SearchText));

        });
    }
}
