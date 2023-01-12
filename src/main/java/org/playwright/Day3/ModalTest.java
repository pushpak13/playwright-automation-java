package org.playwright.Day3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxModalPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModalTest {
    Page page;
    BrowserSetUp setUp;
    SandBoxModalPage modalPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        modalPage = new SandBoxModalPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        assertThat(page).hasURL(EnvConfigs.sandbox_Url);

    }

    @Test
    public void verifySimpleModal() throws InterruptedException {
        modalPage.clickOnModal();
        assertThat(page).hasTitle(Constants.title_ModalsPage);
        modalPage.clickOnSimpleModal();
        assertThat(modalPage.getSimpleModalLocator()).isVisible();
        modalPage.closeSimpleModal();
        assertThat(modalPage.getSimpleModalLocator()).not().isVisible();

    }

    @Test
    public void verifyFormModal() throws InterruptedException {
        modalPage.clickOnModal();
        assertThat(page).hasTitle(Constants.title_ModalsPage);
        modalPage.clickOnFormModal();
        assertThat(modalPage.getFormModalLocator()).isVisible();
        modalPage.clickOnFormModal_NameField();
        modalPage.clickOnFormModal_SubmitField();
        assertThat(modalPage.getFormModalLocator()).not().isVisible();

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();

    }

}
