Feature: Report Service Component Tests

  Scenario: Delete reports by trainer's username1
    When the client calls endpoint "saiprani12345789@gmail.com"
    Then response status code is 200

  Scenario: Delete reports by trainer's username2
    When the client calls endpoint "sai123@gmail.com"
    Then response status code is not present
    


  Scenario: Creating a report
    Given the report data
      | trainerUserName    | trainerFirstName | trainerLastName | trainerStatus | traineeDuration |
      | testTrainer        | John             | Doe             | true          | 90              |
    When the client sends a POST request to /reportservice/save with the data
    Then the response status code should be 200
    And the response should contain the report data

  # Add more scenarios as needed

