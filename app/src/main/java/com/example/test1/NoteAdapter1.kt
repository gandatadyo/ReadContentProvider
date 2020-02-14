package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import kotlinx.android.synthetic.main.item_note.view.*
import kotlin.collections.ArrayList

class NoteAdapter(val listNotes:ArrayList<Note>,val clickItem:(Note,String)->Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewholder(view)
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    inner class NoteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            with(itemView){
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description

                setOnClickListener { clickItem(note,"edit") }
                btnDelete.setOnClickListener { clickItem(note,"delete") }
            }
        }
    }
}
