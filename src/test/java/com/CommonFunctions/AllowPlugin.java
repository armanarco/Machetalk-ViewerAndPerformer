package com.CommonFunctions;

import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

public class AllowPlugin {

    private static WebElement _shadow_root(WebDriver driver, WebElement element){
        return (WebElement)((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
    }

    public static String _base_url(String url){
        if (url.isEmpty()){
            return url;
        }

        try {
            URL urls = new URL(url);
            return String.format("%s://%s",urls.getProtocol(),urls.getHost());
        }catch (Exception e){
            return url;
        }
    }

    public static void flash(WebDriver driver) {
        WebElement webele_settings = _shadow_root(driver,(((ChromeDriver)driver).findElementByTagName("settings-ui")));
        WebElement webele_container = webele_settings.findElement(By.id("container"));
        WebElement webele_main = _shadow_root(driver,webele_container.findElement(By.id("main")));
        WebElement showing_subpage = _shadow_root(driver,webele_main.findElement(By.className("showing-subpage")));
        WebElement advancedPage = showing_subpage.findElement(By.id("advancedPage"));
        WebElement settings_privacy_page = _shadow_root(driver,advancedPage.findElement(By.tagName("settings-privacy-page")));
        WebElement pages = settings_privacy_page.findElement(By.id("pages"));
        WebElement settings_subpage = pages.findElement(By.tagName("settings-subpage"));
        WebElement site_details = _shadow_root(driver,settings_subpage.findElement(By.tagName("site-details")));
        WebElement plugins = _shadow_root(driver,site_details.findElement(By.id("plugins")));
        WebElement permission = plugins.findElement(By.id("permission"));
        Select sel = new Select(permission);
        sel.selectByValue("allow");
    }

    public static void camera(WebDriver driver) {
        WebElement webele_settings = _shadow_root(driver,(((ChromeDriver)driver).findElementByTagName("settings-ui")));
        WebElement webele_container = webele_settings.findElement(By.id("container"));
        WebElement webele_main = _shadow_root(driver,webele_container.findElement(By.id("main")));
        WebElement showing_subpage = _shadow_root(driver,webele_main.findElement(By.className("showing-subpage")));
        WebElement advancedPage = showing_subpage.findElement(By.id("advancedPage"));
        WebElement settings_privacy_page = _shadow_root(driver,advancedPage.findElement(By.tagName("settings-privacy-page")));
        WebElement pages = settings_privacy_page.findElement(By.id("pages"));
        WebElement settings_subpage = pages.findElement(By.tagName("settings-subpage"));
        WebElement site_details = _shadow_root(driver,settings_subpage.findElement(By.tagName("site-details")));
        WebElement plugins = _shadow_root(driver,site_details.findElement(By.id("camera")));
        WebElement permission = plugins.findElement(By.id("permission"));
        Select sel = new Select(permission);
        sel.selectByValue("allow");
    }

    public static void microphone(WebDriver driver) {
        WebElement webele_settings = _shadow_root(driver,(((ChromeDriver)driver).findElementByTagName("settings-ui")));
        WebElement webele_container = webele_settings.findElement(By.id("container"));
        WebElement webele_main = _shadow_root(driver,webele_container.findElement(By.id("main")));
        WebElement showing_subpage = _shadow_root(driver,webele_main.findElement(By.className("showing-subpage")));
        WebElement advancedPage = showing_subpage.findElement(By.id("advancedPage"));
        WebElement settings_privacy_page = _shadow_root(driver,advancedPage.findElement(By.tagName("settings-privacy-page")));
        WebElement pages = settings_privacy_page.findElement(By.id("pages"));
        WebElement settings_subpage = pages.findElement(By.tagName("settings-subpage"));
        WebElement site_details = _shadow_root(driver,settings_subpage.findElement(By.tagName("site-details")));
        WebElement plugins = _shadow_root(driver,site_details.findElement(By.id("mic")));
        WebElement permission = plugins.findElement(By.id("permission"));
        Select sel = new Select(permission);
        sel.selectByValue("allow");
    }

}
