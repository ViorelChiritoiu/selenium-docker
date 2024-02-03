package com.example.pages.flightreservation;

import com.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightconfirmationPage extends AbstractPage {

    public static final Logger log = LoggerFactory.getLogger(FlightconfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public FlightconfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(flightConfirmationElement));
        return flightConfirmationElement.isDisplayed();
    }

    public String getPrice() {
        String confirmation = flightConfirmationElement.getText();
        String price = totalPriceElement.getText();
        log.info("Flight Confirmation#: {}", confirmation);
        log.info("Total price#: {}", price);
        return price;
    }
}
