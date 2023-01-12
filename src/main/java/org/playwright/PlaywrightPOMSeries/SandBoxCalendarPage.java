package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.playwright.utils.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SandBoxCalendarPage {
    Page page;

    //String Locator
    private String link_Calendar = "text=Calendars";
    private String date_Picker = "//input[@class='date jp-contact-form-date hasDatepicker']";
    private String field_Value = "//div[@class='field-value']";

    //Constructor
    public SandBoxCalendarPage(Page page) {
        this.page = page;
    }

    public void clickCalendarLink() {
        this.page.click(link_Calendar);
    }

    public void verifyValidDateForCalendar() {
        this.page.click(date_Picker);
        Locator datePicker = page.locator(date_Picker);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.format_Date);
        String dateToBeSelectedAndVerified = LocalDateTime.now().plusDays(Constants.count_Days).format(dateTimeFormatter);
        datePicker.type(dateToBeSelectedAndVerified);
        datePicker.press(Constants.key_Press);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Constants.text_Submit)).click();
        String fieldValue = this.page.locator(field_Value).innerText();
        assertEquals(dateToBeSelectedAndVerified, fieldValue);
    }
}
