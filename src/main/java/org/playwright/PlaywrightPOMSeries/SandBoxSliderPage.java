package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.utils.Constants;

import java.util.stream.IntStream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class SandBoxSliderPage {

    Page page;

    //String Locator
    private String link_slider = "text=Slider";
    private String sliderHTMLElement_SelectorID = "//input[@type='range']";
    private String sliderResultHTMLElement_SelectorID = "//span[@id='value']";
    private String slide = "#slideMe";
    private String slider_Value = "#value";

    //Constructor
    public SandBoxSliderPage(Page page) {
        this.page = page;
    }

    public void clickOnSlider() {
        this.page.click(link_slider);
    }

    public void sliderAction() {
        IntStream.range(0, 10).forEach(integer -> {
            this.page.locator(slide).press(Constants.slide_Key);
        });
        assertThat(page.locator(slider_Value)).containsText(Constants.slider_Value);
    }

    public Locator getSlider() {
         return this.page.locator(this.sliderHTMLElement_SelectorID);

    }
    public String getSliderResultValue() {
        return this.page.locator(this.sliderResultHTMLElement_SelectorID).innerText();
    }
}
