Feature:
  Command-line interface that allows the user to enter chemical
  formulas and receive output that describes information about
  the given formula

  Scenario: The user gives some input
    When the user enters "<symbol>"
    Then "<info>" should be outputted

  Examples:
    | symbol | info |
    | H | Molar Mass = 1.0008 |
    | He | Molar Mass = 4 |
    | H2O | Molar Mass = 18 |
    | NO3 | Molar Mass = 62 |
    | AZ | Invalid input |
