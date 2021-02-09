package com.asfartz.picturelistingapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PictureAdapter(private val pictureList: List<String>) :
    RecyclerView.Adapter<PictureAdapter.MyViewHolder>() {

    private val TAG = "PictureAdapter"
    private val picasso: Picasso = Picasso.get()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemUrl: TextView = view.findViewById(R.id.pictureItemUrl)
        val itemImageView: ImageView = view.findViewById(R.id.pictureItemImageView)
        val itemProgressBar: ProgressBar = view.findViewById(R.id.pictureItemProgressBar)
    }

    // using the received views, create ViewHolders (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_item, parent, false)

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        holder.itemUrl.text = pictureList.get(position)

        picasso.load(pictureList.get(position))
            .into(holder.itemImageView, object : Callback {
                override fun onSuccess() {
                    holder.itemImageView.visibility = View.VISIBLE
                    holder.itemProgressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Log.d(TAG, "onError: e = " + e?.localizedMessage)
                    holder.itemProgressBar.visibility = View.GONE
                    holder.itemImageView.setImageResource(R.drawable.ic_error)
                }
            })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return pictureList.size
    }

}