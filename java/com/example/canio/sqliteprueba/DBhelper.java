package com.example.canio.sqliteprueba;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Al extender de la Clase SQLiteOpenHelper hay que crear los métodos onCreate() y onUpgrade().
public class DBhelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Nombre de la Tabla
    public static final String TABLE_NAME = "TODOS";

    // Columnas de la tabla
    public static final String _ID = "_id";
    public static final String COLUM_1 = "titulo";
    public static final String COLUM_2 = "contenido";

    // Nombre Base Datos
    static final String DB_NAME = "TOSTRING.DB";

    // Version Base Datos
    static final int DB_VERSION = 1;

    // Cadena que almacena el query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_1 + " TEXT NOT NULL, " + COLUM_2 + " TEXT);";

    // Constructor
    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.v(LOG, "Creando Base de datos");
    }

    // Método para crear la Tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException se) {
            Log.v(LOG, Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(LOG, Log.getStackTraceString(e));
        }
    }

    // Método para hacer un upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Log.w(LOG, "Upgrading database de la versión " + oldVersion + " a "
                    + newVersion + ", destruira los datos antiguos");

            // ELIMINAMOS LAS TABLAS SI HAY UN UPGRADE
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            // CREAMOS UNA NUEVA BASE DE DATOS
            onCreate(db);

        } catch (SQLException se) {
            Log.v(LOG, Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(LOG, Log.getStackTraceString(e));
        }
    }
}