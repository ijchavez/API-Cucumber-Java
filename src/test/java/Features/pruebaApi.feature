@Test
Feature: Consulta de Despacho

  Scenario Outline: Como usuario quiero hacer una consulta de despacho
    Given Estoy en la API de chercher.tech
    When ejecuto el get
    Then verifico que el responseCode sea <responseCode> y la consulta tarde menos de <responseTime> ms
    Examples:
      |responseCode|responseTime|
      |         200|        5000|
