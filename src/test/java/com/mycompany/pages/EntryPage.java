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
public class EntryPage {
    public AppiumDriver<MobileElement> driver;

    public EntryPage(AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void assertPage(TestData testData) {
        //Check to ensure the initial search text view contains "What are you looking for?"
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.EditText[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        String testText = driver.findElement(By.xpath(xpath)).getText();
        assertEquals(testData.data.get("EntryPage_Assert_Text"), testText);
    }
    public void clickHamburgerMenu() {
        //Click the hamburger menu on the entry page
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.ImageView[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();
    }
}
