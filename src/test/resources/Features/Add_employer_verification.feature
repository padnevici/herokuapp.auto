Feature: Verification of add new employer function of herokuapp

  Background:
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When user clicks on Create button on DashboardPage
    Then CreateEmployer page is loaded


  Scenario Outline: scenario_1 - verify that error message is shown for invalid email
    When user fulfill form with following data on CreateEmployer page
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |
    And user clicks on Add button on CreateEmployeePage
    Then CreateEmployer page is still shown
    And field '<fieldName>' is marked with an error

    Examples:
      | firstName      | lastName       | startDate     | email            | fieldName  |
      |                |                |               |                  | FIRST_NAME |
      | [random-Fname] |                |               |                  | LAST_NAME  |
      | [random-Fname] | [random-Lname] |               |                  | START_DATE |
      | [random-Fname] | [random-Lname] | [random-Date] |                  | EMAIL      |
      | [random-Fname] | [random-Lname] | [random-Date] | 12               | EMAIL      |
      | [random-Fname] | [random-Lname] | [random-Date] | @                | EMAIL      |
      | [random-Fname] | [random-Lname] | [random-Date] | 1@               | EMAIL      |
      | [random-Fname] | [random-Lname] | [random-Date] | sada@test.com  1 | EMAIL      |


  Scenario: scenario_2 - verify that employer is not added when cancel is clicked
    When user fulfill form with following data on CreateEmployer page
      | firstName      | lastName       | startDate     | email          |
      | [random-Fname] | [random-Lname] | [random-Date] | [random-Email] |
    And user clicks on Cancel button on CreateEmployeePage
    Then Dashboard page is loaded
    And canceled employee is not present in the list


  Scenario: scenario_3 - verify that employer is added when add is clicked and valid details
    When user fulfill form with following data on CreateEmployer page
      | firstName      | lastName       | startDate     | email          |
      | [random-Fname] | [random-Lname] | [random-Date] | [random-Email] |
    And user clicks on Add button on CreateEmployeePage
    Then Dashboard page is loaded
    And created employee is present in the list