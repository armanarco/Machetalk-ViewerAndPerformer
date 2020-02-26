package com.MachetalkTalk;

import com.CommonFunctions.AllowPlugin;
import com.CommonFunctions.GenerateRandom;
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
    public String randomText;

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
        randomText = GenerateRandom.chars("ABCDEFJHIJKLMNOPQRSTUVWXYZ", 50);
    }

    @Test
    public void functionalityAutomation() throws InterruptedException {


        //allow flash,microphone, camera
        String url = "https://dev-front.machetalk.jp/liver/";
        AllowPlugin._base_url(url);
        driver.get(String.format("chrome://settings/content/siteDetails?site=%s",url));
        AllowPlugin.flash(driver);
        AllowPlugin.camera(driver);
        AllowPlugin.microphone(driver);

        driver.get("https://dev-front.machetalk.jp/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@ng-click,'openLoginModal();')]")).click();

        Thread.sleep(1000);
        login();

        Thread.sleep(2000);
        dailyBonus();

        openBroadcast();

    }

    public void login(){
        //login
        driver.findElement(By.xpath("//*[contains(@name,'login_mail')]")).sendKeys("androidviewer@gmail.com ");
        driver.findElement(By.xpath("//*[contains(@name,'login_password')]")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();
    }

    public void dailyBonus() throws InterruptedException {
        //daily bonus
        try{
            driver.findElement(By.id("doGetBonus")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"login_bonus_get\"]//button")).click();

        }catch (Exception e){
            System.out.println("Daily Bonus is already collected");
        }
        Thread.sleep(500);
    }

    public void openBroadcast() throws InterruptedException {
        boolean isInOpenBroadcast = true;
        //open broadcast
        try{
            driver.findElement(By.xpath("//*[@id=\"liveInfo\"]/div[2]/div/div/div/a")).click();



        }catch (Exception e){
            isInOpenBroadcast = false;
            System.out.println("No broadcaster present as of the moment!");
        }

        if(isInOpenBroadcast){
            Thread.sleep(5000);
            //gifting
            driver.findElement(By.xpath("//*[@id=\"giftList\"]/div[1]")).click();
            driver.findElement(By.id("inputComment")).sendKeys(randomText);
            driver.findElement(By.xpath("//*[@id=\"sendComment\"]/div[3]/button")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[contains(@ng-click,'invitePrivateModal()')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[contains(@ng-click,'requestPrivate()')]")).click();
        }
    }

}
