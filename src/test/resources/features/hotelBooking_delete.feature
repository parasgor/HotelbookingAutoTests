
@Regression
@delete
Feature: Delete Hotel booking

  Scenario: Delete
    Given I navigate to Home page
    When I click on the delete button for the very first entry
    Then that entry should be removed from the list
