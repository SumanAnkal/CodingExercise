package com.accenture.codingexercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.codingexercise.R
import com.bumptech.glide.Glide
import com.task.accenturelistviewtask.network.model.Row
import kotlinx.android.synthetic.main.single_list_item.view.*

/**
 * Custom Adapter for RecyclerView.
 */
class CustomAdapter(private val context: Context) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var mList: List<Row> = ArrayList()

    /**
     * Method to set data to list and refresh the list
     */
    fun setDataToList(list: List<Row>) {
        this.mList = list
        notifyDataSetChanged()  // To refresh the list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].title
        holder.description.text = mList[position].description
        //Glide library for Loads the images lazily.
        Glide.with(context)
            .asDrawable()
            .load(mList[position].imageHref)
            .into(holder.image)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.title!!
        val description = v.description!!
        val image = v.image!!
    }
}