package org.playwright.Day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxSliderPage;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SliderTest {
    Page page;
    BrowserSetUp setUp;
    SandBoxSliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        sliderPage = new SandBoxSliderPage(page);
        page.navigate(EnvConfigs.sandbox_Url);
        assertThat(page).hasURL(EnvConfigs.sandbox_Url);
    }

    @Test
    public void verifySliderFunctionality() {
        sliderPage.clickOnSlider();
        assertThat(page).hasTitle(Constants.title_SliderPage);
        sliderPage.sliderAction();

    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }
}
