package com.learning.acronyms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.acronym_item.view.*

class AcronymRecyclerViewAdapter : RecyclerView.Adapter<AcronymRecyclerViewAdapter.ItemViewHolder>()  {

    private var acronyms:List<Acronym>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =  ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.acronym_item, parent,false))

    override fun getItemCount(): Int = acronyms?.size ?: 0

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        acronyms?.let {
            holder.bindData(it[position])
        }
    }

    fun setData(acronyms:List<Acronym>) {
        this.acronyms = acronyms
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        fun bindData(acronym:Acronym) {
             itemView.acronymName.text = acronym.lf
        }
    }
}