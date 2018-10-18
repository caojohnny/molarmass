Feature:
  Downloads and parses a periodic table data source

  Scenario: Downloading the periodic table
    Given: The periodic table is sourced from "https://raw.githubusercontent.com/Bowserinator/Periodic-Table-JSON/master/PeriodicTableJSON.json"
    When: The program initializes
    Then: The JSON data should be nonnull
