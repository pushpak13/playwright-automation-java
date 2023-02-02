package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NetworkResponsePage {
    Page page;

    //String Locator
    private String tab_Reviews = "text=Reviews";
    private String heading = "//h1";

    //Constructor
    public NetworkResponsePage(Page page) {
        this.page = page;
    }

    public void getResponseForNetworkEvents() {
        this.page.onRequest(request -> System.out.println(">> " + request.method() + " " + request.url()));
        this.page.onResponse(response -> System.out.println("<<" + response.status() + " " + response.url()));
        this.page.navigate(EnvConfigs.DEMO_URL);
        assertThat(page.locator(Locators.navTab)).containsText(Constants.tab_Home);
        Locator sandbox = this.page.locator(tab_Reviews);
        sandbox.hover();
        sandbox.click();
        assertThat(page.locator(heading)).containsText(Constants.text_ReviewsPage);
    }

    public void getResponseForNetworkAfterClick() {
        System.out.println("RESPONSE AFTER CLICK");
        // Use a glob URL pattern
        try{
            Response response = page.waitForResponse(Constants.predicate_Url, () -> {
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Constants.tab_Home)).click();
            });
            page.onResponse(response1 -> System.out.println("<<" + response1.status() + " " + response1.url()));

        }catch (Exception e){

        }
    }

}
