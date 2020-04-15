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
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class ProductViewPage {
    public AppiumDriver<MobileElement> driver;
    public ProductViewPage(AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void assertDataAndclickBuyNow(TestData testData) {
        //Wait until the WebView has been loaded
        String xpath1 = "//android.webkit.WebView[@class='android.webkit.WebView']";
        WebDriverWait wait = new WebDriverWait(driver,30);                    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));

        //first we touch the web view to show the inner native components        
        int x = testData.deviceWidth / 2;
        int y = testData.deviceHeight - 1000;
        int y1 = y-100;        
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x,y))
        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
        .moveTo(PointOption.point(x, y1))
        .release()
        .perform();     
        
        //find the Buy Now button if it exists
        MobileElement ele = null;
        try {
            String xpath = "//android.view.View[@resource-id='buyNowCheckout']";            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
            ele = driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            //Buy now button not on this product. Exit test with fail.
            assertEquals("ERROR: RND selected product cannot be bought now", "");
        }
                
        //Scroll down until the Buy Now button comes into view
        Point valueBuyNowButton = null;
        int prodY;
        x = testData.deviceWidth / 2;
        y = testData.deviceHeight - 1000;
        y1 = y-400;
        for (int q = 0 ; q < 20 ; q++) {
            action = new TouchAction(driver);
            action.press(PointOption.point(x,y))
            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
            .moveTo(PointOption.point(x, y1)).release().perform();
            valueBuyNowButton = ele.getLocation();
            prodY = valueBuyNowButton.getY();
            if (prodY < 2000) {
                System.out.println("BUTTON IS IN VIEW");
                q = 20;
            }            
        }

        //VerifyData
        xpath1 = "//android.view.View[@resource-id='priceblock_ourprice']";
        String productPrice = driver.findElement(By.xpath(xpath1)).getText();
        System.out.println("productPrice = " + productPrice);
        assertTrue(testData.productDetails.contains(productPrice));

        //Click the Buy Now button
        String xpath = "//android.view.View[@resource-id='buyNowCheckout']";            
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        ele = driver.findElement(By.xpath(xpath));
        ele.click();
    }
}
