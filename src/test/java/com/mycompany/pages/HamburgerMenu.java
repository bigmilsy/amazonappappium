/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pages;

import com.mycompany.amazonpurchase.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
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
        //check to ensure that no users are currently logged in.
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/gno_greeting_text_view']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        String testText = driver.findElement(By.xpath(xpath)).getText();
        assertEquals(testData.data.get("HamburgerMenu_Assert_Text"), testText);        
    }
    public void clickSignIn() {
        //Click the signin option
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/gno_greeting_text_view']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();        
    }
}
