package com.example.superpasswordgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PassworAdapter(list: ArrayList<PasswordSave>) : RecyclerView.Adapter<CustomViewHolder>() {
    var pw_list : ArrayList<PasswordSave> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.password_save,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pw_list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val p = pw_list.get(position)
        holder.setHolder(p)
    }

}

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setHolder(passwordSave: PasswordSave) {
    }
}