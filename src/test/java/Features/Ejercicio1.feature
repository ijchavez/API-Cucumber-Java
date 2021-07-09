
Feature: Spotify Ejercicio Uno
  No existe el plan premium, si existe el plan Duo que es el que agregué
  Scenario Outline: Planes de spotify
    Given estoy en la pagina de spotify
    When Busco el plan <plan>
    Then valido que el <plan> exista

    Examples:
      | plan        |
      | "Individual"|
      | "Duo"   |
      | "Familiar"  |