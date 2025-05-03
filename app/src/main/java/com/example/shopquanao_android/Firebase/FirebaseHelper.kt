package com.example.shopquanao_android.Firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.shopquanao_android.model.CartItem
import com.example.shopquanao_android.model.Order
import com.example.shopquanao_android.model.Product
import com.example.shopquanao_android.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//Router Wi-Fi Băng Tần Kép 4G LTE AC1200
//MR90X
//Router Wi-Fi 6 AX6000 8-Stream
//ROUTER WIFI CHUẨN N TỐC ĐỘ 300MBPS TP-LINK TL-WR841N
//Switch 8 Port TP-Link TL-SG108 (8 Port 10/100/1000 Vỏ kim loại)
//APTEK SG1240 - Switch 24 port Gigabit unmanaged
object FirebaseHelper{
    fun registerUser(email: String, password: String, name: String, phone: String){
        val auth = FirebaseAuth.getInstance()
        val dbref = FirebaseDatabase.getInstance().getReference("user")
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                Log.d("auth", "success")
                val uid = auth.currentUser?.uid
                val user: User = User(uid, name, phone)

                uid?.let {
                    dbref.child(it).setValue(user).addOnSuccessListener {
                        //Toast.makeText(applicationContext, "registersuccess", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener {
                            //Toast.makeText(applicationContext, "rgister fail", Toast.LENGTH_SHORT).show()
                        }
                }


            }else{
                Log.d("auth", "no success")
                //Toast.makeText(applicationContext, "rgister fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun signInUser(email: String, password: String, onSuccess: ()-> Unit, onFail: () -> Unit){
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    onSuccess()
                  //  Toast.makeText(applicationContext, "Sign in success", Toast.LENGTH_SHORT).show()
                }else{
                    onFail()
                    //Toast.makeText(applicationContext, "Sign in fail", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signOutUser(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        //Toast.makeText(applicationContext, "Sign out success", Toast.LENGTH_SHORT).show()
    }

    fun fetchAllProductData(onSuccess: (List<Product>) -> Unit, onFail: (Exception) -> Unit){
        val dbref = FirebaseDatabase.getInstance().getReference("product")
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var list = mutableListOf<Product>()
                for(data in snapshot.children){
                    val productdata = data.getValue(Product::class.java)
                    productdata?.let { list.add(it) }
                }
                onSuccess(list)
            }

            override fun onCancelled(error: DatabaseError) {
                onFail(error.toException())
            }
        })
    }

    fun fetchProductByID(productId: String,onSuccess: (Product) -> Unit){
        val dbref = FirebaseDatabase.getInstance().getReference("product")
        dbref.child(productId).get().addOnSuccessListener {
                datasnapshot -> if(datasnapshot.exists()){
            val product: Product? = datasnapshot.getValue(Product::class.java)
            product?.let { onSuccess(it) }
        }
        }.addOnFailureListener {
            notifyInformation("fetch error")
        }
    }

    fun saveProductData(list: List<Product>){
        val dbref = FirebaseDatabase.getInstance().getReference("product")
        for(data in list){
            val pid = dbref.push().key
            data.id = pid
            if(pid!= null){
                dbref.child(pid).setValue(data)
            }
        }
    }

    fun saveCartItem(userId: String, cartItem: CartItem){
        val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("user")
            .child(userId).child("cart")
        val itemId = cartItem.product_id
        itemId?.let { dbref.child(it).setValue(cartItem).addOnSuccessListener{
            //Toast.makeText(applicationContext, "add item success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            //Toast.makeText(applicationContext, "add item fail", Toast.LENGTH_SHORT).show()
        } }
    }

    fun fetchCartItems(userID: String, onSuccess: (List<CartItem>)-> Unit, onFailure: (Exception)-> Unit){
        val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("user")
            .child(userID).child("cart")
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CartItem>()
                for(data in snapshot.children){
                    val cartitem = data.getValue(CartItem::class.java)
                    cartitem?.let { list.add(it) }
                }
                onSuccess(list)
            }
        })
    }

    fun saveOrder(order: Order){
        val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("order")
        val orderId = dbref.push().key
        order.id = orderId
        orderId?.let {
            dbref.child(it).setValue(order).addOnSuccessListener {
                notifyInformation("save order success")
            }.addOnFailureListener {
                notifyInformation("save order fail")
            }
        }
    }

    fun fetchOrderbyID(orderId: String, onSuccess: (Order)-> Unit, onFailure: (Exception)-> Unit){
        val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("order")
            .child(orderId)
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val order = snapshot.getValue(Order::class.java)
                order?.let { onSuccess(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.toException())
            }
        })
    }

    fun fetchOrderbyUserID(userId: String, onSuccess: (List<Order>)-> Unit, onFailure: (Exception)-> Unit){
        val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("order")
        dbref.orderByChild("user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Order>()
                    for(data in snapshot.children){
                        val order = data.getValue(Order::class.java)
                        order?.let { list.add(it) }
                    }
                    onSuccess(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.toException())
                }
            })
    }

    fun notifyInformation(string: String){
       // Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT).show()
    }
    fun getDrawableId(context: Context, imageName: String): Int{
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}