package com.project1forcreativeitinstitute.orrio.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project1forcreativeitinstitute.orrio.core.DataState
import com.project1forcreativeitinstitute.orrio.data.models.UserLogIn
import com.project1forcreativeitinstitute.orrio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthRepository
) : ViewModel() {

    private val _loginResponse= MutableLiveData<DataState<UserLogIn>>()
    val loginResponse : LiveData<DataState<UserLogIn>> =_loginResponse
    fun userLogin(userLogin: UserLogIn){
        _loginResponse.postValue(DataState.Loading())



        authService.userLogin(userLogin).addOnSuccessListener {
            _loginResponse.postValue(DataState.Success(userLogin))
            Log.d("TAG", "userLogin: Success ")

        }.addOnFailureListener {
            _loginResponse.postValue(DataState.Error("${it.message}"))
            Log.d("TAG", "userLogin:${it.message} ")
        }
    }
}