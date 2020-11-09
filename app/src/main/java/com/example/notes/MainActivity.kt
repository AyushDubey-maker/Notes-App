package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesAdapter {
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recycler_view.adapter=adapter

        viewModel=ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {
     adapter.update(it)
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNode(note)
   Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }
    fun submit_note(view:View){
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote((Note(noteText)))
        }else{
            input.error = "Empty"
        }
    }
}