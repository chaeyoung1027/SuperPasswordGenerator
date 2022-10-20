package com.example.superpasswordgenerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SaveListAdapter(val context: Context, val saveList: ArrayList<Save>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.save_item, null)

        val password = view.findViewById<TextView>(R.id.pw)
        val site = view.findViewById<TextView>(R.id.site)
        val id = view.findViewById<TextView>(R.id.id)

        password.text = saveList.toString()
        site.text = saveList.toString()
        id.text = saveList.toString()

        return view
    }

    override fun getCount(): Int {
        return saveList.size
    }

    override fun getItem(position: Int): Any {
        return saveList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

}