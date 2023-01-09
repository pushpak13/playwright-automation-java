package org.playwright.Day3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxPopupPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PopupTest {
    Page page;
    SandBoxPopupPage popupPage;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        popupPage = new SandBoxPopupPage(page);
        page.navigate("https://automatenow.io/sandbox-automation-testing-practice-website/");
        popupPage.clickOnPopups();
    }

    @Test
    public void verifyAlertPopup() {
        page.onDialog(dialog -> {
             String text = dialog.message();
             System.out.println(text);
             dialog.accept();
        });
        popupPage.clickOnAlertButton();

    }

    @Test
    public void verifyConfirmPopupClose() {
        page.onDialog(dialog -> {
            dialog.dismiss();
        });
        popupPage.clickOnConfirmButton();
        String cancelResult = popupPage.getConfirmPopupResultMessage();
        if(cancelResult.contains("Cancel")) {
            System.out.println("Confirm popup cancelled");
            System.out.println(cancelResult);
        }
        else{
            System.out.println("Confirm popup not cancelled");
        }

    }

    @Test
    public void verifyConfirmPopupAccept() {
        page.onDialog(dialog -> {
            dialog.accept();
        });
        popupPage.clickOnConfirmButton();
        String okResult = popupPage.getConfirmPopupResultMessage();
        if(okResult.contains("OK")) {
            System.out.println("Confirm popup accepted");
            System.out.println(okResult);
        }
        else{
            System.out.println("Confirm popup not accepted");
        }

    }

    @Test
    public void verifyPromptPopupWithData() {
        page.onDialog(dialog -> {
            dialog.accept("Playwright");
        });
        popupPage.clickOnPromptButton();
        String promptResult = popupPage.getPromptPopupResultMessage();
        if(promptResult.contains("Playwright")) {
            System.out.println("Prompt box with data and accepted");
            System.out.println(promptResult);
        }
        else{
            System.out.println("Prompt input is without data");
        }
    }

    @Test
    public void verifyPromptPopupCloseWithoutData() {
        page.onDialog(dialog -> {
            dialog.dismiss();
        });
        popupPage.clickOnPromptButton();
        String promptResult = popupPage.getPromptPopupResultMessage();
        if(promptResult.contains("Fine, be that way")) {
            System.out.println("Prompt popup is cancelled without data");
            System.out.println(promptResult);
        }
        else{
            System.out.println("Prompt is not cancelled");
        }

    }

    @AfterEach
    public void tearDown() {
        page.close();

    }
}
