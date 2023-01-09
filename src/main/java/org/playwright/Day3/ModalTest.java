package org.playwright.Day3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxModalPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModalTest {
    Page page;
    SandBoxModalPage modalPage;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        modalPage = new SandBoxModalPage(page);
        page.navigate("https://automatenow.io/sandbox-automation-testing-practice-website/");
        modalPage.clickOnModal();
    }

    @Test
    public void verifySimpleModal() throws InterruptedException {
        modalPage.clickOnSimpleModal();
        assertThat(modalPage.getSimpleModalLocator()).isVisible();
        modalPage.closeSimpleModal();
        Thread.sleep(1000);
        assertThat(modalPage.getSimpleModalLocator()).not().isVisible();

    }

    @Test
    public void verifyFormModal() throws InterruptedException {
        modalPage.clickOnFormModal();
        assertThat(modalPage.getFormModalLocator()).isVisible();
        modalPage.clickOnFormModal_NameField();
        modalPage.clickOnFormModal_SubmitField();
        assertThat(modalPage.getFormModalLocator()).not().isVisible();

    }

    @AfterEach
    public void tearDown() {
        page.close();

    }

}
