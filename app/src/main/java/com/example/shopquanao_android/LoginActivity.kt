package com.example.shopquanao_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopquanao_android.Firebase.FirebaseHelper
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            SignInUser()
        }

    }

    private fun SignInUser() {
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassWord = findViewById<EditText>(R.id.edtPassword)
        val txtErrEmail = findViewById<TextView>(R.id.txtErrEmail)
        val txtErrPassWord = findViewById<TextView>(R.id.txtErrPass)
        val email = edtEmail.text.toString().trim()
        val password = edtPassWord.text.toString().trim()
        if(email.isEmpty()){
            edtEmail.error ="Email là bắt buộc"
            txtErrPassWord.visibility = View.GONE
        }else if(password.isEmpty()){
            edtEmail.error ="Mật khẩu là bắt buộc"
            txtErrPassWord.visibility = View.GONE
        }else{
            FirebaseHelper.signInUser(email, password,{
                Toast.makeText(applicationContext, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                //findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_home)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, {
                Toast.makeText(applicationContext, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show()
            })

        }
        val user = FirebaseAuth.getInstance().currentUser
        if(user!= null){

        }
    }
}