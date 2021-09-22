# VocabBuilder
##Overview
An app to add your new learnt words or basically any new thing you find worth remembering. Making a notes app has become monotonous and I believe this is unique. I love reading and I'm encountering new words which I desire to remember their meaning. Glad that this app solve that and future updates that will make it better than the old word-recording method. Books get lost, but technology will find your data. 

#Lessons Learnt
Just like any other project, I got to learn from this one, both technical and non-technical skills/concepts.
######Technical Concepts:
-ViewModelProviders.Factory - used to instantiate the ViewModel class that has a constructor with arguments(or none).
-Lateinit vs Lazy - lateinit is a promise for later initialization of a variable while lazy is used to make instatiation of bulky classes easier.
Moreover, lateinit can't be used with primitive datatyes e.g Int whose variable can't be null.i.e `lateinit var sample: Int` will generate a compile-time error as intergers can't be null.
-XML formatting an EditText to start at the top-> By default, editText start printing at the center, therefore to correct this, I set ` android:gravity="start"` in the activity_add_edit.xml 
-Explicit Intent and MVVM integration this will be discussed in the features.(Activities)
-Interfaces vs Abstract classes, this too will be discussed in the features(Dao)
######Non-Technical Lessons:
-Patience
-Research/Google-Dorking
-Discipline
#Features
1. Room Database
This is persistent way to store data in an android application.
On device storage, the room library creates and maintains this database for you
It maps database objects and java objects without boilerplate code.(Object Relation Mapping)
Room used with ViewModel and LiveData makes it easy to develop applications.
LiveData is like a newsreporter, it is a data holder class which is observable. It is lifecycle aware too. The UI components just observe the livedata. Just like how an individual watches 
a newsreporter on tv.
ViewModel is like the Media House, it is the communication center between the repository(data) and the UI.(Fragment/Activity). ViewModel instantiation survive UI controllers' recreation.
This will be clearly understood in the subsequent days.
The Repository is a class created to manage multiple data sources.
The Entity describes how data will be stored in the database using columns and rows.(Tables in the database)

2. DAO
This is an interface that issues queries to the database.
It can be either an abstract class or an interface. I have chosen it to be an interface since it only needs to share behaviour to the classes that will implement it.
If I wanted to share behaviour and properties/codes, then an abstract class would have sufficed.
To understand this difference read up on this article https://mjmanaog.medium.com/kotlin-abstract-class-interface-b9c4caf22252

