@amazon
Feature: Browse for shoes on amazon.com

  @test1
  Scenario Outline: search pant on amazon website
    Given I navigate to amazon us website
    And search for "<item>"
    Then I click on first item
    And add the item to cart
    And I navigate to my cart

    Examples: 
      | item |
      | pant |
