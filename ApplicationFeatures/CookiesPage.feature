Feature: cookies feature in Creatio CRM.
	I want to use this feature file to validate all the test scenarios 
	related to Creatio CRM cookies page. 

  Background: initialize all pages.
    Given Initialized all the page classes

  Scenario: Validate the cookies pop-up displayed in the login page
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed

  Scenario: verify the cookies pop-up content message
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    And Verify cookies pop-up content
      | content                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
      | We may use cookies and similar technologies to collect information about the ways you interact with and use the website, to support and enhance features and functionality, to monitor performance, to personalize content and experiences, for marketing and analytics, and for other lawful purposes. We also may share information about your use of our site with our social media, advertising and analytics partners who may combine it with other information that you’ve provided to them or that they’ve collected from your use of their services. Please, see more details on the "About" tab |

  Scenario: Verify Cookies Pop-up Logos
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    And verify the cookies pop-up logos

  Scenario: Verify Cookies Pop-up Selection buttons
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    And verify the cookies pop-up selection buttons

  Scenario: Verify Cookies Pop-up switch buttons
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    And verify the cookies pop-up switch buttons

  Scenario: Verify Cookies Pop-up expanded view
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    When user clicks on the Show details link.
    Then cookies pop-up should be displayed in expanded view

  Scenario Outline: Verify Cookies Pop-up is closed for all the <buttons>
    Given Launch the Creatio application
    Then Verify whether the cookies pop-up is displayed
    When user clicks on the Show details link.
    Then cookies pop-up should be displayed in expanded view
    When User click on the selection <buttons>
    Then Cookies popUp should be closed

    Examples:
      | buttons         |
      | Allow all       |
      | Allow selection |
      | Deny            |
