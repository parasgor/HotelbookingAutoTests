
@Regression
@Create
Feature: Create Hotel booking

  Scenario Outline: Create booking
    Given I navigate to Home page
    When I enter firstname as  "<firstname>"
    And I enter surname as "<surname>"
    And I enter total price as "<totalPrice>"
    And I have "<paidOrNotPaid>" deposit
    And I enter checkindate as "<checkinDate>"
    And I enter checkoutdate as "<checkoutDate>"
    And I click on the save button
    Then I should be able to see new entry in table

    Examples:
      | firstname | surname | totalPrice | checkinDate | checkoutDate | paidOrNotPaid |
      #this entries will be user supplied data
      | Oscar     | Green   | 10         | 2019-01-02  | 2019-02-03   | false         |
      | John      | Oliver  | 10         | 2019-01-01  | 2019-02-02   | false         |
      | Tusok     | Nouin   | 10         | 2019-01-01  | 2019-02-02   | false         |
      #blank entries in firstname and surname  will pick up the random alphabets to make the entry unique everytime
      |           |         | 10         | 2019-01-01  | 2019-02-02   | false         |

