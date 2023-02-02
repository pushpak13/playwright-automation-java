package org.playwright.utils;

import java.io.File;

public final class Constants {

    //Browser
    public static String browser_Name = "Chromium";

    //HelloPlaywright
    public static String msg_Playwright = "Hello Playwright!";

    //SauceDemo
    public static String title_SauceDemo = "Swag Labs";
    public static String sauceDemo_Username = "standard_user";
    public static String sauceDemo_Password = "secret_sauce";
    public static String homePage_UrlText = "inventory";

    public static String homePage_SandBoxUrlText = "sandbox-automation";

    //Sandbox-Modals
    public static String title_ModalsPage = "Modals | automateNow";

    //Sandbox-Popup
    public static String title_PopupPage = "Popups | automateNow";
    public static String msg_AlertPopup = "Hi there, pal!";
    public static String cancelMsg_ConfirmPopup = "Cancel it is!";
    public static String okMsg_ConfirmPopup = "OK it is!";
    public static String text_PromptPopup = "Playwright";
    public static String msg_PromptPopup = "Nice to meet you, Playwright!";
    public static String cancelMsg_PromptPopup = "Fine, be that way...";

    //Sandbox-Sliders
    public static String title_SliderPage = "Slider | automateNow";
    public static String slide_Key = "ArrowRight";
    public static String slider_Value = "35";

    //Sandbox-Hover
    public static String title_HoverPage = "Hover | automateNow";
    public static String text_Hover = "You did it!";

    //Sandbox-Tables
    public static String title_TablesPage = "Tables | automateNow";
    public static String tables_SearchText = "Ind";

    //Sandbox-Accordion
    public static String link_Accordion = "/accordions/";
    public static String title_AccordionPage = "Accordions | automateNow";
    public static String text_clickAccordion = "Click to see more";
    public static String text_AccordionItem = "This is an accordion item.";

    //API Validation
    public static String headers_Status = "Accept";
    public static String headers_ContentType = "application/json";
    public static String id_POST = "/100";

    //Sandbox-Broken Images
    public static String title_BrokenImagesPage = "Broken Images | automateNow";
    public static String text_SRC = "src";
    public static String text_HTTP = "http";

    //Sandbox-Calender
    public static String title_CalendarPage = "Calendars | automateNow";
    public static String format_Date = "MMMM DD, YYYY";
    public static int count_Days = 1;
    public static String key_Press = "Tab";
    public static String text_Submit = "Submit";

    //Sandbox-Form Fields
    public static String link_FormFields = "/form-fields/";
    public static String label_Name = "Name(required)";


    //Sandbox-Search Box
    public static String searchPage_UrlText = "search-box";
    public static String text_Search = "Cypress";

    //File-Upload
    public static String urlLink_FileUpload = "FileUpload.html";
    public static String RESOURCE_PATH = "src" + File.separator + "SampleFiles" + File.separator;
    public static String FILE_PATH = RESOURCE_PATH + "apple.png";
    public static String FILE_PATH_2 = RESOURCE_PATH + "mango.jpg";
    public static String SCREENSHOT_PATH = "screenshot.png";
    public static String file_Name = "Playwright.txt";
    public static String file_Type = "text/plain";
    public static String text_Message = "Welcome to Playwright";
    public static String BUFFER_SCREENSHOT_PATH = "runtime";

    //File-Download
    public static String CHROME_SELECTOR= "a:text('chromedriver_win32.zip')";
    public static String ZIPFILE_NAME= "Test.zip";
    public static String linkText_DownloadUrl = "index.html?path=2.0";
    public static String url_DownloadedFile = "chromedriver_win32.zip";

    //MouseAction
    public static String tab_Home = "Home";
    public static String title_Training = "Training";

    //Network
    public static String predicate_Url = "**/api/fetch_data";
    public static String tab_Reviews = "Reviews";
    public static String text_ReviewsPage = "What people are saying";

    //Emulations
    public static String txt_Notification = "notifications";
    public static String title_Sandbox = "Training";

    //IFrames
    public static String link_Frames = "/frames-and-windows/";
    public static String text_FrameHeader = "Below is an iFrame.";
    public static String link_HomePage = "Trainings";
    public static String text_ListItems = "Performance Testing";

    //Element Handles
    public static String route_FramesLink = "frames";
    public static String route_ButtonsLink = "buttons";
    public static String text_ClickMe = "Click Me";
    public static String text_DynamicClickMsg = "You have done a dynamic click";
    public static String text_DoubleClickMsg = "You have done a double click";
    public static String route_RegisterLink = "Register.html";







}
