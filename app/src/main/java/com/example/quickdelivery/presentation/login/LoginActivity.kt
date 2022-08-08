package com.example.quickdelivery.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quickdelivery.BaseApp
import com.example.quickdelivery.presentation.MainActivity
import com.example.quickdelivery.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var tasarim:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        auth= FirebaseAuth.getInstance()

        //Uygulama her açıldıgında giriş sayfasına atmaması için giriş bilgisini tutuyoruz
       /* val guncelKullanici = auth.currentUser

        if(guncelKullanici != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }*/

        kayitOl()
        girisYap()

    }

    fun girisYap(){
        tasarim.buttonGirisYap.setOnClickListener {
            auth.signInWithEmailAndPassword(tasarim.emailEditText.text.toString(),tasarim.passEditText.text.toString())
                .addOnCompleteListener {  task ->
                    if (task.isSuccessful){
                        val guncelKullanici = auth.currentUser?.email.toString()
                        Toast.makeText(this,"Hoşgeldin : ${guncelKullanici}",Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
                }

        }
    }
    fun kayitOl(){
        tasarim.buttonKayiOl.setOnClickListener {
            val email = tasarim.emailEditText.text.toString()
            val sifre = tasarim.passEditText.text.toString()

            auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener { task ->
               //asenkron
                if(task.isSuccessful){
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}