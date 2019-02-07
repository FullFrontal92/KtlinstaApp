package com.example.kotlininstagramfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    var useremailFromFB : ArrayList<String> = ArrayList<String>()
    var userImageFromFB : ArrayList<String> = ArrayList<String>()
    var userCommentFromFB : ArrayList<String> = ArrayList<String>()
    var firebaseDatabae : FirebaseDatabase? = null
    var myRef : DatabaseReference? = null
    var adapter : PostClass? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_post,menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.add_post){
            val intent = Intent(applicationContext,UploadActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        firebaseDatabae = FirebaseDatabase.getInstance()
        myRef = firebaseDatabae!!.getReference()

        adapter = PostClass(useremailFromFB, userImageFromFB, userCommentFromFB,this)

        listView.adapter = adapter

        getDataFromFirebase()
    }

    fun getDataFromFirebase(){

        val newReference = firebaseDatabae!!.getReference("Posts")

        newReference.addValueEventListener(object :  ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                adapter!!.clear()
                userImageFromFB.clear()
                userCommentFromFB.clear()
                useremailFromFB.clear()

                for (snapshot in p0.children){
                    val hashMap = snapshot.value as HashMap<String,String>

                    if (hashMap.size > 0){

                        val email = hashMap["useremail"]
                        val comment = hashMap["comment"]
                        val image = hashMap["downloadurl"]

                        if (email != null) {
                            useremailFromFB.add(email)
                        }

                        if (comment != null){
                            userCommentFromFB.add(comment)
                        }

                        if (image != null){
                            userImageFromFB.add(image)
                        }

                        adapter!!.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
