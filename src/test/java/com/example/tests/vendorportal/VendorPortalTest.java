package com.example.tests.vendorportal;

import com.example.pages.vendorportal.DashboardPage;
import com.example.pages.vendorportal.LoginPage;
import com.example.tests.AbstractTest;
import com.example.tests.vendorportal.model.VendorPortalTestData;
import com.example.util.Config;
import com.example.util.Constants;
import com.example.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VendorPortalTest extends AbstractTest {

    private  LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        assertTrue(loginPage.isAt());
        loginPage.login(testData.getUsername(), testData.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        assertTrue(dashboardPage.isAt());

        //finance metrics
        assertEquals(dashboardPage.getMonthlyEarning(), testData.getMonthlyEarning());
        assertEquals(dashboardPage.getAnnualEarning(), testData.getAnnualEarning());
        assertEquals(dashboardPage.getProfitMargin(), testData.getProfitMargin());
        assertEquals(dashboardPage.getAvailableInventory(), testData.getAvailableInventory());

        dashboardPage.searchOrderHistoryBy(testData.getSearchKeyword());
        assertEquals(dashboardPage.getSearchResultsCount(), testData.getSearchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
        assertTrue(loginPage.isAt());
    }
}
