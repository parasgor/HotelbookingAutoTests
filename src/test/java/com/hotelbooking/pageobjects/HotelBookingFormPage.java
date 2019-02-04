package com.hotelbooking.pageobjects;


import com.hotelbooking.core.WebElementHelper;
import com.hotelbooking.config.ApplicationProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.hotelbooking.config.ApplicationProperties.ApplicationProperty.APP_URL;

public class HotelBookingFormPage extends Page {

    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstName;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement surname;

    @FindBy(how = How.ID, using = "totalprice")
    private WebElement totalprice;

    @FindBy(how = How.ID, using = "checkin")
    private WebElement checkin;

    @FindBy(how = How.ID, using = "checkout")
    private WebElement checkout;

    @FindBy(how = How.ID, using = "depositpaid")
    private WebElement Select;
    Select depositpaid = new Select(Select);




    public HotelBookingFormPage(){
        waitUntilLoaded();
    }

    public static HotelBookingFormPage navigate() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(APP_URL));
        return new HotelBookingFormPage();
    }

    public void settingFirstName(String firstNameData) {
        WebElementHelper.sendKeys(firstName, firstNameData);
    }


    public void settingSurName(String surnameData) {
        WebElementHelper.sendKeys(surname, surnameData);
    }

    public void settingTotalPrice(String totalPrice) {
        WebElementHelper.sendKeys(totalprice, totalPrice);
    }

    public void settingCheckinDate(String checkinDate) {
        WebElementHelper.sendKeys(checkin, checkinDate);
    }

    public void settingCheckoutDate(String checkoutDate) {
        WebElementHelper.sendKeys(checkout, checkoutDate);
    }

    public void clickOnSaveButton(){
        WebElementHelper.click(By.cssSelector("input[value=' Save ']"));
    }

    public List<WebElement> getAllBookings(){
        return WebElementHelper.getListOfElements(By.cssSelector("div[id='bookings'] div[class='row']"));
    }

    public String getFirstNameFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(0).getText();
    }

    public String getSurNameFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(1).getText();
    }
    public String getTotalPriceFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(2).getText();
    }

    public String getDepositFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(3).getText();
    }
    public String getChekInDateFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(4).getText();
    }

    public String getCheckOutDateFromBooking(WebElement bookingRow){
        return bookingRow.findElements(By.cssSelector("div")).get(5).getText();
    }

    public void clickDeleteButton(WebElement bookingRow){
        WebElementHelper.click(bookingRow.findElement(By.cssSelector("input[value='Delete']")));
    }


    public void selectDepositPaidOrNot(String paidOrNotStatus){
        depositpaid.selectByVisibleText(paidOrNotStatus.toLowerCase());
    }

    @Override
    protected WebElement getControlElement() {
        return firstName;
    }
}