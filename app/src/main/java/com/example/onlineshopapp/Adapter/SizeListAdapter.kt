package com.example.onlineshopapp.Adapter
import android.annotation.SuppressLint
import com.example.onlineshopapp.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshopapp.databinding.ViewholderPicListBinding
import com.example.onlineshopapp.databinding.ViewholderSizeBinding

class SizeListAdapter(val items:MutableList<String>):
    RecyclerView.Adapter<SizeListAdapter.Viewholder>() {
    private var selectedPostion=-1
    private var lastSelectedPosition=-1
    private lateinit var context:Context


    inner class Viewholder(val binding:ViewholderSizeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListAdapter.Viewholder {
        context=parent.context
        var binding=ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SizeListAdapter.Viewholder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.sizeTxt.text=items[position]
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPostion
            selectedPostion=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPostion)

        }
        if (selectedPostion==position){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg3)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int = items.size
}