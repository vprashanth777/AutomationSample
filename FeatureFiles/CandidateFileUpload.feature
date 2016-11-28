@Candidate
Feature: GQA Cadidate upload file for NONCOMPETENT

  Scenario: GQA Cadidate upload file for NONCOMPETENT
    Given User  navigate to the application URL
    And Login into the application as "Candidate"
    Then User should able to login to the application
    When User should navigate to qualification Page
    And User must have "Not Yet Competent" under ELECTIVE UNITS
    And Select the "Not Yet Competent" course
    Then New page should open with the below details
      | Elements and Performance Criteria |
      | Required Skills & Knowledge       |
      | Evidence Guide & Range Statement  |
    And upload the document from previously upload files with "600" AssessementNotes
    When User submit the evidance
    Then status must be changed to satisfactory
    Then User logout from application
