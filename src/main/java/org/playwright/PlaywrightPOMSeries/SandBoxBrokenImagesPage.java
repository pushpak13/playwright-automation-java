package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.playwright.utils.Constants;
import org.playwright.utils.Locators;

import java.util.List;

public class SandBoxBrokenImagesPage {
    Page page;


    //String Locators
    private String link_BrokenImages = "text=Broken Images";


    //Constructors
    public SandBoxBrokenImagesPage(Page page) {
        this.page = page;
    }

    public void clickBrokenImagesLink() {
        this.page.click(link_BrokenImages);
    }

}
