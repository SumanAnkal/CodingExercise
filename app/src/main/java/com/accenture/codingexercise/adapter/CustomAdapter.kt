package com.accenture.codingexercise.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
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

    private var mList: ArrayList<Row> = ArrayList()

    /**
     * Method to set data to list and refresh the list
     */
    fun setDataToList(list: ArrayList<Row>) {
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
        if (!TextUtils.isEmpty(mList[position].title)) {
            holder.title.text = mList[position].title
            holder.description.text = mList[position].description
            //Glide library for Loads the images lazily.
            Glide.with(context)
                .asDrawable()
                .load(mList[position].imageHref)
                .into(holder.image)
        }else{
            holder.parent.layoutParams = RelativeLayout.LayoutParams(0,0)
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.title!!
        val description = v.description!!
        val image = v.image!!
        val parent = v.parent_view
    }


    fun delete(position: Int) {
        mList.removeAt(position)
    }
}