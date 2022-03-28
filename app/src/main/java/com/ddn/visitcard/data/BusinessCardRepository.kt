package com.ddn.visitcard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao){

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    suspend fun remove(businessCard: BusinessCard) {
        dao.delete(businessCard)
    }

    fun getAll() = dao.getAll()

    fun get(cardId: Int): BusinessCard? = dao.get(cardId)

}