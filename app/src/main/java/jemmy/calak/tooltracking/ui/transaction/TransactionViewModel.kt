/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.transaction

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import jemmy.calak.tooltracking.data.repository.BorrowedRepository
import jemmy.calak.tooltracking.data.repository.ToolsRepository
import jemmy.calak.tooltracking.model.TransactionModel
import jemmy.calak.tooltracking.utils.STATUS_BORROWED
import jemmy.calak.tooltracking.utils.STATUS_RETURNED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel @ViewModelInject constructor(
    private val repository: BorrowedRepository,
    private var toolsRepository: ToolsRepository
) : ViewModel() {

    var listTransaction: LiveData<List<TransactionModel>> = MutableLiveData()
    val _listTransaction = MediatorLiveData<List<TransactionModel>>()
    val dataTransaction: LiveData<List<TransactionModel>> get() = _listTransaction

    fun getListTransaction() {
        viewModelScope.launch(Dispatchers.Main) {
            _listTransaction.removeSource(listTransaction)
            withContext(Dispatchers.IO) {
                listTransaction = repository.getListBorrower()
            }
            _listTransaction.addSource(listTransaction) {
                _listTransaction.value = it
            }
        }
    }

    fun getListTransactionByUserId(user: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _listTransaction.removeSource(listTransaction)
            withContext(Dispatchers.IO) {
                listTransaction = repository.getListBorrowedByUserId(user)
            }
            _listTransaction.addSource(listTransaction) {
                _listTransaction.value = it
            }
        }
    }

    fun returnTools(model: TransactionModel) {
        viewModelScope.launch(Dispatchers.Main) {
            model.borrowed.status = STATUS_RETURNED
            repository.updateBorrowed(model.borrowed)

            model.tool.quantity = (model.tool.quantity!! + model.borrowed.value)
            model.tool.quantityBorrowed = (model.tool.quantityBorrowed!! - model.borrowed.value)
            toolsRepository.updateTools(model.tool)
        }
    }

}