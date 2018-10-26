Feature:
  Command-line interface that allows the user to enter
  chemical formulas and receive output that describes
  information about the given formula

  Scenario Outline: The user gives some input
    When the user enters "<symbol>"
    Then the molar mass should be <mass>

    Examples:
      | symbol | mass      |
      | H      | 1.008     |
      | He     | 4.0026022 |
      | H2O    | 18.015    |
      | NO3    | 62.004    |
