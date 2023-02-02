package org.playwright.Day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;
import org.playwright.PlaywrightPOMSeries.SandBoxFormDataPage;
import org.playwright.models.SandBoxFormDataType;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.SandboxFormDataProvisionService;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormFieldsSpec {
    Page page;
    BrowserSetUp setUp;
    SandboxFormDataProvisionService sandboxFormDataProvisionService;
    SandBoxFormDataPage sandBoxFormDataPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        sandboxFormDataProvisionService = new SandboxFormDataProvisionService();
        sandBoxFormDataPage = new SandBoxFormDataPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        Assertions.assertTrue(page.url().contains(Constants.homePage_SandBoxUrlText));

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @DisplayName("should validate the form fields with valid data")
    public void verifyValidFormData() {
        sandBoxFormDataPage.clickFormFieldsLink();
        assertThat(page).hasURL(Pattern.compile(Constants.link_FormFields));
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
        sandBoxFormDataPage.clickFormFieldsLink();
        assertThat(page).hasURL(Pattern.compile(Constants.link_FormFields));
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
        sandBoxFormDataPage.clickFormFieldsLink();
        assertThat(page).hasURL(Pattern.compile(Constants.link_FormFields));
        page.waitForLoadState(LoadState.LOAD);
        page.getByLabel("Name(required)").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page).hasURL(Pattern.compile("/form-fields/"));
        assertThat(page.getByLabel("Name(required)")).isFocused();
    }

}
