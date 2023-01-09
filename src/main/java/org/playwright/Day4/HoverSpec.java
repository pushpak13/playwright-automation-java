package org.playwright.Day4;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

public class HoverSpec {
    Page page;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should check for the changed status on hover event")
    public void verifyTheChangedTextOnHoverEvent() {
        page.navigate(baseURL + "/hover/");
        page.locator("#mouse_over").hover();
        String changedText = page.locator("#mouse_over").textContent();
        Assertions.assertEquals(changedText, "You did it!");
    }


}
