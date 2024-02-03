package com.example.tests.flightreservation;

import com.example.pages.flightreservation.*;
import com.example.tests.AbstractTest;
import com.example.tests.flightreservation.model.FlightReservationTestData;
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

public class FlightReservationTest extends AbstractTest {

    private String noOfPassengers;
    private String expectedPrice;
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.getFirstName(), testData.getLastName());
        registrationPage.enterUserCredentials(testData.getEmail(), testData.getPassword());
        registrationPage.enterAddress(testData.getStreet(), testData.getCity(), testData.getZip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        assertTrue(registrationConfirmationPage.isAt());
        assertEquals(registrationConfirmationPage.getFirstName(), testData.getFirstName());
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.getPassengersCount());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectionTest() {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationconfirmationTest() {
        FlightconfirmationPage flightconfirmationPage = new FlightconfirmationPage(driver);
        assertTrue(flightconfirmationPage.isAt());
        assertEquals(flightconfirmationPage.getPrice(), testData.getExpectedPrice());
    }
}
