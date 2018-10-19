Feature:
  Downloads and parses a periodic table data source

  Scenario Outline: Downloading the periodic table
    When the data has been downloaded
    Then the JSON data should be nonnull
    And the symbol of "<symbol>" should be "<name>"

    Examples:
      | symbol | name      |
      | H      | Hydrogen  |
      | O      | Oxygen    |
      | K      | Potassium |
      | U      | Uranium   |
