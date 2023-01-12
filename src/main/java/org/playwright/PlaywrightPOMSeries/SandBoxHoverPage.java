package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.playwright.utils.Constants;

public class SandBoxHoverPage {
    Page page;

    //String Locator
    private String link_Hover = "text=Hover";
    private String hover = "#mouse_over";

    //Constructor
    public SandBoxHoverPage(Page page) {
        this.page = page;
    }

    public void clickHoverLink() {
        this.page.click(link_Hover);
    }

    public void hover() {
        this.page.hover(hover);
        String changedText = this.page.locator(hover).textContent();
        Assertions.assertEquals(changedText, Constants.text_Hover);
    }

}
