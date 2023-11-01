Feature: Automation II section 2
  Scenario: Create new user, update user and delete user with url  http://todo.ly

    When send POST request "/api/user.json" with body
    """
    {
      "Email": "userTest@userTest.com",
      "FullName": "userTest",
      "Password": "abc123"
    }
    """
    Then response code should be 200
    And the attribute "FullName" should be "userTest"
    And the attribute "Email" should be "userTest@userTest.com"
    Given using token in todo.ly
    When send PUT request "/api/user/0.json" with body
    """
    {
      "Email": "Email2@change.com"
    }
    """
    Then response code should be 200
    And the attribute "Email" should be "Email2@change.com"
    When send DELETE request "/api/user/0.json" with body
    """
    """
    Then response code should be 200
    And the attribute "Email" should be "Email2@change.com"
