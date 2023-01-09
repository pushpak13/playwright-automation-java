package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class SandBoxSliderPage {

    Page page;

    //String Locator
    private String link_slider = "text=Slider";
    private String sliderHTMLElement_SelectorID = "//input[@type='range']";
    private String sliderResultHTMLElement_SelectorID = "//span[@id='value']";

    //Constructor
    public SandBoxSliderPage(Page page) {
        this.page = page;
    }

    public void clickOnSlider() {
        this.page.click(link_slider);
    }

    public Locator getSlider() {
         return this.page.locator(this.sliderHTMLElement_SelectorID);

    }
    public String getSliderResultValue() {
        return this.page.locator(this.sliderResultHTMLElement_SelectorID).innerText();
    }
}
