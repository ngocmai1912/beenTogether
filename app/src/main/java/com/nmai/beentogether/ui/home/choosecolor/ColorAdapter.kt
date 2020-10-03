package com.nmai.beentogether.ui.home.choosecolor

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nmai.beentogether.R
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.repository.color.ColorUser
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber

class ColorAdapter(
    val context : Context,
    val colorCurrent : ColorUser
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>(){
    var currentColorCode = colorCurrent

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var btnChoose : ImageView = itemView.findViewById(R.id.btn_choose)
        var imageView : ImageView = itemView.findViewById(R.id.color_item)
        fun onBind(color: ColorUser){
            if(color.code == currentColorCode.code){
                Timber.d("changeColor123 $currentColorCode")
                btnChoose.visibility = View.VISIBLE
            }
            else {
                Timber.d("changeColor1234 $currentColorCode")
                btnChoose.visibility = View.GONE
            }
            imageView.setImageResource(color.code)
            imageView.setOnClickListener {
                currentColorCode = color
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return Data.listColor.size
    }


    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.onBind(Data.listColor[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    fun setCurrentColor(colorCode: Int){
        for(color in Data.listColor){
            if(color.code == colorCode) currentColorCode = color
        }
        notifyDataSetChanged()
    }
    fun update(){
        notifyDataSetChanged()
    }
}