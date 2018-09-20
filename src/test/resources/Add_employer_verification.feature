Feature: Verification of add new employer function of herokuapp

  Background:
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When user clicks on Create button
    Then CreateEmployer page is loaded


  Scenario Outline: scenario_1 - verify that error message is shown for invalid email
    When user fulfill form with following data
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |
    And user clicks on Add button
    Then CreateEmployer page is shown
    And field '<fieldName>' is marked with an error

    Examples:
      | firstName       | lastName        | startDate     | email            | fieldName |
      |                 |                 |               |                  | firstName |
      | [random-String] |                 |               |                  | lastName  |
      | [random-String] | [random-String] |               |                  | startDate |
      | [random-String] | [random-String] | [random-Date] |                  | email     |
      |                 |                 |               | 12               | email     |
      |                 |                 |               | @                | email     |
      |                 |                 |               | 1@               | email     |
      |                 |                 |               | sada@test.com  1 | email     |


  Scenario: scenario_2 - verify that employer is not added when cancel is clicked
    When user fulfill form with following data
      | firstName       | lastName        | startDate     | email          |
      | [random-String] | [random-String] | [random-Date] | [random-Email] |
    And user clicks on Cancel button
    Then Dashboard page is shown
    And canceled employer is not present in the list


  Scenario: scenario_3 - verify that employer is  added when add is clicked and valid details
    And all elements on CreateEmployer page are present
    When user fulfill form with following data
      | firstName       | lastName        | startDate     | email          |
      | [random-String] | [random-String] | [random-Date] | [random-Email] |
    And user clicks on Add button
    Then Dashboard page is shown
    And created employer is present in the list