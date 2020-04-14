/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amazonpurchase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author dmiller
 */
public class EntryTest {
    public DesiredCapabilities caps;
    public EntryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //These are the main appium settings that are used to connect to both the device and the
        //app that is to be tested. 
        caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "My Phone");
        caps.setCapability("udid", "ce08171813a36e3e027e"); //Device ID from adb devices
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
//com.amazon.mShop.android.shopping/com.amazon.mShop.home.web.MShopWebGatewayActivity
        caps.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        caps.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
        caps.setCapability("noReset", "true");
        caps.setCapability("automationName", "UiAutomator1");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Entry.
     */
    @Test
    public void testMain() {
        try {            
            AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
            
        
            
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }         
    }
    
}