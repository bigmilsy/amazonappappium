/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class MoviesAndTvPage {
    public AppiumDriver<MobileElement> driver;
    public MoviesAndTvPage(AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void clickSearchButton() {
        //Click the search icon in the top menu
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.ImageButton[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_search']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();                
    }
}
