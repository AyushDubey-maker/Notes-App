package com.example.notes

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context:Context,val listener: INotesAdapter): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text_view)
        val deleteButton=itemView.findViewById<ImageView>(R.id.del_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.items_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
       listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
   }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
      return allNotes.size
    }
    fun update(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


}
interface INotesAdapter{
    fun onItemClicked(note:Note)


}