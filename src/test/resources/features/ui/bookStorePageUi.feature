@bookstore-ui @regression
Feature: Book Store Page UI

  Background:
    Given user open "https://demoqa.com/books"
    When ui fully loaded

  Scenario: Verify Books Page
    Then user can see search box
    And user can see login button
    And user can see book table
    And user can see default rows is 10


  Scenario: Verify Books Page - Book Table
    Then user can see the table contains these columns
      | Image     |
      | Title     |
      | Author    |
      | Publisher |
    And user can see pagination tools in the botom of the page


  Scenario Outline: Verify Books Page - Change Total Rows <row>
    Then user click rows drop down and select <row>
    And user can see total page should be <totalPage>
    And user can see next button visiblitiy is <next>
    And user can see total displayed data is <totalDisplayedData>
    Examples:
      | row | next  | totalPage | totalDisplayedData |
      | 5   | true  | 2         | 5                  |
      | 20  | false | 1         | 8                  |

  Scenario: Verify Books Page - Change Page Prev/Next
    And user click rows drop down and select 5
    And user can see next button visiblitiy is true
    And user click next button
    Then user can see this books
      | Title                               | Author            | Publisher       |
      | Programming JavaScript Applications | Eric Elliott      | O'Reilly Media  |
      | Eloquent JavaScript, Second Edition | Marijn Haverbeke  | No Starch Press |
      | Understanding ECMAScript 6          | Nicholas C. Zakas | No Starch Press |
    And user click prev button
    And user can see this books
      | Title                                     | Author               | Publisher      |
      | Git Pocket Guide                          | Richard E. Silverman | O'Reilly Media |
      | Learning JavaScript Design Patterns       | Addy Osmani          | O'Reilly Media |
      | Designing Evolvable Web APIs with ASP.NET | Glenn Block et al.   | O'Reilly Media |
      | Speaking JavaScript                       | Axel Rauschmayer     | O'Reilly Media |
      | You Don't Know JS                         | Kyle Simpson         | O'Reilly Media |


  Scenario: Verify Books Page - Click book
    And user click book title "Programming JavaScript Applications"
    And user redirected to /books?book=


  Scenario Outline: Verify Books Page - Search book by <colName>
    And user fill search box with <keyword>
    And user can see total displayed data is <expectedResult>
    Examples:
      | colName   | keyword        | expectedResult |
      | Title     | Java           | 4              |
      | Author    | Axel           | 1              |
      | Publisher | O'Reilly Media | 6              |
      | Unexisted | ASDASDASD      | 0              |


  Scenario: Verify click login button
    And user click login button
    And user redirected to /login







