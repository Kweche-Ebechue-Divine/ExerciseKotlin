package com.example.ddapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NewViewModel: ViewModel() {

    var username= mutableStateOf("")

    fun userLogin (email: String, password: String) {
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                username.value = email
            }
    }

    fun logoutUser () {
        Firebase.auth.signOut()
        username.value = ""

    }
}