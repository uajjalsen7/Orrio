package com.project1forcreativeitinstitute.orrio.data.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.project1forcreativeitinstitute.orrio.data.models.UserLogIn
import com.project1forcreativeitinstitute.orrio.data.models.UserRegistration

interface AuthService {

    fun userRegistration(user: UserRegistration): Task<AuthResult>
    fun userLogin(user: UserLogIn): Task<AuthResult?>
    fun createUser(user: UserRegistration): Task<Void>
}