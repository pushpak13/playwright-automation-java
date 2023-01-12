package org.playwright.Day5;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SandBoxCalendarPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarSpec {

    Page page;
    BrowserSetUp setUp;
    SandBoxCalendarPage calenderPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        calenderPage = new SandBoxCalendarPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        Assertions.assertTrue(page.url().equals(EnvConfigs.sandbox_Url));
    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should check for a valid date of a calendar")
    public void verifyForAValidDateOfACalendar() {
        calenderPage.clickCalendarLink();
        assertThat(page).hasTitle(Constants.title_CalendarPage);
        calenderPage.verifyValidDateForCalendar();

    }
}
