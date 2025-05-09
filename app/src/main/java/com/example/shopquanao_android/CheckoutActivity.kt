package com.example.shopquanao_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.model.Order
import com.example.shopquanao_android.model.OrderItem
import com.google.firebase.auth.FirebaseAuth

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)

        val user = FirebaseAuth.getInstance().currentUser
        if(user == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Đặt hàng"
        val intent = intent
        val product_id = intent.getStringExtra("product_id")
        Toast.makeText(this, product_id, Toast.LENGTH_SHORT).show()
        product_id?.let { setButtonOrder(it) }
    }

    private fun setButtonOrder(product_id: String) {
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtAddress = findViewById<EditText>(R.id.edtAddress)
        val intent = Intent(this, MainActivity::class.java)
        btnOrder.setOnClickListener {
            val phone = edtPhone.text.toString().trim()
            val address = edtAddress.text.toString().trim()
//            phone.length != 10 -> edtPhone.error = "Vui lòng nhập 10 số"
//            !phone.startsWith("0") -> edtPhone.error = "Vui lòng nhập đúng định dạng"
            if(phone.isEmpty()){
                edtPhone.error = "Vui lòng nhập số điện thoại"
            }else if(address.isEmpty()){
                edtAddress.error = "Vui lòng nhập địa chỉ nhận hàng"
            }else if(phone.length != 10){
                edtPhone.error = "Vui lòng nhập 10 số"
            }else if(!phone.startsWith("0")){
                edtPhone.error = "Vui lòng nhập đúng định dạng"
        }else{
                FirebaseHelper.fetchProductByID(product_id, {
                    product ->
                    val orderitem = OrderItem(product.id, product.name, product.photo, product.price)
                    val list: List<OrderItem> = listOf(orderitem)
                    val order = Order("1", FirebaseAuth.getInstance().currentUser!!.uid, phone,address, list, product.price, "Pending")
                    FirebaseHelper.saveOrder(order)
                    startActivity(intent)
                    Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}