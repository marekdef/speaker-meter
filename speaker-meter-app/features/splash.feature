Feature: Splash screen

  Scenario: When I start application it starts with splash screen 
    Then I wait for the "SplashScreenActivity" screen to appear

  Scenario: When I wait I see splash screen transition to list activity
    Then I wait for 4 seconds
    Then I wait upto 6 seconds for the "VenueTabActivity" screen to appear 
