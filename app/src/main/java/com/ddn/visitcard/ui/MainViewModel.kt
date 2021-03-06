package com.ddn.visitcard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ddn.visitcard.data.BusinessCard
import com.ddn.visitcard.data.BusinessCardRepository
import kotlinx.coroutines.launch

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)
    }

    fun getAll() : LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll()
    }

    fun removeItem(businessCard: BusinessCard) {
        viewModelScope.launch {
            businessCardRepository.remove(businessCard)
        }
    }

    fun get(card: BusinessCard) : BusinessCard? {
        return businessCardRepository.get(card.id)
    }

}

class MainViewModelFactory(private val repository: BusinessCardRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}