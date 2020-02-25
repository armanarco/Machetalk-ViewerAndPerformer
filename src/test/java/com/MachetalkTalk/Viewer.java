package com.MachetalkTalk;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Viewer {

    public WebDriver driver;
    public String username = "androidviewer@gmail.com";
    public String password = "admin123";

    @Before
    public void launchBrowser(){

        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preference = new HashMap<String, Object>();

        preference.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", preference);
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--use-fake-ui-for-media-stream=1");
        driver = new ChromeDriver(options);

    }

    @Test
    public void doLogin() throws InterruptedException {

        driver.get("https://dev-front.machetalk.jp/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@ng-click,'openLoginModal();')]")).click();
        //login
        driver.findElement(By.xpath("//*[contains(@name,'login_mail')]")).sendKeys("asdasd");
        driver.findElement(By.xpath("//*[contains(@name,'login_password')]")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id=\"validate\"]/ul/li/button")).click();
//        driver.findElement(By.
    }

}
