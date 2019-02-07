package com.example.kotlininstagramfirebase

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

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

        mAuth!!.signInWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val intent = Intent(applicationContext,FeedActivity::class.java)
                    startActivity(intent)
                }
            }


    }

    fun sign_up(view: View){

        mAuth!!.createUserWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    Toast.makeText(applicationContext,"User Created", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,FeedActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { exception ->

                if (exception != null) {
                    Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
                }
            }

    }
}
