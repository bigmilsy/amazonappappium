/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amazonpurchase;

import com.mycompany.pages.EntryPage;
import com.mycompany.pages.HamburgerMenu;
import com.mycompany.pages.MoviesAndTvPage;
import com.mycompany.pages.SearchPage;
import com.mycompany.pages.SigninPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author dmiller
 */
public class EntryTest {
    public DesiredCapabilities caps;
    public TestData testData;    
    public EntryTest() {
        testData = new TestData();
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
        caps.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        caps.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
        caps.setCapability("noReset", "true");
        caps.setCapability("automationName", "UiAutomator1");
        //caps.setCapability("autoWebview", "true");
        //caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
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
            EntryPage entryPage = new EntryPage(driver);
            entryPage.assertPage(testData);
            entryPage.clickHamburgerMenu();
        
            HamburgerMenu hamburgerMenu = new HamburgerMenu(driver);
            hamburgerMenu.assertPage(testData);
            hamburgerMenu.clickShopByDepartment();
            hamburgerMenu.swipeDownFromElectronics();
            hamburgerMenu.clickMoviesAndTv();

            MoviesAndTvPage moviesAndTvPage = new MoviesAndTvPage(driver);
            moviesAndTvPage.clickSearchButton();

            SearchPage searchPage = new SearchPage(driver);
            searchPage.typeSearchCriteria(testData);
            searchPage.swipeUpToShowResults();
            searchPage.selectAndClickProduct();
            
            //moviesAndTvPage.clickHamburgerMenu();
            //moviesAndTvPage.clickSignIn();
            
            
            //SigninPage signinPage = new SigninPage(driver);
            //signinPage.assertPage(testData);
            //close app
            //driver.closeApp();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }                     
    }
    
}
