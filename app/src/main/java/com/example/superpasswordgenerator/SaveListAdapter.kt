package com.example.superpasswordgenerator

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SaveListAdapter(val context: Context, val saveList: MutableList<Save>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.save_item, null)

        /*var convertView = convertView
        if (convertView == null)
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.save_item, parent, false)*/

        val password = view.findViewById<TextView>(R.id.pw)
        val site = view.findViewById<TextView>(R.id.site)
        val id = view.findViewById<TextView>(R.id.id)

        val save: Save = saveList[position]

        password.text = save.password
        site.text = save.site
        id.text = save.id

        return view
    }

    override fun getCount(): Int {
        return saveList.size
    }

    override fun getItem(position: Int): Save {
        return saveList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}