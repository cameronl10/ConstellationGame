# My Personal Project

## Constellation Identifier Game

This project will be a constellation trivia/learning  game.
 
The functionality of this game is: 
* The user will be given an image of a constellation and a list of possible answers where they can select the correct
constellation name. 
* At the end of the questions, the user will be given the constellations that they have gotten wrong and can review them
again in the game.

The project would contain a __GamePanel__ class that would hold an image and a list of options as answers. Then, if the 
user 
gets a question wrong, it would add that constellation name into a list of answers to be reviewed. The user would also 
be able to create their own question sets if they only wanted to quiz themselves on a specific selection of 
constellations.

This project is meant to be used by people who would like to test their ability to identify constellations and improve
their ability to do so.

This project is of interest to me because I like to stargaze and while I can identify some constellations in the night
sky, I would like to increase my ability to identify most the constellations in the sky.

## User Stories

* As a user, I want the questions I answered wrong to be added to a list.
* As a user, I want to be able to choose one of the four possible answers and know if I was correct.
* As a user, I want to be able to view all the questions I got wrong and review them at the end.
* As a user, I want to be able to create my own set and be able to play it.
* As a user, I want to be able to save my quiz sets  to file (if I so choose)
* As a user, I want to be able to be able to load my quiz sets from file (if I so choose)

## Citations

* Data persistence in this program is based off the UBC CPSC 210 edX Phase 2 Workroom example
* https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
* Constellation images from seasky.org, alarmy.com and star-name-registry.com
# Instructions for Grader

- You can generate the first required action related to adding Quiz Sets to a list of quiz sets by clicking the Quiz Set
Manager button, clicking the Create set button, clicking on any number of Constellation buttons, clicking 
  Create Set and entering a quiz name then clicking Create Quiz.
- You can generate the second required action related to adding Quiz Sets to a list of quiz sets by either clicking on
the Play game button and seeing the quiz sets listed as clickable buttons or clicking the Quiz Set Manager button and 
seeing the quiz sets listed as text.
- You can locate my visual component by clicking on the Play game button and clicking on any quiz sets that are made,
an image should be displayed on the screen.
- You can save the state of my application by clicking on the Quiz Set Manager Button and clicking the Save button. 
- You can reload the state of my application by clicking on the Quiz Set Manager Button and clicking on the Load button.

## Phase 4: Task 2
Tue Apr 11 19:29:45 PDT 2023
changed this quiz sets question to the full set

Tue Apr 11 19:29:45 PDT 2023
created full set of constellations

Tue Apr 11 19:29:45 PDT 2023
returned list of all constellation names

Tue Apr 11 19:29:45 PDT 2023
set up the default quiz set

Tue Apr 11 19:29:51 PDT 2023
created full set of constellations

Tue Apr 11 19:29:51 PDT 2023
returned list of all constellation names

Tue Apr 11 19:30:05 PDT 2023
set questions at quiz set: Ursa Quiz Set

Tue Apr 11 19:30:05 PDT 2023
added Ursa Quiz Set to  the list of quizzes

Tue Apr 11 19:30:07 PDT 2023
created full set of constellations

Tue Apr 11 19:30:07 PDT 2023
returned list of all constellation names

Tue Apr 11 19:30:15 PDT 2023
set questions at quiz set: Northern Hem Quiz Set

Tue Apr 11 19:30:15 PDT 2023
added Northern Hem Quiz Set to  the list of quizzes

## Phase 4: Task 3


If I were to refactor my design, I would include the use of the Observer Design Pattern.
I would use the Observer pattern because many of my classes have full access to a ListOfQuizSet field where it only
needs to know information about when it gets updated. This would decrease coupling since if I were to use the Observer
pattern I would only be looking at the implementation of an Observer object instead of a ListOfQuizSet.

Another redesign I would do would be to split up my ListOfQuizSet class into multiple smaller subclasses. This is
because the ListOfQuizSet takes on a lot of responsibilities so splitting it up into subclasses would increase the 
cohesion of the design and decrease coupling from all the classes that have a field of ListOfQuizSet.# ConstellationGame

