# VocabBuilder
## Overview
An app to add your new learnt words or basically any new thing you find worth remembering. Making a notes app has become monotonous and I believe this is unique. I love reading and I'm encountering new words which I desire to remember their meaning. Glad that this app solves that and future updates that will make it better than the old word-recording method. Books get lost, but technology will find your data.

# Lessons Learnt
Just like any other project, I got to learn from this one, both technical and non-technical skills/concepts.
###### Technical Concepts:
- ViewModelProviders.Factory - used to instantiate the ViewModel class that has a constructor with arguments(or none).
- Lateinit vs Lazy - lateinit is a promise for later initialization of a variable while lazy is used to make instatiation of bulky classes easier.
Moreover, lateinit can't be used with primitive datatyes e.g Int whose variable can't be null.i.e `lateinit var sample: Int` will generate a compile-time error as intergers can't be null.
- XML formatting an EditText to start at the top-> By default, editText start printing at the center, therefore to correct this, I set ` android:gravity="start"` in the activity_add_edit.xml 
- Explicit Intent and MVVM integration this will be discussed in the features.(Activities)
- Interfaces vs Abstract classes, this too will be discussed in the features(Dao)
###### Non-Technical Lessons:
- Patience
- Research/Google-Dorking
- Discipline
# Libraries
## 1. [Room Database](https://developer.android.com/training/data-storage/room)
This is persistent way to store data locally in an android application.
On device storage, the room library creates and maintains this database for you
It maps database objects and java objects without boilerplate code.(Object Relation Mapping)
Room used with ViewModel and LiveData makes it easy to develop applications.
LiveData is like a newsreporter, it is a data holder class which is observable. It is lifecycle aware too. The UI components just observe the livedata. Just like how an individual watches a newsreporter on tv.
## 2A.[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwy7CKBhBMEiwA0Eb7an0EIfb7CGEGmYBNdvT4hIKBs4AzHYE_VfFZKQWHsg3hGHLtqOft3BoCxA0QAvD_BwE&gclsrc=aw.ds)
It is like the Media House, it is the communication center between the repository(data) and the UI.(Fragment/Activity). ViewModel instantiation survive UI controllers' recreation.
The Repository is a class created to manage multiple data sources.
The Entity describes how data will be stored in the database using columns and rows.(Tables in the database)
In the app, the WordViewModel has a constructor with an argument(dependency) of the repository to help access methods from dao and describe what they will do in the viewModel. The viewmodel is where business logic is contained. These methods are executed from a [coroutine](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwy7CKBhBMEiwA0Eb7arbh2ua5s_jYyCeuCObVxR8GbZoshy3wialREeUk6XtNmoGkqH7zwhoCYHQQAvD_BwE&gclsrc=aw.ds) This prevents blocking in the main thread and lagging of an app.
Since the viewmodel has a constructor, I created a viewmodelfactory custom class, to help in initialization of the viewmodel class. If there was no parameter in the construcor of the viewmodel, then there would not be need to create a custom factory class.  

###### B. DAO
This is an interface that issues queries to the database.
It can be either an abstract class or an interface. I have chosen it to be an interface since it only needs to share behaviour to the classes that will implement it.
If I wanted to share behaviour and properties/codes, then an abstract class would have sufficed.
To understand this difference read up on this [article](https://mjmanaog.medium.com/kotlin-abstract-class-interface-b9c4caf22252)

###### C.Database
Here we create our database and define how it will be accessed, it has a companion object to allow clients to access methods for creating or getting a database without instatiating the class(it is an abstract class) The class is solely to provide a database, there is no need to instatiate it. There is a synchronize block with a Lock to ensure only one thread of execution at a time can enter this block of code.
## 3.Adapter
It prepares views to be loaded in the recyclerview. This is done with the help of the methods:
- onCreateViewHolder -> to create a new ViewHolder(template) when no existing viewHolders can be re-used.
- onBindViewHolder -> to bind views from the input to the viewHolder 
- getItemCount-> to return the numbers of list items. In this project however, a DiffUtil was used that calculates the difference between two lists and outputs a list of updated operations that convert the old list(first) to a new list(second).
- an inner class of viewHolder-> to represent a sinlge list item view.
- interfaces, are the ones to help in making the recycler items clickable and these interfaces are defined in the main activity. One interface is on clicking the delete icon while the other is to see detals of the new word or phrase.
## 4.Screens
##### Main Activity
##### AddEdit Activity
## 5.Screenshoots
![screen1](https://user-images.githubusercontent.com/64706463/134977944-cb829693-b196-4361-8e0d-82ddaf9c5b2b.png)
![screen2](https://user-images.githubusercontent.com/64706463/134977955-d0630c6f-1585-4e07-bc2d-0436377421b4.png)

## 6. Future Updates
As I continue learning and researching, the following are features I would like to add:
- Categorize what to add by color e.g. New Words, Quotes, Religion Verses, Names of a new place.
- Add alarm, this alarm will use the desired tune, i.e. of a new word or quote, or whatever in the list that the user desires to wake up to. (I find waking up to phrases I like better than just some sound, which becomes like a noisy gong over time)
- Reminders according to a selected frequency by the users, e.g daily, once a week, etc to remind them of the new learnt things. 
