/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.friends

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.findNavController
import jemmy.calak.tooltracking.data.repository.UserRepository
import jemmy.calak.tooltracking.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendsViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var listUserMutable: LiveData<List<User>> = MutableLiveData()
    val _listUsers = MediatorLiveData<List<User>>()
    val listUsers: LiveData<List<User>> get() = _listUsers

    init {
        getListFriends()
    }

    private fun getListFriends() {
        viewModelScope.launch(Dispatchers.Main) {
            _listUsers.removeSource(listUserMutable)
            withContext(Dispatchers.IO){ listUserMutable = repository.getListUsers() }
            _listUsers.addSource(listUserMutable){
                _listUsers.value = it
            }
        }
    }

    fun navigateToDetailFriend(v:View, model: User) {
        v.findNavController().navigate(FriendsFragmentDirections.actionFriendToFriendDetailFragment(model))
    }
}
