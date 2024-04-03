# Reader's Room

## A book logging system.

### What will the application do?

Reader's room is a book logging system that will allow users to create their
own personal libraries. Each book will have a title, author, genre, and length. Users may leave ratings and reviews for 
books that they have read, as well as create a book wish list, for books that they want to read. The application will also
generate statistics for users based on the number of pages, genres, and authors they read.

### Who will use this application?

Avid readers and beginners alike may be interested in Reader's Room. This tool aims to motivate and engage with readers of all levels.

### Why is this project of interest to you?

My favourite hobby is reading, and I enjoy keeping track of my reading data. Creating this tool will give me a space to 
store all of this data and share my ratings and reviews with friends. 


## User Stories:
- As a user, I want to be able to add a book to my library
- As a user, I want to be able to view a list of the titles of the books in my library
- As a user, I want to be able to select a book and view the author, genre, and length.
- As a user, I want to be able to select a book in my library and rate it on a scale of one to five stars (Console only)
- As a user, I want to be able to select a book in my library and leave a review. (Console only)
- As a user, I want to have the option to save my library to file or not.
- As a user, I want to be given the option to load my library from file, or not.
- As a user, I want to be able to remove a book from my library

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by: 
  selecting the "Add Book" tab on the left side of screen. Enter book's information and select add.




- You can generate the second required action related to the user story "adding multiple Xs to a Y" by: selecting the 
"Library" tab on the left side of the screen and clicking refresh. Click any of the books in the list to view more
details about the book. You may click remove book or ok to continue.


- You can locate my visual component by viewing the logo image on the Home tab


- You can save the state of my application by clicking "Save data" button on the Home tab


- You can reload the state of my application by clicking "Load data" button on the Home tab

## Phase 4: Task 2

Wed Apr 03 00:28:15 PDT 2024
'The Hunger Games' was added to library.


Wed Apr 03 00:28:47 PDT 2024
'Harry Potter' was added to library.


Wed Apr 03 00:28:52 PDT 2024
'Harry Potter' was removed from library.

## Phase 4: Task 3

As this was my first exposure to Swing, I don't think I was very organized in my approach. Based on my UML class diagram, I notice some coupling around the GUI class and the 
tabs classes. Looking into the classes, I notice that this coupling comes from the fact that each of the tabs
has a field of a GUI. This is because each of the tabs extends JPanel (by extending Tab), but they still need
to make changes to the JFrame itself, which is in GUI class. There are also times that the tabs need to access 
library from GUI, which results in some messy code that could be simplified by refactoring GUI. 

More specifically, I could use the Observer pattern that we learned recently to notify UI components of changes in the 
model without directly coupling them. I would make the GUI class the observer and the Library class the Subject/Observable.
The GUI would then be notified and updated when books are added or removed from a library. Not only would this reduce coupling,
but it would also solve of the problems related to GUI/Tabs issue I discussed above. Overall, I hope to continue working 
on this project outside of class to create a better design for Reader's Room!