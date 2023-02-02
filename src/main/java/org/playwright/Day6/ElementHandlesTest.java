package org.playwright.Day6;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.JSHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertEquals;

public class ElementHandlesTest {
    Page page;
    BrowserSetUp setUp;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
    }

    @Test
    public void clickActionWithHandles() {
        page.navigate(EnvConfigs.BUTTONS_URL);
        assertThat(page).hasURL(Pattern.compile(Constants.route_ButtonsLink));
        //Single Click
        ElementHandle singleClick = page.querySelector(Locators.singleClick);
        singleClick.click();
        page.waitForTimeout(1000);
        assertEquals(Constants.text_ClickMe, singleClick.evaluate("node => node.innerText"));
        assertThat(page.locator(Locators.dynamicClickMsg)).containsText(Constants.text_DynamicClickMsg);
        //Double Click
        ElementHandle doubleClick = page.querySelector(Locators.doubleClick);
        doubleClick.dblclick();
        page.waitForTimeout(1000);
        assertThat(page.locator(Locators.doubleClickMsg)).containsText(Constants.text_DoubleClickMsg);

    }

    @Test
    public void contentFrameWithHandles() {
        page.navigate(EnvConfigs.FRAMES_URL);
        page.waitForLoadState(LoadState.LOAD);
        assertThat(page).hasURL(Pattern.compile(Constants.route_FramesLink));
        ElementHandle elementHandle = page.querySelector("#frame1");
        Frame frame = elementHandle.contentFrame();
        assertEquals(page.frames().get(1), frame);
        System.out.println(frame);
    }

    @Test
    public void checkBoxWithHandles() {
        page.navigate(EnvConfigs.TESTING_URL);
        page.waitForLoadState();
        assertThat(page).hasURL(Pattern.compile(Constants.route_RegisterLink));
        ElementHandle elementHandle = page.querySelector(Locators.checkBox);
        assertEquals(false, elementHandle.isChecked());
        elementHandle.check();
        assertEquals(true, elementHandle.isChecked());
        elementHandle.screenshot(new ElementHandle.ScreenshotOptions().setPath(Paths.get(Constants.SCREENSHOT_PATH)));

    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }
}

