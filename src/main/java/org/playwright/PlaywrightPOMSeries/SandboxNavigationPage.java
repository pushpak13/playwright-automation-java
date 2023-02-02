package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.utils.Constants;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SandboxNavigationPage {
    Page page;

    //String Locator
    private String tab_Sandbox = "text=Sandbox";
    private String title_Sandbox = "//h1";

    //Constructor
    public SandboxNavigationPage(Page page) {
        this.page = page;
    }

    public void navigateToSandboxPage() {
        Locator sandbox = page.locator(tab_Sandbox);
        sandbox.hover();
        sandbox.click();
        assertThat(page.locator(title_Sandbox)).containsText(Constants.title_Sandbox);
    }
}
