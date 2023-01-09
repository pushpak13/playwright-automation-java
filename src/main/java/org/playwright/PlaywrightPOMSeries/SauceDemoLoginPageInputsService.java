package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;

public class SauceDemoLoginPageInputsService {
    private static final String userNameHTMLInputElement_SelectorPlaceholder = "Username";
    private static final String passwordHTMLInputElement_SelectorPlaceholder = "Password";
    private static final String loginButtonHTMLElement_SelectorText = "LOGIN";
    private static final String loginErrorHTMLElement_SelectorLocator = "[data-test=\"error\"]";
    Page page;

    public SauceDemoLoginPageInputsService(Page page) {
        this.page = page;
    }

    public void enterUsername(String usernameToEnter) {
        this.page.getByPlaceholder(userNameHTMLInputElement_SelectorPlaceholder).fill(usernameToEnter);
    }

    public void enterPassword(String passwordToEnter) {
        this.page.getByPlaceholder(passwordHTMLInputElement_SelectorPlaceholder).fill(passwordToEnter);
    }

    public void clickLoginButton() {
        this.page.getByText(loginButtonHTMLElement_SelectorText).click();
    }

    public void clearUsername() {
        this.page.getByPlaceholder(userNameHTMLInputElement_SelectorPlaceholder).clear();
    }

    public void clearPassword() {
        this.page.getByPlaceholder(passwordHTMLInputElement_SelectorPlaceholder).clear();
    }

    public Boolean getErrorMessageHeadingIsVisible() {
        return this.page.locator(loginErrorHTMLElement_SelectorLocator).isVisible();
    }

}
