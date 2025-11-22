package com.project1forcreativeitinstitute.orrio.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project1forcreativeitinstitute.orrio.core.Nodes
import com.project1forcreativeitinstitute.orrio.data.models.UserLogIn
import com.project1forcreativeitinstitute.orrio.data.models.UserRegistration
import com.project1forcreativeitinstitute.orrio.data.services.AuthService
import jakarta.inject.Inject

class AuthRepository @Inject constructor(
    private val jAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthService {
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {

        return jAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: UserLogIn): Task<AuthResult?> {

        return jAuth.signInWithEmailAndPassword(user.email, user.password)

    }

    override fun createUser(user: UserRegistration): Task<Void> {


        return   db.collection(Nodes.USER).document(user.userID).set(user)

    }
}





