This file contains a few notes on the tests for the ShowPedia application. The test files are organized as follows:

For each test, there are two files:
- an input file: spoilerXX-in.txt
- an expected output file: spoilerXX-out.txt

The XX is replaced by a serial number on the file (e.g. 01, 02, 03, ...) in those files. All tests are independent and can be run in any order. That said, in general, the serial number in the test file indicates a possible path for constructing and testing your application. So, for example, test spoiler01-in.txt will check commands that will be used in other tests (in particular, the exit command is used in all tests).

Please note that this test suite is still in a beta version. It will be updated with a few more tests. If you identify any problems with the expected results, please contact mgoul@ftc.unl.pt .

Test goals:
- spoiler01-in.txt
Commands used: help, exit
Comment: This test checks whether commands (in particular help) are case insensitive. It also verifies that the help menu and goodbye message are correctly sent to the standard output.

- spoiler02-in.txt
Commands used: exit, currentShow, addShow, and switchToShow
Comment: this test checks whether the basic creation of new shows and the switching between active shows are working correctly. It also validates error conditions for the three new commands (currentShow, addShow and switchToBox)

- spoiler03-in.txt
Commands used: exit, currentShow, addShow, and switchToShow, and addSeason.
Comment: this test checks the same as in spoiler02 and tries to add seasons to the created shows. This includes error messages, as well.

- spoiler04-in.txt
Commands used: exit, currentShow, addShow, and switchToShow, and addSeason, and addEpisode.
Comment: this test checks the same as spoiler03 and tries to add episodes to seasons of created shows. This includers error messages, as well.

- spoiler05-in.txt
Commands used: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter.
Comment: this test creates a few shows and adds several characters to each of them. This includes error messages, as well.

- spoiler06-in.txt
Commands used: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter, addRelationship.
Comment: this test creates several family relationships. Most of the examples come from the series Modern Family. You can get a picture of the family tree, which is actually a graph ;-), from https://en.wikipedia.org/wiki/Modern_Family , as this will make it easier for you to check whether your results are correct. This example will also be extensively used when testing if and how two characters are related. Note also a couple of relationships established in the series Game of Thrones. Sorry for the spoilers, if you have not caught up with season 1. Yes, there is a human who is a mother of 3 dragons. Go figure. This example is there to show that you can have parental relationships between characters played by humans and digital characters. This examples includes error messages, as well.

- spoilers07-in.txt
Commands used: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter, addRomance.
Comment: this test creates several romantic relationships, including both human and virtual characters, as well as error messages, where appropriate.

- spoilers08-in.txt
Commands used: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter, addRelationship, addRomance, characterResume.
Comment: This test creates several characters, relationships and romances among those characters. It also presents a character's resume. At this stage, without the introduction of actions or quotes, the resume includes nothing on the character's participation in episodes. In other words, it only shows the list of parents, kids, siblings and romantic relationships by that character in that show. The goal is to double check that all information so far was collected correctly.

- spoilers09-in.txt: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter, alsoAppearsOn.
Comment: This test checks wether we correctly can identify all the shows in which a particular actor has participated (command alsoAppearsOn). Note that an actor may perform more than one role in the same show. In this test, one of the actors has 5 roles in the show Star Trek: The Next Generation.

- spoilers10-in.txt 
Commands used: exit, currentShow, addShow, addEpisod, switchToShow, addCharacter, addRelationship, addRomance, characterResume, howAreTheseTwoRelated.
Comment: This test creates several characters, relationships and romances among those characters. It also presents a character's resume. At this stage, without the introduction of actions or quotes, the resume includes nothing on the character's participation in episodes. In other words, it only shows the list of parents, kids, siblings and romantic relationships by that character in that show. Then, it gets to its main goal: to check the howAreTheseTwoRelated functionality. Error messages are tested, as well.
