/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pages;

import com.mycompany.amazonpurchase.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class SigninPage {
    public AppiumDriver<MobileElement> driver;
    public SigninPage (AppiumDriver<MobileElement> drv) {
        this.driver = drv;
    }
    public void assertPage(TestData testData) {
        //check to ensure we see the right headers "Create account. New to Amazon?"
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.view.View[@resource-id='register_accordion_header']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        String testText = driver.findElement(By.xpath(xpath)).getText();
        assertEquals(testData.data.get("SigninPage_Assert_Text"), testText);        
    }
    public void typeUsernameClickContinue(TestData testData) {
        //first, touch page to ensure native elements are loaded
        WebDriverWait wait = new WebDriverWait(driver,30);            
        String xpath = "//android.view.View[@text='Welcome']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();
        
        //type in the username
        xpath = "//android.widget.EditText[@resource-id='ap_email_login']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).sendKeys(testData.data.get("SigninPage_TestData_Username"));
        
        //click continue button
        xpath = "//android.widget.Button[@resource-id='continue']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).click();
    }
    public void typePasswordClickSignIn(TestData testData) {
        //type in the password
        WebDriverWait wait = new WebDriverWait(driver,30); 
        String xpath = "//android.widget.EditText[@resource-id='ap_password']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        driver.findElement(By.xpath(xpath)).sendKeys(testData.data.get("SigninPage_TestData_Password"));

        xpath = "//android.widget.Button[@resource-id='signInSubmit']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));            
        //END OF TEST HERE....don't actually click login
        //driver.findElement(By.xpath(xpath)).click();        
    }
    
}
