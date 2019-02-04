package com.hotelbooking.stepdefs;

import com.hotelbooking.pageobjects.HotelBookingFormPage;
import com.hotelbooking.util.TestDataContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import org.openqa.selenium.WebElement;

import java.util.List;
import static org.junit.Assert.fail;

public class Stepdefs extends  StepsModel
{
    private HotelBookingFormPage hotelBookingFormPage;
    private TestDataContext resourceManager;


    public Stepdefs(TestDataContext resourceManager) throws Exception {
        super(resourceManager);
    }


    public Stepdefs() {
        super();
        this.resourceManager = TestDataContext.getInstance();
    }




    @Given("^I navigate to Home page$")
    public void iNavigateToHomePage() throws Throwable {
        hotelBookingFormPage = HotelBookingFormPage.navigate();
        Thread.sleep(5000);

    }


    @When("^I enter firstname as  \"([^\"]*)\"$")
    public void iEnterFirstnameAs(String firstname) throws Throwable {
        String newFirstName = firstname.isEmpty() ? RandomStringUtils.randomAlphabetic(10) : firstname;
        hotelBookingFormPage.settingFirstName(newFirstName);
        resourceManager.setTestData("firstname", newFirstName);
    }

    @And("^I enter surname as \"([^\"]*)\"$")
    public void iEnterSurnameAs(String surname) throws Throwable {
        String newSurName = surname.isEmpty() ? RandomStringUtils.randomAlphabetic(5) : surname;
        hotelBookingFormPage.settingSurName(newSurName);
        resourceManager.setTestData("surname", newSurName);
    }


    @And("^I enter total price as \"([^\"]*)\"$")
    public void iEnterTotalPriceAs(String totalPrice) throws Throwable {
      hotelBookingFormPage.settingTotalPrice(totalPrice);
       resourceManager.setTestData("totalPrice", totalPrice);
    }

    @And("^I enter checkoutdate as \"([^\"]*)\"$")
    public void iEnterCheckoutdateAs(String checkoutDate) throws Throwable {
        hotelBookingFormPage.settingCheckoutDate(checkoutDate);
        resourceManager.setTestData("checkoutDate", checkoutDate);
    }

    @And("^I enter checkindate as \"([^\"]*)\"$")
    public void iEnterCheckindateAs(String checkinDate) throws Throwable {
        hotelBookingFormPage.settingCheckinDate(checkinDate);
        resourceManager.setTestData("checkinDate", checkinDate);
    }

    @And("^I click on the save button$")
    public void iClickOnTheSaveButton() throws Throwable {
        Thread.sleep(5000);
        hotelBookingFormPage.clickOnSaveButton();
        Thread.sleep(5000);
    }

    @Then("^I should be able to see new entry in table$")
    public void iShouldBeAbleToSeeNewEntryInTable() throws Throwable {

        String expectedFirstName = resourceManager.getTestData("firstname").toString();
        String expectedSurName = resourceManager.getTestData("surname").toString();
        String expectedTotalPrice = resourceManager.getTestData("totalPrice").toString();
        String expectedCheckInDate = resourceManager.getTestData("checkinDate").toString();
        String expectedCheckOutDate = resourceManager.getTestData("checkoutDate").toString();
        String expectedDepositStatus = resourceManager.getTestData("depositStatus").toString();

        boolean isEntryInGrid = false;
        int retryCount = 3;
        for( int counter =1; counter<=retryCount; counter++){
            isEntryInGrid = isEntryAvailInGrid(
                    expectedFirstName,expectedSurName,expectedTotalPrice, expectedDepositStatus,
                    expectedCheckInDate, expectedCheckOutDate);

            if( isEntryInGrid == false){
                System.out.println("Trying one more time to find the entry..");
                continue;
            }else {
                break;
            }

        }


            if(isEntryInGrid== false) {

                System.out.println("expected entry having below information");
                System.out.println("expectedFirstName -" + expectedFirstName);
                System.out.println("expectedSurName -" + expectedSurName);
                System.out.println("expectedTotalPrice -" + expectedTotalPrice);
                System.out.println("expectedCheckInDate -" + expectedCheckInDate);
                System.out.println("expectedCheckOutDate -" + expectedCheckOutDate);
                System.out.println("expectedDepositStatus -" + expectedDepositStatus);

                Assert.fail("Newly created entry is not available in list");
                }
    }


