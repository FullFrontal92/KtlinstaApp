package com.example.kotlininstagramfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {


    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener {  }
    }


    fun sign_in(view : View){

    }

    fun sign_up(view: View){

    }
}
