package com.example.roomwordsample

import android.app.Application
import com.example.roomwordsample.db.WordDatabase
import com.example.roomwordsample.db.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * For only one instance of the database and of the repository in the app.
 * An easy way to achieve this is by creating them as members of the Application class.
 * Then they will just be retrieved from the Application whenever they're needed, rather than constructed every time.
 */



class WordApplications: Application() {
    /**
     *  Using by lazy so the database and the repository are only created when they're needed
    rather than when the application starts
     */

    //private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { WordDatabase.invoke(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}