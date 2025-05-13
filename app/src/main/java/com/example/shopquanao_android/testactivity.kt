/*package com.example.shopquanao_android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

class testactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_testactivity)

/*----------------- lay duong dan len hinh */
//        var st = this.resources.getIdentifier("avatars", "drawable", this.packageName)
//        val bt4 = findViewById<Button>(R.id.button4)
//        val txt4 = findViewById<TextView>(R.id.textView4)
//        val img = findViewById<ImageView>(R.id.imageView5)
//        bt4.setOnClickListener {
//            txt4.setText(st.toString())
//            img.setImageResource(st)
//        }

        /*----------------- lay, day du lieu thu nghiem */
        //val dbref = FirebaseDatabase.getInstance().getReference("user")
//        var user: user = user("124", "lo tahng")
//        dbref.child(user.id).setValue(user).addOnCompleteListener {
    //}

//        dbref.child("123").child("name").get().addOnSuccessListener {
//            snap -> var st= snap.value as? String
//            Toast.makeText(this, st, Toast.LENGTH_SHORT).show()
//        }

//        dbref.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val list = mutableListOf<user>()
//                for(data in snapshot.children){
//                    val userdata = data.getValue(user::class.java)
//                    userdata?.let {
//                        list.add(it)
//                        Toast.makeText(applicationContext, it.id, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//        Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT).show()
//        registerUser("ab@gmail.com", "123456", "thang", "0923847584")

// thu dang nhap dang ky
//        val auth = FirebaseAuth.getInstance()
//        val uid = auth.currentUser?.uid
//        if(uid != null){
//            Toast.makeText(applicationContext, "sign in", Toast.LENGTH_SHORT).show()
//            signOutUser()
//        }else{
//            Toast.makeText(applicationContext, "sign out", Toast.LENGTH_SHORT).show()
//            signInUser("ab@gmail.com", "12345")
//        }
//test lay du lieu san pham
//Router Wi-Fi Băng Tần Kép 4G LTE AC1200
//MR90X
//Router Wi-Fi 6 AX6000 8-Stream
//ROUTER WIFI CHUẨN N TỐC ĐỘ 300MBPS TP-LINK TL-WR841N
//Switch 8 Port TP-Link TL-SG108 (8 Port 10/100/1000 Vỏ kim loại)
//APTEK SG1240 - Switch 24 port Gigabit unmanaged
//        var list: List<Product> = listOf(
//            Product("1", "Router LTE AC1200", "Router Wi-Fi Băng Tần Kép 4G LTE AC1200", "router","router1", 200.0),
//            Product("1", "Router AX6000 8-Stream", "Router Wi-Fi 6 AX6000 8-Stream", "router","router2", 210.0),
//            Product("1", "ROUTER TP-LINK TL-WR841N", "ROUTER WIFI CHUẨN N TỐC ĐỘ 300MBPS TP-LINK TL-WR841N", "router","router3", 220.0),
//            Product("1", "Switch 8 Port TP-Link TL-SG108", "Switch 8 Port TP-Link TL-SG108 (8 Port 10/100/1000 Vỏ kim loại)", "switch","switch1", 230.0),
//            Product("1", "APTEK SG1240", "APTEK SG1240 - Switch 24 port Gigabit unmanaged", "switch","switch2", 245.0)
//        )
//        saveProductData(list)

//        fetchAllProductData({
//            list ->
//            Toast.makeText(applicationContext, list[2].description, Toast.LENGTH_SHORT).show()
//        }, {
//            err -> err?.let { Toast.makeText(applicationContext,err.message, Toast.LENGTH_SHORT).show() }
//        })

// thu cart
        //FirebaseAuth.getInstance().currentUser?.let { saveCartItem(it.uid, CartItem("1", "router 2901", "avatar", 100.0)) }
//        saveCartItem("123", cartItem("2", "router 3911", "avatar", 200.0))
//        fetchCartItems("123", {
//            list ->
//            Toast.makeText(applicationContext, list[1].product_id, Toast.LENGTH_SHORT).show()
//        }, {
//            err -> Toast.makeText(applicationContext, err.message, Toast.LENGTH_SHORT).show()
//        })
// thu order
//        val listOrderitem: List<orderItem> = listOf(
//            orderItem("4", "switch 6", "switch", 300.0),
//            orderItem("5", "switch 7", "switch", 400.0)
//        )
//        val order: order = order("1", "124", "0847587647",
//            "đà nẵng", listOrderitem, 700.0)
//        saveOrder(order)

//        fetchOrderbyID("-OPAE7FtnvKHgr515deZ", { order->
//            val list: List<orderItem>? = order.orderItem
//            list?.let { notifyInformation(it[0].product_name.toString()) }
//        }, { err ->
//            notifyInformation(err.message.toString())
//        })

//        fetchOrderbyUserID("124", { list ->
//            for(order in list){
//                notifyInformation(order.totalPrice.toString())
//            }
//        }, { err ->
//            notifyInformation(err.message.toString())
//        })

        registerUser("abta@gmail.com", "123456", "anbt", "0384758392")
    }
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
                        Toast.makeText(applicationContext, "registersuccess", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(applicationContext, "rgister fail 9898", Toast.LENGTH_SHORT).show()
                    }
                }


            }else{
                Log.d("auth", "no success")
                Toast.makeText(applicationContext, "rgister fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun signInUser(email: String, password: String){
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "Sign in success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Sign in fail", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signOutUser(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        Toast.makeText(applicationContext, "Sign out success", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(applicationContext, "add item success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "add item fail", Toast.LENGTH_SHORT).show()
        } }
    }

    fun fetchCartItems(userID: String,onSuccess: (List<CartItem>)-> Unit, onFailure: (Exception)-> Unit){
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
        Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT).show()
    }
}

//class user(val id: String? = null,
//           val name: String? = null,
//           val phone: String? = null){}
//class product(
//    var id:String? = null,
//    val name: String? = null,
//    val description: String? = null,
//    val category: String?= null,
//    val photo: String? = null,
//    val price:Double? = null){}
//class cart(
//    val id: String? = null,
//    val cartItem: List<cartItem>? = null
//){}
//class cartItem(
//    val product_id: String? = null,
//    val product_name: String? = null,
//    val photo: String? = null,
//    val product_price: Double? = null
//){}
//class orderItem(
//    val product_id: String? = null,
//    val product_name: String? = null,
//    val photo: String? = null,
//    val product_price: Double? = null
//){}
//class order(
//    var id: String? = null,
//    val user_id: String? = null,
//    val user_phonenumber: String? = null,
//    val address: String? = null,
//    val orderItem: List<orderItem>? = null,
//    val totalPrice: Double? = null
//){}



*/