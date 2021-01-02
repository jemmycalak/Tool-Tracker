/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.tools

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import jemmy.calak.tooltracking.model.Tools
import jemmy.calak.tooltracking.data.repository.ToolsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListToolsViewModel @ViewModelInject constructor(
    private val toolRepository: ToolsRepository
) : ViewModel() {

    var listToolMutable: LiveData<List<Tools>> = MutableLiveData()
    val _listTools = MediatorLiveData<List<Tools>>()
    val listTools: LiveData<List<Tools>> get() = _listTools

    init {
        getListTools()
    }

    fun getListTools() {
        viewModelScope.launch(Dispatchers.Main) {
            _listTools.removeSource(listToolMutable)
            withContext(Dispatchers.IO) { listToolMutable = toolRepository.getListTools() }
            _listTools.addSource(listToolMutable) {
                _listTools.value = it
            }
        }
    }

    fun navigateToDetailTool(v:View, model: Tools) {
        v.findNavController().navigate(
            ListToolsFragmentDirections.actionNavigationHomeToDetailToolsFragment(model)
        )
    }

}