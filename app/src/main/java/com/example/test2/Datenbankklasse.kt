package com.example.test2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Datenbankklasse(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE " +  TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        COLUMN_SCHALTER + " TEXT NOT NULL, " +
                        COLUMN_WERT + " INTEGER NOT NULL" + ")"
        db.execSQL(sql)
    }

    override fun onUpgrade (db: SQLiteDatabase, versionAlt: Int, versionNeu: Int){
        //onUpgrade(db, versionAlt, versionNeu)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // Datensatz wird zur Tabelle hinzugefügt
    fun addDS(s: String, wert: Int) {
        val values = ContentValues()
        values.put(COLUMN_SCHALTER, s)
        values.put(COLUMN_WERT, wert)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Die ganze Tabelle wird ausgelesen bzw. in einer Cursor-Variable zurückgegeben
    // Dargestellt werden die Daten durch die Funktion showDB in MainActivity
    fun auslesenDS(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_SCHALTER ASC", null)
    }

    // Hier werden die wichtigen Daten in Variablen eines Companion-Objekts gesepeichert
    companion object {
        private val DATABASE_VERSION = 7
        private val DATABASE_NAME = "aufgabe1.db"
        val TABLE_NAME = "schalterzustaende"
        val COLUMN_ID = "id"
        val COLUMN_SCHALTER = "schalter"
        val COLUMN_WERT = "wert"
    }






}