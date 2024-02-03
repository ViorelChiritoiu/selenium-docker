package com.example.pages.flightreservation;

import com.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;


    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
        searchFlightsButton.click();
    }
}
