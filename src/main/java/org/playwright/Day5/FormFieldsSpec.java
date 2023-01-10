package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.playwright.models.SandBoxFormDataType;
import org.playwright.utils.SandboxFormDataProvisionService;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormFieldsSpec {
    Page page;

    SandboxFormDataProvisionService sandboxFormDataProvisionService;
    String baseURL = "https://automatenow.io/sandbox-automation-testing-practice-website/";

    @BeforeEach
    public void setUp() {
        sandboxFormDataProvisionService = new SandboxFormDataProvisionService();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate(baseURL + "/form-fields/");

    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    @DisplayName("should validate the form fields with valid data")
    public void verifyValidFormData() {
        assertThat(page).hasURL(Pattern.compile("/form-fields/"));
        Locator inputTextForName = page.getByLabel("Name(required)");
        inputTextForName.click();
        SandBoxFormDataType validFormData = sandboxFormDataProvisionService.getValidFormData();
        inputTextForName.fill(validFormData.formName());
        page.getByLabel(validFormData.FavoriteDrink()).check();
        page.getByText(validFormData.FavoriteColour()).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill(validFormData.Email());
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Do you have any siblings?")).selectOption("Yes");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page.locator("//h4[@id='contact-form-success-header']")).containsText("Your message has been sent");
    }

    @Test
    @DisplayName("should validate the form with required valid data")
    public void verifyRequiredFormData() {
        assertThat(page).hasURL(Pattern.compile("/form-fields/"));
        Locator inputTextForName = page.getByLabel("Name(required)");
        inputTextForName.click();
        SandBoxFormDataType requiredFormData = sandboxFormDataProvisionService.getValidFormData();
        inputTextForName.fill(requiredFormData.formName());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page.locator("//h4[@id='contact-form-success-header']")).containsText("Your message has been sent");
    }

    @Test
    @DisplayName("should validate the form with invalid data")
    public void verifyInvalidFormData() {
        assertThat(page).hasURL(Pattern.compile("/form-fields/"));
        page.waitForLoadState(LoadState.LOAD);
        page.getByLabel("Name(required)").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page).hasURL(Pattern.compile("/form-fields/"));
        assertThat(page.getByLabel("Name(required)")).isFocused();
    }

}
