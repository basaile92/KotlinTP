package com.woaini.kotlintp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Spinner
import listadapter.NoteAdapter
import musicoutput.Output
import java.util.concurrent.TimeUnit
import android.widget.Toast
import android.widget.AdapterView
import music.Note
import music.NoteHauteur
import music.NoteRythme
import music.Partition


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var partition: Partition = Partition()
    var hauteurName : String = ""
    var rythmeName : String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addNote : Button = findViewById(R.id.addNote)
        var play : Button = findViewById(R.id.play)
        var clear : Button = findViewById(R.id.clear)
        var notesList : ListView = findViewById(R.id.notesList)
        var hauteurPicker : Spinner = findViewById(R.id.hauteurPicker)
        var rythmePicker : Spinner = findViewById(R.id.rythmePicker)
        var octaveNumber : EditText = findViewById(R.id.octaveNumber)
        var noteText : TextView = findViewById(R.id.noteText)
        var tempo : TextView = findViewById(R.id.tempoNumber)

        val hauteurPickerAdapter = ArrayAdapter.createFromResource(this, R.array.notes_array, android.R.layout.simple_spinner_item)
        hauteurPickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hauteurPicker.setAdapter(hauteurPickerAdapter)

        val rythmePickerAdapter = ArrayAdapter.createFromResource(this, R.array.rythme_array, android.R.layout.simple_spinner_item)
        rythmePickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rythmePicker.setAdapter(rythmePickerAdapter)
        hauteurPicker.setOnItemSelectedListener(this);
        rythmePicker.setOnItemSelectedListener(this);

        var adapter = NoteAdapter(applicationContext, R.layout.note, partition)
        notesList.adapter = adapter


        addNote.setOnClickListener(View.OnClickListener {

            var noteHauteur: NoteHauteur = NoteHauteur.stringToNoteHauteur(hauteurName)
            var noteRythme : NoteRythme = NoteRythme.stringToNoteRythme(rythmeName)
            partition.add(Note(noteHauteur ,octaveNumber.text.toString().toInt(),  noteRythme))
            adapter.notifyDataSetChanged()
        })



        play.setOnClickListener(View.OnClickListener {

            Thread(Runnable {
                play.isClickable = false
                addNote.isClickable = false
                clear.isClickable = false
                for (note in partition) {

                    runOnUiThread {
                        noteText.setText(note.toString())
                    }

                    var audioTrack: AudioTrack
                    audioTrack = Output.generateTone(note.frequence.toDouble(), (note.rythme.temps * tempo.text.toString().toFloat()).toInt())

                    if(audioTrack.state != AudioTrack.STATE_INITIALIZED ) {
                        continue
                    }
                        audioTrack.play()

                    TimeUnit.MILLISECONDS.sleep(note.rythme.temps.toLong() * tempo.text.toString().toInt());
                }
                play.isClickable = true
                addNote.isClickable = true
                clear.isClickable = true


            }).start()
        })

        clear.setOnClickListener(View.OnClickListener {

            this.partition.clear()
            adapter.notifyDataSetChanged()

        })

        notesList.setOnItemClickListener(object : android.widget.AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = notesList.getItemAtPosition(position)
                partition.removeAt(position)
                adapter.notifyDataSetChanged()
                val toast = Toast.makeText(applicationContext, item.toString() + " " + getString(R.string.delete)  , Toast.LENGTH_SHORT)
                toast.show()            }
        })    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val spinner = parent as Spinner

        if(spinner.id == R.id.hauteurPicker){

            hauteurName = parent.getItemAtPosition(position).toString()


        }else if(spinner.id == R.id.rythmePicker){

            rythmeName = parent.getItemAtPosition(position).toString()
        }
    }
}
