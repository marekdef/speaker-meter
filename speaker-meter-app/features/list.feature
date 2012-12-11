Feature: List feature

 Scenario: When I wait I see Speaker list
    Given I wait for 10 seconds
    Then I wait for the "SpeakerListActivity" screen to appear
    Then I should see "Marek Defeciński i Mariusz Saramak"
    Then I should see "Higiena pracy w projekcie"

 Scenario: When I click menu refresh I should see list update
	Given I wait for 3 seconds
	When I select "Odśwież" from the menu
	Then I wait for progress
