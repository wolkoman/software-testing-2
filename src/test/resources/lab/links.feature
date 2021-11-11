Feature: Links

  Scenario Outline: Follow external links
	Given I am on the registration page
	When I hover over the menu
	And I click the link labeled "<label>"
	Then the title of the page should be "<title>"

	Examples:
	  | label | title                      |
	  | TUWEL | TU Wien E-Learning         |
	  | TISS  | TISS - Homepage \| TU Wien |
