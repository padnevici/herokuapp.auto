Feature: Verification of edit employer function of herokuapp

  Background:
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When user clicks on Create button
    Then CreateEmployer page is loaded
    When user fulfill form with following data
      | firstName       | lastName        | startDate     | email          |
      | [random-String] | [random-String] | [random-Date] | [random-Email] |
    And user clicks on Add button
    Then Dashboard page is shown
    And create employer is present in the list
    When user opens details for created employer
    Then EditEmployer page is loaded


  Scenario Outline: scenario_1 - verify that error message is shown for invalid email
    When user fulfill form with following data
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |
    And user clicks on Add button
    Then EditEmployer page is shown
    And field '<fieldWithError>' is marked with an error

    Examples:
      | firstName       | lastName        | startDate     | email            | fieldWithError |
      |                 |                 |               |                  | firstName      |
      | [random-String] |                 |               |                  | lastName       |
      | [random-String] | [random-String] |               |                  | startDate      |
      | [random-String] | [random-String] | [random-Date] |                  | email          |
      |                 |                 |               | 12               | email          |
      |                 |                 |               | @                | email          |
      |                 |                 |               | 1@               | email          |
      |                 |                 |               | sada@test.com  1 | email          |


  Scenario: scenario_2 - verify that employer is not added when cancel is clicked
    When user fulfill form with following data
      | firstName       | lastName        | startDate     | email          |
      | [random-String] | [random-String] | [random-Date] | [random-Email] |
    And user clicks on Cancel button
    Then Dashboard page is shown
    When user opens details for created employer
    Then employer details are not updated


  Scenario: scenario_3 - verify that employer is update when Update is clicked and valid data
    And all elements on EditEmployer page are present
    When user fulfill form with following data
      | firstName       | lastName        | startDate     | email          |
      | [random-String] | [random-String] | [random-Date] | [random-Email] |
    And user clicks on Update button
    Then Dashboard page is shown
    And updated employer is present in the list
    When user opens details for updated employer
    Then employer details are updated