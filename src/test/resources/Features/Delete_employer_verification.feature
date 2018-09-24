@Regression
Feature: Verification of delete employer function of herokuapp

  Background:
    Given user is navigated to Login page
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When user clicks on Create button on DashboardPage
    Then CreateEmployer page is loaded
    When user fulfill form with following data on CreateEmployer page
      | firstName      | lastName       | startDate     | email          |
      | [random-Fname] | [random-Lname] | [random-Date] | [random-Email] |
    And user clicks on Add button on CreateEmployeePage
    Then Dashboard page is loaded
    And created employee is present in the list

  @Smoke
  Scenario: scenario_1 - verify that employer can be deleted from Dashboard page
    When created employee is selected
    And user clicks on Delete button on DashboardPage
    And user confirms delete action
    Then Dashboard page is loaded
    # workaround for an automation bug
    When user is navigated to Login page
    And user logs in with correct credentials
    Then Dashboard page is loaded
    # end workaround
    And deleted employee is not present in the list


  Scenario: scenario_2 - verify that employer is not deleted when delete action is canceled
    When created employee is selected
    And user clicks on Delete button on DashboardPage
    And user cancels delete action
    Then Dashboard page is still shown
    And created employee is present in the list


  Scenario: scenario_3 - verify that employer can be deleted from EditEmployer page
    When user opens details for created employee
    Then EditEmployer page is loaded
    And user clicks on Delete button on EditEmployeePage
    And user confirms delete action
    Then Dashboard page is loaded
    And deleted employee is not present in the list