package listadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import android.widget.TextView
import com.woaini.kotlintp.R
import music.Note
import music.Partition



class NoteAdapter(context: Context,val textViewResourceId: Int,val notes: Partition) : ArrayAdapter<Note>(context, textViewResourceId, notes) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{

        var converted : View? = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            converted = inflater.inflate(R.layout.note, parent, false)
        } else {
            converted = convertView
        }
        val note = converted!!.findViewById<TextView>(R.id.hauteur)
        note.text = this.notes.get(position).hauteur.toString()
        val rythme = converted.findViewById<TextView>(R.id.rythme)
        rythme.text = this.notes.get(position).rythme.toString()
        val octave = converted.findViewById<TextView>(R.id.octave)
        octave.text = this.notes.get(position).octave.toString()
        return converted
    }

}
