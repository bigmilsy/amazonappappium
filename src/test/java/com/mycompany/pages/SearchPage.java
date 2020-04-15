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
    public void swipeUpToShowResults() {
        //We swipe up to shwo the native elements within the web view
        Point value = null;
        String xpath1 = "//android.webkit.WebView[@class='android.webkit.WebView']";
        WebDriverWait wait = new WebDriverWait(driver,30);                    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
        value = driver.findElement(By.xpath(xpath1)).getLocation();
        int x = value.x;
        int y = value.y;
        int y1 = y-100;
        
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x,y))
        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000)))
        .moveTo(PointOption.point(x, y1))
        .release()
        .perform();        
    }
    public void selectAndClickProduct() {
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.view.View[@resource-id='search']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
        MobileElement ele1 = driver.findElement(By.xpath(xpath));
        String xpath2 = "//android.view.View[@class='android.view.View']";
        List<MobileElement> list1 = ele1.findElements(By.xpath(xpath2));
        
        System.out.println("SIZE: " + list1.size());
        
        Iterator it = list1.iterator();
        MobileElement ele;
        String tempStr = new String();
        List<MobileElement> list2 = new LinkedList<>();
        while(it.hasNext()) {
            ele = (MobileElement)it.next();
            tempStr = ele.getText();
            if (tempStr.contains("TV")) {
                list2.add(ele);
            }
        }
        /*it = list2.iterator();
        while (it.hasNext()) {
            ele = (MobileElement)it.next();
            System.out.println(ele.getText());
        }*/
        
        Random rand = new Random();
        int randInt = rand.nextInt(list2.size());
        ele = (MobileElement)list2.get(randInt);
        System.out.println(ele.getText());
        System.out.println(ele.getAttribute("clickable"));

        Point valueProduct = null;
        valueProduct = ele.getLocation();
        System.out.println("Prod X: " + valueProduct.getX());
        System.out.println("Prod Y: " + valueProduct.getY());

        Point webViewValue = null;
        String xpath1 = "//android.webkit.WebView[@class='android.webkit.WebView']";
        webViewValue = driver.findElement(By.xpath(xpath1)).getLocation();
        System.out.println("Web X: " + webViewValue.getX());
        System.out.println("Web Y: " + webViewValue.getY());        

        int webX = webViewValue.getX();
        int webY = webViewValue.getY();
        int prodX = valueProduct.getX();
        int prodY = valueProduct.getY();
       
        Point value = null;
        value = driver.findElement(By.xpath(xpath1)).getLocation();
        int x = value.x;
        int y = value.y;
        int y1 = y-500;
        for (int q = 0 ; q < 20 ; q++) {
            TouchAction action = new TouchAction(driver);
            action.press(PointOption.point(x,y))
            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
            .moveTo(PointOption.point(x, y1))
            .release()
            .perform();

            valueProduct = ele.getLocation();
            System.out.println("Clickable: " + ele.getAttribute("focusable"));
            System.out.println("Prod Y: " + valueProduct.getY());            
            prodY = valueProduct.getY();
            if (prodY < 2000) {
                System.out.println("PRODUCT IS IN VIEW");
                q = 20;
            }
            
        }

        ele.click();                    
        
/*        Point value = null;
        String xpath1 = "//android.webkit.WebView[@class='android.webkit.WebView']";
        value = driver.findElement(By.xpath(xpath1)).getLocation();
        int x = value.x;
        int y = value.y;
        int y1 = y-500;
        for (int q = 0 ; q < 50 ; q++) {
            if (ele.isDisplayed() == false) {
                TouchAction action = new TouchAction(driver);
                action.press(PointOption.point(x,y))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000)))
                .moveTo(PointOption.point(x, y1))
                .release()
                .perform();                                                            
                System.out.println("NOT FOUND");
            } else {
                System.out.println("FOUND!!!");
                ele.click();
            }

        }*/
        
        

        
        /*for (int x = 0 ; x < 10 ; x++) {
            if (ele.isDisplayed() == false) {

            } else {
                System.out.println("IS DISPLAYED");
            }
        }*/
        


        //Point value = ele.getLocation();
        

    }
}
