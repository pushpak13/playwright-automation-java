package org.playwright.Day5;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;
import org.playwright.models.BlogPosts;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.MapperUtility;
import org.playwright.utils.TestDataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class APIValidationSpec extends TestDataProvider {
    MapperUtility mapperUtility;
    APIRequestContext requestContext;

    Map<String, Object> blogPostData = new HashMap();

    @BeforeTest //(dataProvider = "PostData")
    public void setup(int userID, String title, String body) {
        Map<String, String> headers = new HashMap<>();
        mapperUtility = new MapperUtility();
        headers.putIfAbsent(Constants.headers_Status, Constants.headers_ContentType);
        requestContext = Playwright.create().request().newContext(new APIRequest.NewContextOptions().setExtraHTTPHeaders(headers));
        blogPostData.put("userId", userID);
        blogPostData.put("title", title);
        blogPostData.put("body", body);
    }

    @AfterTest
    public void breakup() {
        requestContext = null;
        blogPostData.clear();
    }

    @Test
    @DisplayName("should get all the posts")
    public void getAllBlogPosts() {
        APIResponse apiResponse = this.requestContext.get(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL);
        assertThat(apiResponse).isOK();
        List<BlogPosts> parsedBlogPosts = mapperUtility.getPostsObjectFromJson(apiResponse);
        Assertions.assertTrue(parsedBlogPosts.size() > 0);
    }

    @Test
    @DisplayName("should create a blog post")
    public void createABlogPost() {
        APIResponse apiResponse = this.requestContext.post(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL, RequestOptions.create().setData(blogPostData));
        assertThat(apiResponse).isOK();
    }

    @Test //(dataProvider = "PutData")
    @DisplayName("should update a blog post")
    public void updateABlogPost(int userID) {
        blogPostData.replace("userId", userID);
        APIResponse apiResponse = this.requestContext.put(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL + Constants.id_POST, RequestOptions.create().setData(blogPostData));
        assertThat(apiResponse).isOK();
    }

    @Test
    @DisplayName("should delete a blog post")
    public void deleteABlogPost() {
        APIResponse apiResponse = this.requestContext.delete(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL + Constants.id_POST);
        assertThat(apiResponse).isOK();
    }


}
