Feature: Verification of login and logout functions of herokuapp

  Background:
    Given user is navigated to Login page
    Then Login page is loaded


  Scenario Outline: scenario_1 - verify that error message is shown for invalid credentials
    When user logs in with wrong '<login>' and '<password>'
    Then following error message is shown on the page
      | Invalid username or password! |

    Examples:
      | login | password  | comment        |
      | 12    | 213       | wrong both     |
      | Luke  | 213       | wrong password |
      | 12    | Skywalker | wrong username |


  Scenario: scenario_2 - verify that user cannot login with empty data
    When user logs in with wrong '' and ''
    Then Login page is still shown

@Smoke
  Scenario: scenario_3 - verify that user can login with valid credentials and logout
    When user logs in with correct credentials
    Then Dashboard page is loaded
    When user logs out
    Then Login page is loaded