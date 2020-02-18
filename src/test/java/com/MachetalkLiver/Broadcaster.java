package com.MachetalkLiver;
import Flash.FlashObjectWebDriver;
import com.CommonFunctions.AllowPlugin;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.JVM)
public class Broadcaster {
    public WebDriver driver;


    @Before
    public void launchBrowser(){

        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> preference = new HashMap<String, Object>();

        preference.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", preference);
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--use-fake-ui-for-media-stream=1");
        driver = new ChromeDriver();

    }



//    @AfterClass
//    public void afterTest() {
//        driver.quit();
//    }

    @Test
    public void doLogin() throws InterruptedException {

        //allow flash,microphone, camera
        String url = "https://dev-front.machetalk.jp/liver/";
        AllowPlugin._base_url(url);
        driver.get(String.format("chrome://settings/content/siteDetails?site=%s",url));
        AllowPlugin.flash(driver);
        AllowPlugin.camera(driver);
        AllowPlugin.microphone(driver);
        Thread.sleep(5000);

        Thread.sleep(500);
        driver.get("https://dev-front.machetalk.jp/liver/login/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(@href,'/liver/login/mail/')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@name,'login_mail')]")).sendKeys("androidcaster@gmail.com");
        driver.findElement(By.xpath("//*[contains(@name,'login_password')]")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"validate\"]/ul/li/button")).click();

        driver.get("https://dev-front.machetalk.jp/liver/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@href,'http://dev-front.machetalk.jp/liver/live/theme/')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("count_text")).sendKeys("test");
        driver.findElement(By.xpath("//*[contains(@for,'tag_3')]")).click();
        driver.findElement(By.xpath("//*[@id=\"validate\"]/ul/li/button")).click();
        Thread.sleep(8000);

        //camera
//        driver.findElement(By.xpath("//*[contains(@label,'Logitech HD Webcam C270 (046d:0825)')]")).click();
        //microphone
//        driver.findElement(By.xpath("//*[contains(@label,'Default - Microphone (HD Webcam C270) (046d:0825)')]")).click();

        driver.findElement(By.id("js-configCoverHide")).click();//confirm broadcast
        FlashObjectWebDriver flashapp = new FlashObjectWebDriver(driver, "live_caster_ready");

        Integer.parseInt(flashapp.callFlashObject("Play"));

    }

//    @Test
//    public void doTheCoreFunctionalities() throws InterruptedException {
//
//
//
//
//    }

}
