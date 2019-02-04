
@Regression
@Create
Feature: Create & Delete Hotel booking

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
#      | Oscar     | Green   | 10         | 2019-01-02  | 2019-02-03   | true          |
#      | John      | Oliver  | 10         | 2019-01-01  | 2019-02-02   | false         |
#      | Tusok     | Nouin   | 10         | 2019-01-01  | 2019-02-02   | false         |
      |           |         | 10         | 2019-01-01  | 2019-02-02   | false         |

#
#
#  @delete
#  Scenario: Delete Booking
#    Given I navigate to Home page
#    When I click on the delete button for the very first entry
#    Then that entry should be removed from the list
