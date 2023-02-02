package org.playwright.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager {

    static ExtentReports reports;
    public static String screenshootFolderPath;
    public static ExtentReports getReport(String basePath){
        if(reports==null){
            Date date=new Date();
            String reportsFolderName= date.toString().replaceAll(":","_");
            String path = basePath+reportsFolderName;
            //screenshootFolderPath=path+"/screenshots/";
            screenshootFolderPath= System.getProperty("user.dir")+"/test/";
            File file= new File(screenshootFolderPath);
            file.mkdirs();
            ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
            sparkReporter.config().setReportName("Test Report "+reportsFolderName);
            sparkReporter.config().setDocumentTitle("Extent report using Playwright java");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");
            reports=new ExtentReports();
            reports.attachReporter(sparkReporter);
        }
        return reports;

    }
}
