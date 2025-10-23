Feature: Git repository API validations.
	I want to use this feature file to verify all the scenarios related to GitHub API Requests.

  Scenario Outline: Request to create a new repository for the authenticator user with <Scenario>.
    Given User set the repository name as <RepoName> and description as <RepoDescription>
    When User sends a "POST" request to end point "/user/repos" to create the repository with visibility <IsPrivate>
    Then User should get the response code as <StatusCode>
    And User should get the response message as <StatusMessage>
    And User should get the response with in 2 seconds
    And Response body should contain <Key> and <ExpectedValue>

    Examples:
      | Scenario        | RepoName        | RepoDescription   | IsPrivate | StatusCode | StatusMessage        | Key               | ExpectedValue                       |
      | Invalid Request | JMeterRepo      | Test Repository 1 | true      | 422        | Unprocessable Entity | errors[0].message | name already exists on this account |
      | Valid Request   | RestAssuredRepo | Test Repository 2 | true      | 201        | Created              | name              | RestAssuredRepo                     |

  Scenario: Request to get the existing repository for the authenticated user.
    Given User sends a "GET" request to the endpoint "/repos/bharathtechacademy05/RestAssuredRepo" to get the repository
    Then User should get the response code as 200
    And User should get the response message as "OK"
    And User should get the response with in 2 seconds
    And Response body should contain "name" and "RestAssuredRepo"

  Scenario: Request to update the existing repository for the authenticated user.
    Given User sends a "PATCH" request to the endpoint "/repos/bharathtechacademy05/RestAssuredRepo" to update the repository visibility "false"
    Then User should get the response code as 200
    And User should get the response message as "OK"
    And User should get the response with in 2 seconds
    And Response body should contain "name" and "RestAssuredRepo"
    And Response body should contain "private" and "false"

  Scenario: Request to delete the existing repository for the authenticated user.
    Given User sends a "DELETE" request to the endpoint "/repos/bharathtechacademy05/RestAssuredRepo" to delete the repository
    Then User should get the response code as 204
    And User should get the response message as "No Content"
    And User should get the response with in 2 seconds
