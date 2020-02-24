package com.MachetalkLiver;

import com.CommonFunctions.AllowPlugin;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

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


//    @Test
//    public void aw() throws FindFailed {
//        Screen s = new Screen();
//
//        Pattern allowImg = new Pattern("Desktop:\\chrome.png");
//
//        s.wait(allowImg, 2000);
//        s.click();
//        s.click();
//    }


//    @AfterClass
//    public void afterTest() {
//        driver.quit();
//    }

    @Test
    public void doLogin() throws InterruptedException, FindFailed {

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

        System.out.println("Location of the confirm button.");
        System.out.println("Size: "+driver.findElement(By.id("js-configCoverHide")).getSize());
        System.out.println("Location: "+driver.findElement(By.id("js-configCoverHide")).getLocation());
        driver.findElement(By.id("js-configCoverHide")).click();//confirm broadcast

        System.out.println("Location of the live viewer.");
        System.out.println("Size: "+driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize());
        System.out.println("Location: "+driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getLocation());

        double mult = (double) driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize().width  * 0.485084;
        double mult2 = (double) driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize().height * 0.612765;

        System.out.print("Height: "+ mult);
        System.out.print("Height:"+ mult2);
        System.out.print("Height: "+ (int)mult);
        System.out.print("Height:"+ (int)mult2);
        Actions clicker = new Actions(driver);
//        clicker.moveToElement(driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]"))).moveByOffset((int)mult, (int)mult2).click().perform();
        int test = 0;
        int test1 = 1;
//        while (test < 470){
//            clicker.moveToElement(driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]"))).moveByOffset((int)mult, test).click().perform();
//            System.out.println(test1++);
//            test++;
//        }
//        driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).
//        WebElement ele = driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]"));
//        Actions builder = new Actions(driver);
//        builder.moveToElement(ele, (int)mult, (int)mult2).click().build().perform();



        Thread.sleep(3000);
        Screen s = new Screen();

        Pattern allowImg = new Pattern("allow.PNG");

        s.click(allowImg);
        s.click(allowImg);

    }

//    @Test
//    public void doTheCoreFunctionalities() throws InterruptedException {
//
//
//
//
//    }

}
