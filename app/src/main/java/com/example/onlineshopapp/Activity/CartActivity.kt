package com.example.onlineshopapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopapp.Adapter.CartAdapter
import com.example.onlineshopapp.Helper.ChangeNumberItemsListener
import com.example.onlineshopapp.Helper.ManagmentCart
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.ActivityCartBinding
import com.example.onlineshopapp.databinding.ViewholderCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managerCart: ManagmentCart
    private var tax: Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerCart= ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun calculateCart() {
        val percentTax=0.02
        val delivery=15.0
        tax=Math.round((managerCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managerCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal= Math.round(managerCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }

    }

    private fun initCartList() {
        binding.cartView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter= CartAdapter(managerCart.getListCart(),this, object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}