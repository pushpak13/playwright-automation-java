package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SandBoxModalPage {
     Page page;

    //String Locator
    private String link_modal = "text=Modals";
    private String button_simpleModal = "id=simpleModal";
    private String button_close = "//*[@id=\"popmake-1318\"]/button";
    private String button_formModal = "id=formModal";
    private String field_name = "id=g1051-name";

    //Constructor
    public SandBoxModalPage(Page  page)
    {
        this.page = page;
    }

    public void clickOnModal() {
        this.page.click(link_modal);
    }

    public void clickOnSimpleModal() {
        this.page.click(button_simpleModal);
    }

    public void closeSimpleModal()
    {
         this.page.click(button_close);
         this.page.waitForTimeout(2000);
    }

    public Locator getSimpleModalLocator() {
        return this.page.locator("#pum_popup_title_1318");

    }

    public void clickOnFormModal() {
        this.page.click(button_formModal);
    }

    public void clickOnFormModal_NameField()
    {

        this .page.fill(field_name, "Playwright");
    }


    public void clickOnFormModal_SubmitField() {
        this.page.getByText("Submit").click();

    }

    public Locator getFormModalLocator() {
        return this.page.locator("#popmake-674");
    }

}
