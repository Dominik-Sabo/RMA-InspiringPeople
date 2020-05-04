package com.sabo.dominik.inspiringpeople
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*


class RecyclerAdapter(private val clickInterface: PersonClickInterface) :
    RecyclerView.Adapter<PersonViewHolder>() {
    private val dataList: MutableList<InspiringPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return PersonViewHolder(view, clickInterface)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setInfo(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addData(data: List<InspiringPerson>?) {
        dataList.clear()
        dataList.addAll(data!!)
        notifyDataSetChanged()
    }

    fun addItem(person: InspiringPerson) {
        val position = dataList.size
        dataList.add(position, person)
        notifyItemInserted(position)
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

}
