package com.project1forcreativeitinstitute.orrio.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project1forcreativeitinstitute.orrio.core.DataState
import com.project1forcreativeitinstitute.orrio.data.models.UserRegistration
import com.project1forcreativeitinstitute.orrio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authService : AuthRepository
)  : ViewModel() {

   private val _registrtionResponse = MutableLiveData<DataState<UserRegistration>>()
    val registrationResponse : LiveData<DataState<UserRegistration>> = _registrtionResponse
    fun userRegistration(user: UserRegistration){
        _registrtionResponse.postValue(DataState.Loading())
        authService.userRegistration(user).addOnSuccessListener {
            it.user?.let { createdUser->
                user.userID =createdUser.uid

                authService.createUser(user).addOnSuccessListener {
                    _registrtionResponse.postValue(DataState.Success(user))
                    Log.d("TAG", "userRegistration: Success")
                }.addOnFailureListener {error->

                    _registrtionResponse.postValue(DataState.Error("${error.message}"))
                    Log.d("TAG", "userRegistration: ${error.message}")

                }
            }

        }.addOnFailureListener { error->
            _registrtionResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userRegistration: ${error.message}")

        }
    }
}