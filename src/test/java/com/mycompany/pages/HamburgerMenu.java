/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pages;

import com.mycompany.amazonpurchase.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class HamburgerMenu {
    public AppiumDriver<MobileElement> driver;
    public HamburgerMenu(AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void assertPage(TestData testData) {
        //check to ensure we see "Hello. Sign In"
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/gno_greeting_text_view']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        String testText = driver.findElement(By.xpath(xpath)).getText();
        assertEquals(testData.data.get("HamburgerMenu_Assert_Text"), testText);        
    }
    public void clickShopByDepartment() {
        //Click the Shop by Department link
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.TextView[@text='Shop by Department']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();     
    }
    public void swipeDownFromElectronics() {
        //We swipe up to ensure the Movies & TV option is visble.
        Point value = null;
        String xpath1 = "//android.widget.TextView[@text='Electronics']";
        WebDriverWait wait = new WebDriverWait(driver,30);                    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
        value = driver.findElement(By.xpath(xpath1)).getLocation();
        int x = value.x;
        int y = value.y;
        int y1 = y-1800;
        
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x,y))
        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000)))
        .moveTo(PointOption.point(x, y1))
        .release()
        .perform();
    }
    public void clickMoviesAndTv() {
        //Click the Movies & Tv link
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.TextView[@text='Movies & TV']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();
    }
}
