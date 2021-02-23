package com.asfartz.picturelistingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberedAdapter(count: Int, private val listener: OnItemClickListener?) : RecyclerView.Adapter<NumberedAdapter.TextViewHolder>() {
    private val labels: MutableList<String>

    init {
        labels = ArrayList(count)
        for (i in 0 until count) {
            labels.add(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.number_item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val label = labels[position]
        holder.textView.setText(label)

        if (listener != null) {
            holder.textView.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return labels.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView as TextView
    }
}



