package com.example.pages.flightreservation;

import com.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchBtn;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
       wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchBtn));
        return goToFlightsSearchBtn.isDisplayed();
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchBtn.click();
    }

    public String getFirstName() {
        return this.firstNameElement.getText();
    }
}
