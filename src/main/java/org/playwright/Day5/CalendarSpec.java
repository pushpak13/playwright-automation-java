package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarSpec {

    Page page;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate(baseURL + "/calendars/");
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should check for a valid date of a calendar")
    public void verifyForAValidDateOfACalendar() {
        page.click("//input[@class='date jp-contact-form-date hasDatepicker']");
        Locator datePicker = page.locator("//input[@class='date jp-contact-form-date hasDatepicker']");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM DD, YYYY");
        String dateToBeSelectedAndVerified = LocalDateTime.now().plusDays(1).format(dateTimeFormatter);
        datePicker.type(dateToBeSelectedAndVerified);
        datePicker.press("Tab");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        String fieldValue = page.locator("//div[@class='field-value']").innerText();
        assertEquals(dateToBeSelectedAndVerified, fieldValue);
    }
}
