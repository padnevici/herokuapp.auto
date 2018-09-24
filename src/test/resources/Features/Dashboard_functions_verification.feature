Feature: Verification of some Dashboard functions of herokuapp

  Scenario: scenario_1 - verify that Edit and Delete buttons are disabled
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    And Edit button is disabled
    And Delete button is disabled


  Scenario: scenario_2 - verify that Edit and Delete buttons are enabled when user is selected
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When any employer is selected
    Then Edit button is enabled
    And Delete button is enabled


  Scenario: scenario_3 - verify that double clicking on any user opens Edit form
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When any employer is selected
    And user makes double click on it
    Then EditEmployer page is loaded