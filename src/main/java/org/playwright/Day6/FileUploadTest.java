package org.playwright.Day6;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import org.playwright.utils.Locators;

import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadTest {
    Page page;
    BrowserSetUp setUp;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
        page.navigate(EnvConfigs.fileUpload_Url);
        page.waitForLoadState(LoadState.LOAD);
        Assertions.assertTrue(page.url().contains(Constants.urlLink_FileUpload));
    }

    @Test
    @DisplayName("Upload Single File")
    public void test_FileUpload() throws NoSuchFileException, InterruptedException {
        page.setInputFiles(Locators.fileUpload, Paths.get(Constants.FILE_PATH) );
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(Constants.SCREENSHOT_PATH)));
    }

    @Test
    @DisplayName("Upload Multiple Files")
    public void test_MultipleFilesUpload() throws NoSuchFileException {
        page.setInputFiles(Locators.fileUpload, new Path[]{
                Paths.get(Constants.FILE_PATH), Paths.get(Constants.FILE_PATH_2)
        });
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(Constants.SCREENSHOT_PATH)));
    }

    @Test
    @DisplayName("Upload File At Runtime")
    public void test_RunTimeFileUpload() {
        page.setInputFiles(Locators.fileUpload, new FilePayload(Constants.file_Name, Constants.file_Type,
                Constants.text_Message.getBytes(StandardCharsets.UTF_8)));
        byte[] buffer = page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(Constants.BUFFER_SCREENSHOT_PATH)));

    }

    @AfterEach
    public void tearDown()
    {
        page.context().browser().close();
    }

}
