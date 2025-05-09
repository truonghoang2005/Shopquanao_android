package com.example.shopquanao_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            setButtonSignUp()
        }
    }

    private fun setButtonSignUp() {
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassWord = findViewById<EditText>(R.id.edtPassword)
        val edtConfirmPassword = findViewById<EditText>(R.id.edtConfirmPassword)

        val name = edtName.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val pass = edtPassWord.text.toString().trim()
        val confirmPass = edtConfirmPassword.text.toString().trim()

        when{
            name.isEmpty() -> edtName.error = "Vui lòng nhập tên"
            phone.isEmpty() -> edtPhone.error = "Vui lòng nhập số điện thoại"
            phone.length != 10 -> edtPhone.error = "Vui lòng nhập 10 số"
            !phone.startsWith("0") -> edtPhone.error = "Vui lòng nhập đúng định dạng"
            email.isEmpty() -> edtEmail.error = "Vui lòng nhập email"
            pass.isEmpty() -> edtPassWord.error = "Vui lòng nhập mật khẩu"
            pass.length < 6 -> edtPassWord.error = "Vui lòng nhập mật khẩu hơn 6 kí tự"
            confirmPass.isEmpty() -> edtConfirmPassword.error = "Vui lòng nhập lại mật khẩu"
            pass.compareTo(confirmPass) != 0 -> edtConfirmPassword.error = "Nhập lại mật khẩu không đúng"
            else ->{
                FirebaseHelper.registerUser(email, pass, name, phone, {
                    Toast.makeText(applicationContext, "Đăng ký thất bại, email không đúng định dạng hoặc đã tồn tại", Toast.LENGTH_SHORT).show()
                }, {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(applicationContext, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                })

            }
        }

    }
}