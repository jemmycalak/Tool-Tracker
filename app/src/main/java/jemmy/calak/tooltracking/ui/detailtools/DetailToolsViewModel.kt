/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.detailtools

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import jemmy.calak.tooltracking.data.repository.BorrowedRepository
import jemmy.calak.tooltracking.data.repository.ToolsRepository
import jemmy.calak.tooltracking.model.Borrowed
import jemmy.calak.tooltracking.model.Tools
import jemmy.calak.tooltracking.model.UserBorrowed
import jemmy.calak.tooltracking.utils.STATUS_BORROWED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailToolsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val borrowedRepository: BorrowedRepository,
    private val toolsRepository: ToolsRepository
) : ViewModel() {

    val friends = MutableLiveData<String>()
    val quantiry = MutableLiveData<Int>()

    var listBorrowed: LiveData<List<UserBorrowed>> = MutableLiveData()
    val _listBorrowed = MediatorLiveData<List<UserBorrowed>>()
    val dataBorrowed: LiveData<List<UserBorrowed>> get() = _listBorrowed

    var tools: LiveData<Tools> = MutableLiveData()

    fun getTools(idTools:String) {
        tools = toolsRepository.getTools(idTools)
    }

    fun getListTools(idTools: String) {
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.Main) {
                _listBorrowed.removeSource(listBorrowed)
                withContext(Dispatchers.IO) {
                    listBorrowed = borrowedRepository.getListUserByIdTools(idTools)
                }
                _listBorrowed.addSource(listBorrowed) {
                    _listBorrowed.value = it
                }
            }
        }
    }

    fun lendTools(tools: Tools) = viewModelScope.launch(Dispatchers.Main) {
        borrowedRepository.onInsertBorrowed(Borrowed(idTools=tools.nameTool, idUser = friends.value!!, value = quantiry.value!!, status = STATUS_BORROWED))

        tools.quantity = (tools.quantity!! - quantiry.value!!)
        tools.quantityBorrowed = (tools.quantityBorrowed!! + quantiry.value!!)
        toolsRepository.updateTools(tools)
    }

}
