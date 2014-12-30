package com.example.canio.sqliteprueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*Siempre es una buena práctica para definir un controlador o clase separarla para todas sus operaciones
de base de datos. Aquí estamos creando una nueva clase que realiza todas las operaciones relacionadas
con bases de datos como agregar, actualizar, eliminar registros en la tabla.*/

public class SQLController {

    private DBhelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    // Constructor
    public SQLController(Context c) {
        ourcontext = c;
    }

    // Metodo para abrir la Base de Datos
    public SQLController open() throws SQLException {
        dbHelper = new DBhelper(ourcontext);
        database = dbHelper.getWritableDatabase();

        // Enable foreign key constraints
        if (!database.isReadOnly()) {
            database.execSQL("PRAGMA foreign_keys = ON;");
        }
        return this;
    }

    // Método para cerrar la conexión a Base de Datos
    public void close() {
        dbHelper.close();
    }

    // Método para insertar los registros
    // Recibe dos cadenas de texto y los inserta
    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.COLUM_1, name);
        contentValue.put(DBhelper.COLUM_2, desc);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }

    // Metodo para colocar los registro en el listview a través de un cursor
    public Cursor fetch() {
        String[] columns = new String[] {
                DBhelper._ID, DBhelper.COLUM_1,
                DBhelper.COLUM_2
        };

        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Metodo para actualizar un registro
    public int update(long _id, String titu, String conte) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.COLUM_1, titu);
        contentValues.put(DBhelper.COLUM_2, conte);
        int i = database.update(DBhelper.TABLE_NAME, contentValues,
                DBhelper._ID + " = " + _id, null);
        return i;
    }

    // Metodo para eliminar un registro
    public void delete(long _id) {
        database.delete(DBhelper.TABLE_NAME, DBhelper._ID + "=" + _id, null);
    }
}