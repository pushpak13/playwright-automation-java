package org.playwright.Day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.SandBoxSliderPage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        page.navigate("https://automatenow.io/sandbox-automation-testing-practice-website/slider/");
        IntStream.range(0, 10).forEach(integer -> {
            page.locator("#slideMe").press("ArrowRight");
        });
        assertThat(page.locator("#value")).containsText("35");
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }
}
