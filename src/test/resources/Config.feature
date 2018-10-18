Feature:
  The configuration file that is used to modify different ways in which the program
  functions

  Scenario: The config is loaded
    When: The program initializes
    Given: The config is at "./config.json"
    Then: The configuration should be nonnull
