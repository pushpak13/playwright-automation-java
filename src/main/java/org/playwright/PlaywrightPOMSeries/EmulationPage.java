package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import org.playwright.utils.Constants;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EmulationPage {
    Page page;

    //String Locator
    private String tab_Sandbox = "text=Sandbox";
    private String title_Sandbox = "//h1";

    //Constructor
    public EmulationPage(Page page) {
        this.page = page;
    }

    public void verifyEmulation() {
        // Resize viewport for individual page
        this.page.setViewportSize(1000, 800);
        this.page.waitForTimeout(3000);
        this.page.setViewportSize(1280, 1024);
        Locator sandbox = this.page.locator(tab_Sandbox);
        sandbox.hover();
        sandbox.click();
        assertThat(page.locator(title_Sandbox)).containsText(Constants.title_Sandbox);
    }
}
