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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class SearchPage {
    public AppiumDriver<MobileElement> driver;
    public SearchPage(AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void typeSearchCriteria(TestData testData) {
        //
        //Enter in the search criteria as stated in the Assignment Question
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.widget.EditText[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).sendKeys(testData.data.get("SearchPage_TestData_SearchCriteria") + "\n");               
    }
    public void swipeUpToShowResults(TestData testData) {
        //We swipe up to show the native elements within the web view
//        Point value = null;
        String xpath1 = "//android.webkit.WebView[@class='android.webkit.WebView']";
        WebDriverWait wait = new WebDriverWait(driver,30);                    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));

        int x = testData.deviceWidth / 2;
        int y = testData.deviceHeight - 1000;
        int y1 = y - 100;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x,y))
        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000)))
        .moveTo(PointOption.point(x, y1))
        .release()
        .perform();

    }
    public TestData selectAndClickProduct(TestData testData) {

        //Select the parent View with product list inside
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.view.View[@resource-id='search']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
        MobileElement ele1 = driver.findElement(By.xpath(xpath));

        //Return back a list of all products.
        String xpath2 = "//android.view.View[@class='android.view.View']";
        List<MobileElement> list1 = ele1.findElements(By.xpath(xpath2));        

        //Select on products with the word 'TV' inside
        Iterator it = list1.iterator();
        MobileElement ele;
        MobileElement ele2;
        String tempStr = new String();
        List<MobileElement> list2 = new LinkedList<>();
        while(it.hasNext()) {
            ele = (MobileElement)it.next();
            tempStr = ele.getText();
            if (tempStr.contains("TV") && tempStr.contains("$")) {
                list2.add(ele);
            }
        }

        //Select a random product from the list
        Random rand = new Random();
        int randInt = rand.nextInt(list2.size());
        ele = (MobileElement)list2.get(randInt);
        
        //gather up product data to verify on next screens
        testData.productDetails = ele.getText();
        System.out.println("Product Details: " + testData.productDetails);
        
        
        //Scroll down the product list until the selected product comes into view
        Point valueProduct = null;
        int prodY;
        int x = testData.deviceWidth / 2;
        int y = testData.deviceHeight - 1000;
        int y1 = y-500;
        for (int q = 0 ; q < 20 ; q++) {
            TouchAction action = new TouchAction(driver);
            action.press(PointOption.point(x,y))
            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
            .moveTo(PointOption.point(x, y1)).release().perform();            
            valueProduct = ele.getLocation();
            prodY = valueProduct.getY();
            if (prodY < 2000) {
                System.out.println("PRODUCT IS IN VIEW");
                q = 20;
            }
            
        }

        //click the product
        ele.click();
        return testData;
    }
}