    @When("^I click on the delete button for the very first entry$")
    public void iSelectTheVeryFirstEntryForDeletion() throws Throwable {
        ///get all booking and select the first one to delete

        List<WebElement> lstOfAllBookings = hotelBookingFormPage.getAllBookings();
        if (lstOfAllBookings.size() > 2){

            String goingToDeleteEntrysFirstname = "" , goingToDeleteEntrysSurname = "", goingToDeleteEntrysTotalPrice = "", goingToDeleteEntrysCheckInDate = "", goingToDeleteEntrysCheckoutDate = "", goingToDeleteEntrysDeposit="";

            //get the entry information and save in cache
            goingToDeleteEntrysFirstname =  hotelBookingFormPage.getFirstNameFromBooking(lstOfAllBookings.get(1));
            goingToDeleteEntrysSurname = hotelBookingFormPage.getSurNameFromBooking(lstOfAllBookings.get(1));
            goingToDeleteEntrysTotalPrice = hotelBookingFormPage.getTotalPriceFromBooking(lstOfAllBookings.get(1));
            goingToDeleteEntrysCheckInDate = hotelBookingFormPage.getChekInDateFromBooking(lstOfAllBookings.get(1));
            goingToDeleteEntrysCheckoutDate = hotelBookingFormPage.getCheckOutDateFromBooking(lstOfAllBookings.get(1));
            goingToDeleteEntrysDeposit = hotelBookingFormPage.getDepositFromBooking(lstOfAllBookings.get(1));

            resourceManager.setTestData("deleteFirstName", goingToDeleteEntrysFirstname);
            resourceManager.setTestData("deleteSuName", goingToDeleteEntrysSurname);
            resourceManager.setTestData("deleteTotalPrice", goingToDeleteEntrysTotalPrice);
            resourceManager.setTestData("deleteCheckinDate", goingToDeleteEntrysCheckInDate);
            resourceManager.setTestData("deleteCheckoutDate", goingToDeleteEntrysCheckoutDate);
            resourceManager.setTestData("deleteDeposit", goingToDeleteEntrysDeposit);

            hotelBookingFormPage.clickDeleteButton(lstOfAllBookings.get(1));

        }else {
            Assert.fail("This test can not perform as there are not entries in list..Please create some entries");
        }
    }


    public boolean isEntryAvailInGrid(String expectedFirstName, String expectedSurName, String expectedTotalPrice, String expectedDepositStatus, String expectedCheckInDate, String expectedCheckOutDate){
        boolean isMatchFound = false;
        List<WebElement> lstOfAllBookings = hotelBookingFormPage.getAllBookings();

        String currentReferenceFirstname = "" , currentReferenceSurname = "", currentReferenceTotalPrice = "", currentReferenceCheckInDate = "", currentReferenceCheckoutDate = "", currentReferenceDeposit="";

        try{
            for (WebElement bookingRowElement : lstOfAllBookings){
                currentReferenceFirstname =  hotelBookingFormPage.getFirstNameFromBooking(bookingRowElement);
                currentReferenceSurname = hotelBookingFormPage.getSurNameFromBooking(bookingRowElement);
                currentReferenceTotalPrice = hotelBookingFormPage.getTotalPriceFromBooking(bookingRowElement);
                currentReferenceCheckInDate = hotelBookingFormPage.getChekInDateFromBooking(bookingRowElement);
                currentReferenceCheckoutDate = hotelBookingFormPage.getCheckOutDateFromBooking(bookingRowElement);
                currentReferenceDeposit = hotelBookingFormPage.getDepositFromBooking(bookingRowElement);


                if( expectedFirstName.equalsIgnoreCase(currentReferenceFirstname)  &&
                        expectedSurName.equalsIgnoreCase(currentReferenceSurname)    &&
                        expectedTotalPrice.equalsIgnoreCase(currentReferenceTotalPrice) &&
                        expectedCheckInDate.equalsIgnoreCase(currentReferenceCheckInDate) &&
                        expectedDepositStatus.equalsIgnoreCase(currentReferenceDeposit) &&
                        expectedCheckOutDate.equalsIgnoreCase(currentReferenceCheckoutDate)){
                    //match found
                    isMatchFound = true;
                    System.out.println("Newly created entry is available in list");
                    break;
                }else{
                    continue;
                }
            }

        }catch (Exception e)
        {
            System.out.println("Exception came while finding entry in grid");
        }
        return isMatchFound;
    }

    @Then("^that entry should be removed from the list$")
    public void thatEntryShouldBeRemovedFromTheList() throws Throwable {

        Thread.sleep(2000);

        String deletedFirstName = resourceManager.getTestData("deleteFirstName").toString();
        String deletedSurName = resourceManager.getTestData("deleteSuName").toString();
        String deletedTotalPrice = resourceManager.getTestData("deleteTotalPrice").toString();
        String deletedCheckinDate = resourceManager.getTestData("deleteCheckinDate").toString();
        String deletedCheckOutDate = resourceManager.getTestData("deleteCheckoutDate").toString();
        String deletedDeposit = resourceManager.getTestData("deleteDeposit").toString();

        //verify the deleted entry
        boolean isEntryInGrid =  isEntryAvailInGrid(
                deletedFirstName,deletedSurName,deletedTotalPrice,deletedDeposit,
                deletedCheckinDate, deletedCheckOutDate);

        System.out.println(
                "firstname-"+ deletedFirstName +  ", " +
                "Surname - "+ deletedSurName + ", " +
                        "Total Price-"+ deletedTotalPrice + ", " +
                        "Deposit Status -" + deletedDeposit +
                        "CheckinDate -" + deletedCheckinDate
                +        ", CheckOut Date-"+ deletedCheckOutDate);


        Assert.assertFalse("Deleted entries still there in list", isEntryInGrid);
    }

    @And("^I have \"([^\"]*)\" deposit$")
    public void iHaveDeposit(String depositStatus) throws Throwable {
        resourceManager.setTestData("depositStatus", depositStatus);
       if( depositStatus.equalsIgnoreCase("paid")){
           //choose TRUE
           hotelBookingFormPage.selectDepositPaidOrNot("true");
       }else{
           //choose FALSE
           hotelBookingFormPage.selectDepositPaidOrNot("false");
       }

    }
}
