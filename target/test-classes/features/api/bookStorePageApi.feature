@bookstore-api @regression
Feature: Book Store Page API

  Background:
    Given user login with user user and password Kasur1234!
    And user set userId with 821b4102-6bd2-45f0-bac3-e9671691f5ab

  Scenario: Verify Get All Books
    When user hit get books api
    Then user can verify all of these books
      | isbn          | title                                     | subTitle                                                          | author               | publish_date             | publisher       | pages | website                                                                 | description                                                                                                                                                                                                                                                       |
      | 9781449325862 | Git Pocket Guide                          | A Working Introduction                                            | Richard E. Silverman | 2020-06-04T08:48:39.000Z | O'Reilly Media  | 234   | http://chimera.labs.oreilly.com/books/1230000000561/index.html          | This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp   |
      | 9781449331818 | Learning JavaScript Design Patterns       | A JavaScript and jQuery Developer's Guide                         | Addy Osmani          | 2020-06-04T09:11:40.000Z | O'Reilly Media  | 254   | http://www.addyosmani.com/resources/essentialjsdesignpatterns/book/     | With Learning JavaScript Design Patterns, you'll learn how to write beautiful, structured, and maintainable JavaScript by applying classical and modern design patterns to the language. If you want to keep your code efficient, more manageable, and up-to-da   |
      | 9781449337711 | Designing Evolvable Web APIs with ASP.NET | Harnessing the Power of the Web                                   | Glenn Block et al.   | 2020-06-04T09:12:43.000Z | O'Reilly Media  | 238   | http://chimera.labs.oreilly.com/books/1234000001708/index.html          | Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical, hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft   |
      | 9781449365035 | Speaking JavaScript                       | An In-Depth Guide for Programmers                                 | Axel Rauschmayer     | 2014-02-01T00:00:00.000Z | O'Reilly Media  | 460   | http://speakingjs.com/                                                  | Like it or not, JavaScript is everywhere these days-from browser to server to mobile-and now you, too, need to learn the language or dive deeper than you have. This concise book guides you into and through JavaScript, written by a veteran programmer who o   |
      | 9781491904244 | You Don't Know JS                         | ES6 & Beyond                                                      | Kyle Simpson         | 2015-12-27T00:00:00.000Z | O'Reilly Media  | 278   | https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond | No matter how much experience you have with JavaScript, odds are you don’t fully understand the language. As part of the \\"You Don’t Know JS\\" series, this compact guide focuses on new features available in ECMAScript 6 (ES6), the latest version of the st |
      | 9781491950296 | Programming JavaScript Applications       | Robust Web Architecture with Node, HTML5, and Modern JS Libraries | Eric Elliott         | 2014-07-01T00:00:00.000Z | O'Reilly Media  | 254   | http://chimera.labs.oreilly.com/books/1234000000262/index.html          | Take advantage of JavaScript's power to build robust web-scale or enterprise applications that are easy to extend and maintain. By applying the design patterns outlined in this practical book, experienced JavaScript developers will learn how to write flex   |
      | 9781593275846 | Eloquent JavaScript, Second Edition       | A Modern Introduction to Programming                              | Marijn Haverbeke     | 2014-12-14T00:00:00.000Z | No Starch Press | 472   | http://eloquentjavascript.net/                                          | JavaScript lies at the heart of almost every modern web application, from social apps to the newest browser-based games. Though simple for beginners to pick up and play with, JavaScript is a flexible, complex language that you can use to build full-scale    |
      | 9781593277574 | Understanding ECMAScript 6                | The Definitive Guide for JavaScript Developers                    | Nicholas C. Zakas    | 2016-09-03T00:00:00.000Z | No Starch Press | 352   | https://leanpub.com/understandinges6/read                               | ECMAScript 6 represents the biggest update to the core of JavaScript in the history of the language. In Understanding ECMAScript 6, expert developer Nicholas C. Zakas provides a complete guide to the object types, syntax, and other exciting changes that E   |

  Scenario: Verify Get Book By Isbn
    When user hit get books api by isbn 9781449325862
    And user add the book to list of books
    Then user can verify all of these books
      | isbn          | title            | subTitle               | author               | publish_date             | publisher      | pages | website                                                        | description                                                                                                                                                                                                                                                     |
      | 9781449325862 | Git Pocket Guide | A Working Introduction | Richard E. Silverman | 2020-06-04T08:48:39.000Z | O'Reilly Media | 234   | http://chimera.labs.oreilly.com/books/1230000000561/index.html | This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp |


  Scenario: Verify Delete All Books
    When user hit delete all books api
    And user get user profile information
    Then user books count should be 0

  Scenario: Verify Post Books
    When user hit post books api with list of isbns
      | 9781449325862 |
      | 9781449331818 |
      | 9781449337711 |
      | 9781593277574 |
    And user get user profile information
    Then user books count should be 4
    Then user can verify all of these books
      | isbn          | title                                     | subTitle                                       | author               | publish_date             | publisher       | pages | website                                                             | description                                                                                                                                                                                                                                                     |
      | 9781449325862 | Git Pocket Guide                          | A Working Introduction                         | Richard E. Silverman | 2020-06-04T08:48:39.000Z | O'Reilly Media  | 234   | http://chimera.labs.oreilly.com/books/1230000000561/index.html      | This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp |
      | 9781449331818 | Learning JavaScript Design Patterns       | A JavaScript and jQuery Developer's Guide      | Addy Osmani          | 2020-06-04T09:11:40.000Z | O'Reilly Media  | 254   | http://www.addyosmani.com/resources/essentialjsdesignpatterns/book/ | With Learning JavaScript Design Patterns, you'll learn how to write beautiful, structured, and maintainable JavaScript by applying classical and modern design patterns to the language. If you want to keep your code efficient, more manageable, and up-to-da |
      | 9781449337711 | Designing Evolvable Web APIs with ASP.NET | Harnessing the Power of the Web                | Glenn Block et al.   | 2020-06-04T09:12:43.000Z | O'Reilly Media  | 238   | http://chimera.labs.oreilly.com/books/1234000001708/index.html      | Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical, hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft |
      | 9781593277574 | Understanding ECMAScript 6                | The Definitive Guide for JavaScript Developers | Nicholas C. Zakas    | 2016-09-03T00:00:00.000Z | No Starch Press | 352   | https://leanpub.com/understandinges6/read                           | ECMAScript 6 represents the biggest update to the core of JavaScript in the history of the language. In Understanding ECMAScript 6, expert developer Nicholas C. Zakas provides a complete guide to the object types, syntax, and other exciting changes that E |

  Scenario: Verify Delete by Isbn
    When user hit delete book by isbn 9781449325862
    And user get user profile information
    Then user book with isbn 9781449325862 existence is false
    Then user book with isbn 9781449331818 existence is true
    Then user book with isbn 9781449337711 existence is true
    Then user book with isbn 9781593277574 existence is true
    Then user books count should be 3

  Scenario: Verify Put Replace Book by Isbn
    When user hit put replace book by isbn 9781449325862 with 9781449331818
    And user get user profile information
    #verify replaced data
    Then user book with isbn 9781449325862 existence is true
    Then user book with isbn 9781449331818 existence is false
    #verify existing data
    Then user book with isbn 9781449337711 existence is true
    Then user book with isbn 9781593277574 existence is true
    Then user books count should be 3


