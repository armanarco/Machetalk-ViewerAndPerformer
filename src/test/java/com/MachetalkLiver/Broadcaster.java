package com.MachetalkLiver;

import com.CommonFunctions.AllowPlugin;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@FixMethodOrder(MethodSorters.JVM)
public class Broadcaster {
    public WebDriver driver;
    public JavascriptExecutor js;
    public Random rand = new Random();
    public String randomName;
    public String randomUsername;
    public String randomPassword;

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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        randomName = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10);
        randomUsername = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10) + "@gmail.com";
        randomPassword = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10);


    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }


//    @AfterClass
//    public void afterTest() {
//        driver.quit();
//    }

    @Test
    public void functionalityAutomation() throws InterruptedException, FindFailed {

        //allow flash,microphone, camera
        String url = "https://dev-front.machetalk.jp/liver/";
        AllowPlugin._base_url(url);
        driver.get(String.format("chrome://settings/content/siteDetails?site=%s",url));
        AllowPlugin.flash(driver);
        AllowPlugin.camera(driver);
        AllowPlugin.microphone(driver);
        Thread.sleep(5000);

        Thread.sleep(500);
        //go to website
        driver.get("https://dev-front.machetalk.jp/liver/login/");
        driver.manage().window().maximize();

        //Registration
        driver.findElement(By.xpath("//*[contains(@href,'/liver/register/mail/')]")).click();
        driver.findElement(By.xpath("//*[contains(@name,'register_mail')]")).sendKeys(randomUsername);
        driver.findElement(By.xpath("//*[contains(@name,'register_password')]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("//*[contains(@name,'register_password_conf')]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("//*[@id=\"validate\"]/ul/li/button")).click();//confirm button
        Thread.sleep(2000);

        //Registration page2
        driver.findElement(By.xpath("//*[@id=\"validate\"]/dl/dd/label/input")).sendKeys(randomName);
        driver.findElement(By.id("btnProfileImgUpdate")).click();
        Thread.sleep(2000);

        //daily bonus
        driver.findElement(By.id("doGetBonus")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"login_bonus_get\"]/div[1]/div/div[2]/ul/li/button")).click();
        Thread.sleep(1000);

        //Logout
        driver.findElement(By.xpath("//*[contains(@href,'http://dev-front.machetalk.jp/liver/logout/')]")).click();
        Thread.sleep(2000);

        //login
        driver.findElement(By.xpath("//*[contains(@href,'/liver/login/mail/')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@name,'login_mail')]")).sendKeys("pccaster@gmail.com");
        driver.findElement(By.xpath("//*[contains(@name,'login_password')]")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"validate\"]/ul/li/button")).click();
        Thread.sleep(1000);
        try{
            driver.findElement(By.id("doGetBonus")).click();
            driver.findElement(By.xpath("//*[@id=\"login_bonus_get\"]/div[1]/div/div[2]/ul/li/button")).click();
        }catch (Exception e){

        }finally {
            //edit profile
            driver.findElement(By.xpath("//*[contains(@href,'http://dev-front.machetalk.jp/liver/profile/edit/')]")).click();
            driver.findElement(By.xpath("//*[contains(@name,'register_nickname')]")).sendKeys(randomName);
            Select age = new Select(driver.findElement(By.xpath("//*[contains(@name,'register_age')]")));
            age.selectByIndex(rand.nextInt(23));

            Select area = new Select(driver.findElement(By.xpath("//*[contains(@name,'register_area_1')]")));
            area.selectByValue("1");

            js.executeScript("window.scrollBy(0,1000)");//scroll down to locate other elements

            Select area2 = new Select(driver.findElement(By.xpath("//*[contains(@name,'register_area_2')]")));
            area2.selectByIndex(rand.nextInt(46));

            Select job = new Select(driver.findElement(By.xpath("//*[contains(@name,'register_job')]")));
            job.selectByIndex(rand.nextInt(42) + 1);

            driver.findElement(By.id("count_text")).sendKeys(generateRandomChars("ABCDEFGHIJKLMOP",50));
            driver.findElement(By.id("btn_submit_profileUpdate")).click();

            //broadcast
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

            //x and y coordinates method not working.
//        System.out.println("Location of the live viewer.");
//        System.out.println("Size: "+driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize());
//        System.out.println("Location: "+driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getLocation());
//        WebElement ele = driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]"));
//        double mult = (double) driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize().width  * 0.485084;
//        double mult2 = (double) driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")).getSize().height * 0.612765;
//
//        System.out.print("Height: "+ mult);
//        System.out.print("Height:"+ mult2);
//        System.out.print("Height: "+ (int)mult);
//        System.out.print("Height:"+ (int)mult2);

            //sikuli framework not working. [error] ImagePath: find: not there: allow.png
            Thread.sleep(5000);
            Actions clicker = new Actions(driver);
            Thread.sleep(5000);
            Screen s = new Screen();
            Pattern imgAllow = new Pattern("allow.png");
            ImagePath.add("allow.png");
            s.wait(imgAllow, 5000);
            s.click();
            s.click();

//        clicker.moveToElement(driver.findElement(By.xpath("//*[@id=\"live_caster_ready\"]")))
//                .moveByOffset((int)mult, (int)mult2).click().perform();
        }
    }
}
