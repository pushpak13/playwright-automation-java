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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class APIValidationSpec {

    APIRequestContext requestContext;
    Map<String, Object> blogPostData = new HashMap();

    @BeforeEach
    public void setup() {
        Map<String, String> headers = new HashMap<>();
        headers.putIfAbsent(Constants.headers_Status, Constants.headers_ContentType);
        requestContext = Playwright.create().request().newContext(new APIRequest.NewContextOptions().setExtraHTTPHeaders(headers));
        blogPostData.put("userId", 1);
        blogPostData.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        blogPostData.put("body", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
    }

    @AfterEach
    public void breakup() {
        requestContext = null;
        blogPostData.clear();
    }

    @Test
    @DisplayName("should get all the posts")
    public void getAllBlogPosts() {
        APIResponse apiResponse = this.requestContext.get(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL);
        assertThat(apiResponse).isOK();
        List<BlogPosts> parsedBlogPosts = MapperUtility.getPostsObjectFromJson(apiResponse);
        Assertions.assertTrue(parsedBlogPosts.size() > 0);
    }

    @Test
    @DisplayName("should create a blog post")
    public void createABlogPost() {
        APIResponse apiResponse = this.requestContext.post(EnvConfigs.JSON_URL + EnvConfigs.POSTS_URL, RequestOptions.create().setData(blogPostData));
        assertThat(apiResponse).isOK();
    }

    @Test
    @DisplayName("should update a blog post")
    public void updateABlogPost() {
        blogPostData.replace("userId", 10);
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
