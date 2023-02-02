package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.utils.SandboxFormDataProvisionService;
import org.playwright.utils.Constants;

public class SandBoxFormDataPage {
    Page page;

    //String Locator
    private String link_FormFields = "text=Form Fields";

    //Constructor
    public SandBoxFormDataPage(Page page) {
        this.page = page;
    }

    public void clickFormFieldsLink() {
        this.page.click(link_FormFields);
    }

    public void enterNameField() {
        Locator inputTextForName = page.getByLabel(Constants.label_Name);
        inputTextForName.click();
    }

}
