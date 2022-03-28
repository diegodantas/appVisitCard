package com.ddn.visitcard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE id=:position ")
    fun get(position: Int): BusinessCard?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete
    suspend fun delete(businessCard: BusinessCard)
}