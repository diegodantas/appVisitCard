package com.ddn.visitcard

import android.app.Application
import com.ddn.visitcard.data.AppDatabase
import com.ddn.visitcard.data.BusinessCardRepository

class App  : Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}