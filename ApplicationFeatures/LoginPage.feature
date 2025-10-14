Feature: Login feature in Creatio CRM.
	I want to use this feature file to validate all the test scenarios 
	related to Creatio CRM login page. 

  Background: initialize all pages.
    Given Initialized all the page classes

  Scenario Outline: Verify application login
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    When User click on the selection <buttons>
    Then Cookies popUp should be closed
    When User enter <username> and <password>
    And Click on the login button
    Then Login should be successful

    Examples:
      | buttons   | username                      | password                |
      | Allow all | bharathtechacademy5@gmail.com | BharathTechAcademy#1234 |
      | Deny      | test@gmail.com                | test#1234               |

  Scenario Outline: Verify application logout feature
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    When User click on the selection <buttons>
    Then Cookies popUp should be closed
    When User enter <username> and <password>
    And Click on the login button
    Then Login should be successful
    When User clicks on the user profile icon
    And Click on the logout button
    Then Logout should be successful

    Examples:
      | buttons   | username                      | password                |
      | Allow all | bharathtechacademy5@gmail.com | BharathTechAcademy#1234 |
