package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Page;
public class SandBoxPopupPage {
    Page page;

    //String Locator
    private String link_popups = "text=Popups";
    private String alertBoxHTMLElement_SelectorID = "#alert";
    private String confirmBoxHTMLElement_SelectorID = "#confirm";
    private String promptBoxHTMLElement_SelectorID = "#prompt";
    private String confirmHTMLElementResult_SelectorID = "#confirmResult";
    private String promptInputResultHTMLElement_SelectorID = "#promptResult";

    //Constructor
    public SandBoxPopupPage(Page page) {
        this.page = page;
    }

    public void clickOnPopups() {
        this.page.click(link_popups);
    }

    public void clickOnAlertButton() {
        this.page.locator(this.alertBoxHTMLElement_SelectorID).click();
    }
    public void clickOnConfirmButton() {
        this.page.locator(this.confirmBoxHTMLElement_SelectorID).click();
    }
    public void clickOnPromptButton() {
        this.page.locator(this.promptBoxHTMLElement_SelectorID).click();
    }
    public String getConfirmPopupResultMessage() {
        return this.page.locator(this.confirmHTMLElementResult_SelectorID).innerText();
    }
    public String getPromptPopupResultMessage() {
        return this.page.locator(this.promptInputResultHTMLElement_SelectorID).innerText();
    }

}
