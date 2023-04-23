Feature: Is the response ok?
  Sending a request should return a valid response

  Scenario: Validate status of the response after handling an event
    Given myLambda is running
    When I send a valid request
    Then I should get status "\"200 OK\""
  