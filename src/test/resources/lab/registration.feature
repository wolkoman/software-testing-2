Feature: Successful Registration

  Background:
	Given I am on the registration page
	When I enter "Alexander Gustav Ben" as a first name
	And I enter "Doppelmaierschubhoff" as a last name
	And I enter "01/19/1997" as a birthday
	And I select "Unspecified" as a gender
	And I select "Technische Universität Wien" as university

  Scenario: Successful Registration as a student
	And I acknowledge that I am a student yes
	And I sign up
	Then I should be registered
	And the name should be "Alexander Gustav Ben Doppelmaierschubhoff"
	And the birthday should be "01/19/1997"
	And the gender should be "unspecified"
	And the university should be "technischeUniversitätWien"

  Scenario: Successful Registration as a non-student
	And I sign up
	Then I should be registered

  Scenario: Barely 18
	And I enter "11/11/2002" as a birthday
	And I sign up
	Then I should be registered
