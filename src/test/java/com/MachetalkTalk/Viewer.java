package com.MachetalkTalk;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Viewer {

    public WebDriver driver;
    private String username = "androidviewer@gmail.com";
    private String password = "admin123";

    @Before
    public void launchBrowser(){

        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);

    }

    @Test
    public void doLogin() throws InterruptedException {

        driver.get("https://dev-front.machetalk.jp/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@ng-click,'openLoginModal();')]")).click();
        //login
        driver.findElement(By.xpath("//*[contains(@name,'login_mail')]")).sendKeys(username);
        driver.findElement(By.xpath("//*[contains(@name,'login_password')]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"validate_login\"]/ul/li/button")).click();

    }

}
