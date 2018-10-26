Feature:
  The configuration file that is used to modify different
  ways in which the program functions

  Background:
    Given the configuration is at "./config.json"

  Scenario: The config is loaded
    Then the configuration should be nonnull

  Scenario Outline: Config values are polled
    Then string "<key>" should be configured to <value>

    Examples:
      | key                   | value                                                                                              |
      | prompt                | "> "                                                                                               |
      | data-source           | "https://raw.githubusercontent.com/Bowserinator/Periodic-Table-JSON/master/PeriodicTableJSON.json" |
      | output-format         | "Molar Mass: {0} g/mol"                                                                            |
      | invalid-input-message | "Invalid Input"                                                                                    |

  Scenario Outline: Config values should be polled
    Then boolean "<key>" should be configured to <value>

    Examples:
      | key   | value  |
      | debug | "true" |
