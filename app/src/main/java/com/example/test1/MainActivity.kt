package com.example.test1

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.Note
import com.example.test.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var listNotes = ArrayList<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.setHasFixedSize(true)
        adapter = NoteAdapter(listNotes) { partItem : Note, partMode:String -> ChooseData(partItem,partMode)}
        rv_notes.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        GetData()
    }

    private fun GetData(){
        val CONTENT_URI: Uri = Uri.Builder().scheme("content").authority("com.dicoding.picodiploma.mynotesapp").appendPath("note").build()
        val notesCursor = contentResolver?.query(CONTENT_URI, null, null, null, null) as Cursor

        listNotes.clear()
        if(notesCursor.count==0){
            showSnackbarMessage("Tidak ada data saat ini")
        }else {
            while (notesCursor.moveToNext()) {
                val id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow("_id"))
                val title = notesCursor.getString(notesCursor.getColumnIndexOrThrow("title"))
                val description = notesCursor.getString(notesCursor.getColumnIndexOrThrow("description"))
                val date = notesCursor.getString(notesCursor.getColumnIndexOrThrow("date"))
                listNotes.add(Note(id, title, description, date))
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun showSnackbarMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun ChooseData(note:Note,mode:String){

    }
}
