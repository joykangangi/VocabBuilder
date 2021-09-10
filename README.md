# VocabBuilder
An app to add your new learnt words
The following are updates done on the project from the first to the final day.
Day0:
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

