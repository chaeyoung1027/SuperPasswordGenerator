package com.example.superpasswordgenerator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SaveListAdapter(val context: Context, val saveList: MutableList<Save>) : BaseAdapter() {
    val db = Firebase.firestore

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.save_item, null)

        val password = view.findViewById<TextView>(R.id.pw)
        val site = view.findViewById<TextView>(R.id.site)
        val id = view.findViewById<TextView>(R.id.id)
        val delete_button = view.findViewById<ImageButton>(R.id.delete_btn)
        val edit_button = view.findViewById<ImageButton>(R.id.edit_btn)

        val save: Save = saveList[position]

        password.text = save.password
        site.text = save.site
        id.text = save.id

        delete_button.setOnClickListener {
            val siteURL = site.text.toString()
            db.collection("passwords").document(siteURL + "#" + id.text.toString())
                .delete()
                .addOnSuccessListener {
                    Log.d("mytag", "DocumentSnapshot successfully written!")
                    saveList.removeAt(position)
                    notifyDataSetChanged()}
                .addOnFailureListener { e -> Log.w("mytag", "Error writing document", e) }
        }

        edit_button.setOnClickListener {
            val intent = Intent(context, PasswordManage::class.java)
            intent.putExtra("save", save)
            context.startActivity(intent)

        }

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