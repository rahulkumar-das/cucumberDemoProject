@orangedemo
Feature: Login and search for user

  @test1
  Scenario Outline: Login to the website and search for user Amanda in ESS userRole
    Given I navigate to the orange hrm website
    And I enter the admin name "<adminName>" and admin password "<adminPassword>"
    Then I click on Login button
    And I handle the chrome alert box
    Then I click on admin button
    And I enter the username "<username>"
    Then I will verify if the records should be displayed "<shouldBeDisplayed>"

    Examples: 
      | adminName | adminPassword | username | shouldBeDisplayed |
      | admin     | admin123      | abc      | False             |
