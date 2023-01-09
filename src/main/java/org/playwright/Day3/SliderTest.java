package org.playwright.Day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxSliderPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SliderTest {
    Page page;
    SandBoxSliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        sliderPage = new SandBoxSliderPage(page);
        page.navigate("https://automatenow.io/sandbox-automation-testing-practice-website/");
        sliderPage.clickOnSlider();
    }

    @Test
    public void verifySliderFunctionality() {
        Locator slider = sliderPage.getSlider();
        String targetAmount = "29";
        boolean isCompleted = false;
        if (slider.isVisible()) {
            while (!isCompleted) {
                BoundingBox sliderBoundingBox = slider.boundingBox();
                //if (sliderBoundingBox) {
                    page.mouse().move(sliderBoundingBox.x + sliderBoundingBox.width / 2,
                           sliderBoundingBox.y + sliderBoundingBox.height / 2);
                    page.mouse().down();
                     //12 pixels per step up & 12 pixels per step down
                    page.mouse().move(sliderBoundingBox.x + 327, sliderBoundingBox.y + sliderBoundingBox.height / 2);
                    page.mouse().up();

                    if (targetAmount.equals("29")) {
                        isCompleted = true;
                    }
                //}
            }
            page.waitForTimeout(5000);
            assertEquals(sliderPage.getSliderResultValue(), targetAmount);
        }
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }
}
