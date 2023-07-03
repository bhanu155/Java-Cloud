Feature: Movie Store
Search Movie By Name

Scenario: Exact Match
Given two movies titled "Forrest Gump" and "Titanic" 
When "Forrest Gump" is searched 
Then the results list consists of "Forrest Gump"