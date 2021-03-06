# Football Team Chooser
> Project based on Spring Framework .

## Table of contents
* [General info](#general-info)
* [Demo](#Demo)
* [Technologies](#technologies)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
Main purpose of the application is to help choose equal teams based on each player’s skill when they play football match or other team game. The application is for people who like to play football occasionally with friends and want to enjoy this time together.
The next stage of development will be adding the possibility of signing up for the event (football match).
In general, the application helps an organizer of a match to manage every aspect of a game.

## Demo
Here is a working live demo : http://www.teamchooser.pl/ or https://footballteamchooser.herokuapp.com/ . 
(need to wait few seconds for respond because of heroku server's limits)

## Technologies
* JavaSE - version 1.8
* Spring Core - version 5.0.8
* Spring Boot - version 2.0.4
* Spring Data JPA - version 2.0.9
* Hibernate Core - version 5.2.17
* Thymeleaf - version 3.0.9
* HTML - version 5
* CSS - version 3
* Bootstrap - version 3.3.7-1

## Features
List of features ready:
* Manage players with full CRUD
* Choose players for game
* Calculating teams based on skill indicator.
* History of games with scores
* Separate accounts (Login Page)
* Security (only logged user can view the entire website)
* RESTfull Api for managing players

To-do list (technical site)
* Improve Front-end
* Add REST Api for whole application
* Add tests
* Add handling of database exceptions

Potentiality of future development
* Schedule for games with list of records.
* Recording statistics after game.
* Recording fees after game.
* More complex skill indicator (min 1 - max 100) : 
    * 1-60 : starting skill
    * 60-80 : based on last 5 games
    * 81-100 : random number simulating day form
* Better algorithm for calculating teams
* Possibility of choosing 3 or more teams

## Status
Project is in progress and will be developed.

## Contact
Created by Sławomir Morawski - [slawvs@gmail.com] - feel free to contact me!
