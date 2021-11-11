Feature: Validation Errors

  Background:
	Given I am on the registration page


  Scenario Outline: Should show validation errors
	When I enter "<firstname>" as a first name
	And I enter "<lastname>" as a last name
	And I enter "<birthday>" as a birthday
	And I select "<gender>" as a gender
	And I select "<university>" as university
	And I sign up
	Then there should be a validation error

	Examples:
	  | firstname | lastname   | birthday   | gender      | university                  |
	  |           | Mustermann | 01/19/1997 | Unspecified | Technische Universität Wien |
	  | Max       |            | 01/19/1997 | Unspecified | Technische Universität Wien |
	  | Max       | Mustermann |            | Unspecified | Technische Universität Wien |
	  | Max       | Mustermann | 01/01/2004 | Unspecified | Technische Universität Wien |
	  | Max       | Mustermann | 01/19/1997 | Male        | Technische Universität Wien |
	  | Max       | Mustermann | 01/19/1997 |             | Technische Universität Wien |
	  | Max       | Mustermann | 01/19/1997 | Unspecified |                             |

  Scenario: Long firstname
	When I enter "This text surely is way to long" as a first name
	And I enter "Mustermann" as a last name
	And I enter "01/19/1997" as a birthday
	And I select "Technische Universität Wien" as university
	And I select "Unspecified" as a gender
	And I acknowledge that I am a student yes
	And I sign up
	Then there should be a validation error

  Scenario: Long lastname
	When I enter "Max" as a first name
	When I enter "This text surely is way to long" as a last name
	And I enter "01/19/1997" as a birthday
	And I select "Technische Universität Wien" as university
	And I select "Unspecified" as a gender
	And I acknowledge that I am a student yes
	And I sign up
	Then there should be a validation error

  Scenario: Numeric firstname
	When I enter "Max1" as a first name
	When I enter "Mustermann" as a last name
	And I enter "01/19/1997" as a birthday
	And I select "Technische Universität Wien" as university
	And I select "Unspecified" as a gender
	And I acknowledge that I am a student yes
	And I sign up
	Then there should be a validation error

