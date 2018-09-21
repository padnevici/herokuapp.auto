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


  Scenario: scenario_1 - verify that employer can be deleted from Dashboard page
    When created employer is selected
    And user clicks Delete button
    And user confirms delete action
    Then Dashboard page is loaded
    And deleted employer is not present in the list


  Scenario: scenario_2 - verify that employer is not deleted when delete action is canceled
    When created employer is selected
    And user clicks Delete button
    And user cancel delete action
    Then CreateEmployer page is loaded


  Scenario: scenario_3 - verify that employer can be deleted from EditEmployer page
    When user opens details for created employer
    Then EditEmployer page is loaded
    And user clicks Delete button
    And user confirms delete action
    Then Dashboard page is loaded
    And deleted employer is not present in the list