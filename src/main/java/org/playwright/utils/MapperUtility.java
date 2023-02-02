package org.playwright.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.playwright.models.BlogPosts;

import java.io.IOException;
import java.util.List;

public class MapperUtility {
     ObjectMapper objectMapper = new ObjectMapper();

    public List<BlogPosts> getPostsObjectFromJson(APIResponse response) {

        try {
            String toBeParsed = response.text();
            List<BlogPosts> objectToReturn = objectMapper.readValue(toBeParsed, new TypeReference<>() {
            });
            return objectToReturn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
