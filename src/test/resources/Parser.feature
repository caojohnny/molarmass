Feature:
  Parses a chemical formula or symbol into something that can
  be used to calculate information

  Scenario: an element is passed to the parser
    When "<element>" is passed
    Then return "<elements>"

    Examples:
    | element | elements |
    | H | H = 1 |
    | O | O = 1 |

  Scenario: a formula is passed to the parser
    When "<formula>" is passed
    Then return "<elements>"

    Examples:
    | formula | elements |
    | H20 | H = 2, O = 1 |
    | HCl | H = 1, Cl = 1|
    | O2 | O = 2 |