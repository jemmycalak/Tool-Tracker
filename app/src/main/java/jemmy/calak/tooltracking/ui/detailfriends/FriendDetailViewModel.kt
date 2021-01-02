/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.detailfriends

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import jemmy.calak.tooltracking.data.repository.UserRepository
import jemmy.calak.tooltracking.model.User

class FriendDetailViewModel @ViewModelInject constructor(
    val repository: UserRepository
) : ViewModel() {

    var user: LiveData<User> = MutableLiveData()

    fun getUser(idUser:String) {
        user = repository.getDetailUser(idUser)
    }
}
