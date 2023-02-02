package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Assertions;
import org.playwright.utils.Constants;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IFramesPage {
    Page page;
    FrameLocator frameLocator;

    //String Locator
    private String iframeTab = "#iFrame";
    private String iframe = "globalSqa";
    private String filter = "#current_filter";

    //Constructor
    public IFramesPage(Page page) {
        this.page = page;
    }

    public void clickIframeLink() {
        this.page.click(iframeTab);
        page.waitForLoadState(LoadState.LOAD);
        Assertions.assertTrue(page.getByText(Constants.text_FrameHeader).isVisible());
    }

    public void navigateToIframe() {
        Frame frame = page.frame(iframe);
        assertThat(frame.getByRole(AriaRole.LINK, new Frame.GetByRoleOptions().setName(Constants.link_HomePage))).isVisible();
        page.mouse().wheel(0, 100);
        frame.locator(filter).hover();
        frame.getByRole(AriaRole.LISTITEM).getByText(Constants.text_ListItems).click();
        page.waitForLoadState(LoadState.LOAD);
        page.waitForTimeout(10000);
        page.mouse().wheel(0, 100);
        frame.locator("#portfolio_items").getByText("JMeter Training").hover();

    }

}
