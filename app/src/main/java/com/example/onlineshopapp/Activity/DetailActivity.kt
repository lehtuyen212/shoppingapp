package com.example.onlineshopapp.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshopapp.Adapter.PicListAdapter
import com.example.onlineshopapp.Adapter.SizeListAdapter
import com.example.onlineshopapp.Helper.ManagmentCart
import com.example.onlineshopapp.Model.ItemsModel
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOder=1
    private lateinit var managentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managentCart=ManagmentCart(this)

        getBundle()
        initList()
    }

    private fun initList() {
        val  sizeList=ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter=SizeListAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList=ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)
        binding.picList.adapter=PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.SellerNameTxt.text=item.sellerName

        binding.AddtoCartBtn.setOnClickListener{
            item.numberInCart=numberOder
            managentCart.insertItems(item)
        }

        binding.backBtn.setOnClickListener{ finish() }
        binding.CartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))

        }
        Glide.with(this)
            .load(item.sellerPic)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(binding.picSeller)
        binding.msgToSellerBtn.setOnClickListener {
            val sendIntent=Intent(Intent.ACTION_VIEW)
            sendIntent.setData(Uri.parse("sms:"+item.sellerTell))
            sendIntent.putExtra("sms_body", "type your message")
            startActivity(intent)
        }
        binding.callToSellerBtn.setOnClickListener {
            val phone=item.sellerTell.toString()
            val intent=Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
    }
}