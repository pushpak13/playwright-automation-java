package org.playwright.Day6;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.utils.BrowserSetUp;
import org.playwright.utils.Constants;
import org.playwright.utils.EnvConfigs;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileDownloadTest {
    Page page;
    BrowserSetUp setUp;

    @BeforeEach
    public void setUp() {
        setUp = new BrowserSetUp();
        page = setUp.initBrowser(Constants.browser_Name);
    }

    @Test
    public void downloadFileTest(){
        page.navigate(EnvConfigs.DOWNLOAD_URL);
        Assertions.assertTrue(page.url().contains(Constants.linkText_DownloadUrl));
        Download download = page.waitForDownload(() ->{
            page.click(Constants.CHROME_SELECTOR);
        });
        Assertions.assertTrue(download.url().contains(Constants.url_DownloadedFile));
        //String path = download.page().toString();
        download.saveAs(Paths.get(Constants.ZIPFILE_NAME));
    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }
}
