package com.example.apiproject

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(var searchedCards : ArrayList<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val textViewCardName : TextView
        val textViewLocation : TextView
        val textViewTime : TextView
        val layout : ConstraintLayout

        init {
            textViewMagnitude = view.findViewById(R.id.textView_earthquakeItem_magnitude)
            textViewLocation = view.findViewById(R.id.textView_earthquakeItem_location)
            textViewTime = view.findViewById(R.id.textView_earthquakeItem_time)
            layout = view.findViewById(R.id.layout)
        }
    }

}
