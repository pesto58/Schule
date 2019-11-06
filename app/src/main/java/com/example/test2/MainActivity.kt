package com.example.test2

import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //private var b1S = BooleanArray(10);

    //val SS = Schalterstaende()

    val datenbank = Datenbankklasse(this, null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvAusgabe: TextView = findViewById(R.id.tvAusgabe)

        tvAusgabe.text = datenbank.databaseName + "\n"


        datenbank.addDS("b1S", 1)
        datenbank.addDS("b1S", 1)
        datenbank.addDS("b2S", 1)
        datenbank.addDS("b3S", 1)
        datenbank.addDS("b4S", 1)
        datenbank.addDS("b5S", 1)
        datenbank.addDS("b6S", 1)
        datenbank.addDS("b7S", 1)
        datenbank.addDS("b8S", 1)
        datenbank.addDS("b9S", 1)


        showDB(tvAusgabe)

        val tb1B1: ToggleButton = findViewById(R.id.tb1B1)
        tb1B1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                datenbank.addDS("b1S", 1)
            }  else {
                datenbank.addDS("b1S", 0)
            }
        }
    }

    fun showDB(tv: TextView){
        val ausgabe = ""
        val dbHandler =  Datenbankklasse(this, null)
        val cursor = dbHandler.auslesenDS()
        cursor!!.moveToFirst()
        cursor.moveToPosition(0)
        while(cursor.moveToNext()){
            tv.append((cursor.getString(cursor.getColumnIndex(Datenbankklasse.COLUMN_SCHALTER))))
            tv.append(": ")
            tv.append((cursor.getString(cursor.getColumnIndex(Datenbankklasse.COLUMN_WERT))))
            tv.append("   ")
        }
        tv.append("\n\n")
    }
}
/*
class Schalterstaende {
    private var b1S = BooleanArray(10);

    fun setzenSchalterX(x: Int, w: Boolean){
        b1S[x] = w;
    }

    fun Ausgabe (tv: TextView){
        var ausgabe = ""
        for (z in 1..9) {
            ausgabe += b1S[z].toString() + " "
        }
        tv.text = ausgabe
        //tv.text = b1S[1].toString() + " " + b1S[2].toString() + " " + b1S[3]. + " " + b1S[4] + " " + b1S[5] + " " + b1S[6] + " " + b1S[7] + " " + b1S[8] + " " + b1S[9]
    }
}
*/

