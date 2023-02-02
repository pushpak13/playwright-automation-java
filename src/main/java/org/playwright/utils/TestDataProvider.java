package org.playwright.utils;


import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name="PostData")
    public Object[][] postData() {
        Object[][] obj = new Object[][] {
                {"userID", 1},
                {"title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"},
                {"body", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"}
        };
        return obj;

    }

    @DataProvider(name="PutData")
    public Object[] putData() {
        Object[] obj = new Object[] {
                "userID", 10
        };
        return obj;

    }

}
