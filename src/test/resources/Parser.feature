Feature:
  Parses a chemical formula or symbol into something that
  can be used to calculate information

  Scenario Outline: an element is passed to the parser
    When "<element>" is passed
    Then return "<elements>"

    Examples:
      | element | elements |
      | H       | H = 1    |
      | O       | O = 1    |

  Scenario Outline: a formula is passed to the parser
    When "<formula>" is passed
    Then return "<elements>"

    Examples:
      | formula | elements      |
      | H2O     | O = 1, H = 2  |
      | HCl     | Cl = 1, H = 1 |
      | O2      | O = 2         |