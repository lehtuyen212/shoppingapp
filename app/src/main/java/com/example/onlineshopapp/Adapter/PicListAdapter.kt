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

class PicListAdapter(val items:MutableList<String>, var picMain:ImageView):
    RecyclerView.Adapter<PicListAdapter.Viewholder>() {
    private var selectedPostion=-1
    private var lastSelectedPosition=-1
    private lateinit var context:Context


    inner class Viewholder(val binding:ViewholderPicListBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.Viewholder {
        context=parent.context
        var binding=ViewholderPicListBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }
    override fun onBindViewHolder(holder: PicListAdapter.Viewholder, @SuppressLint("RecyclerView") position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.picList)
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPostion
            selectedPostion=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPostion)
            Glide.with(holder.itemView.context)
                .load(items[position])
                .into(picMain)
        }
        if (selectedPostion==position){
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size
}