package org.playwright.Day3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxPopupPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PopupTest {
    Page page;
    BrowserSetUp setUp;
    SandBoxPopupPage popupPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        popupPage = new SandBoxPopupPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        assertThat(page).hasURL(EnvConfigs.sandbox_Url);

    }

    @Test
    public void verifyAlertPopup() {
        popupPage.clickOnPopups();
        assertThat(page).hasTitle(Constants.title_PopupPage);
        page.onDialog(dialog -> {
             String text = dialog.message();
             Assertions.assertEquals(text, Constants.msg_AlertPopup);
             dialog.accept();
        });
        popupPage.clickOnAlertButton();

    }

    @Test
    public void verifyConfirmPopupClose() {
        popupPage.clickOnPopups();
        assertThat(page).hasTitle(Constants.title_PopupPage);
        page.onDialog(dialog -> {
            dialog.dismiss();
        });
        popupPage.clickOnConfirmButton();
        String cancelResult = popupPage.getConfirmPopupResultMessage();
        Assertions.assertEquals(cancelResult, Constants.cancelMsg_ConfirmPopup);

    }

    @Test
    public void verifyConfirmPopupAccept() {
        popupPage.clickOnPopups();
        assertThat(page).hasTitle(Constants.title_PopupPage);
        page.onDialog(dialog -> {
            dialog.accept();
        });
        popupPage.clickOnConfirmButton();
        String okResult = popupPage.getConfirmPopupResultMessage();
        Assertions.assertEquals(okResult, Constants.okMsg_ConfirmPopup);

    }

    @Test
    public void verifyPromptPopupWithData() {
        popupPage.clickOnPopups();
        assertThat(page).hasTitle(Constants.title_PopupPage);
        page.onDialog(dialog -> {
            dialog.accept(Constants.text_PromptPopup);
        });
        popupPage.clickOnPromptButton();
        String promptResult = popupPage.getPromptPopupResultMessage();
        Assertions.assertEquals(promptResult, Constants.msg_PromptPopup);

    }

    @Test
    public void verifyPromptPopupCloseWithoutData() {
        popupPage.clickOnPopups();
        assertThat(page).hasTitle(Constants.title_PopupPage);
        page.onDialog(dialog -> {
            dialog.dismiss();
        });
        popupPage.clickOnPromptButton();
        String promptResult = popupPage.getPromptPopupResultMessage();
        Assertions.assertEquals(promptResult, Constants.cancelMsg_PromptPopup);

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();

    }
}
