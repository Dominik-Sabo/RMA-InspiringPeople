package com.sabo.dominik.inspiringpeople

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class PersonViewHolder(itemView: View, private val clickInterface: PersonClickInterface) :
    ViewHolder(itemView), View.OnClickListener {
    private val ivPicture: ImageView = itemView.findViewById(R.id.ivPicture)
    private val ivRemove: ImageView = itemView.findViewById(R.id.ivRemove)
    private val tvName: TextView = itemView.findViewById(R.id.tvName)
    private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

    fun setInfo(person: InspiringPerson) {
        tvName.text = person.name
        tvTime.text = person.time
        tvDescription.text = person.description
        ivPicture.setImageBitmap(person.picture)
    }

    override fun onClick(view: View?) {
        if(view == ivPicture) clickInterface.onPersonClick(adapterPosition)
        else if (view == ivRemove) clickInterface.onRemoveClick(adapterPosition)
    }

    init {
        ivPicture.setOnClickListener(this)
        ivRemove.setOnClickListener(this)
    }
}
