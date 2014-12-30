package com.example.canio.sqliteprueba;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private SQLController dbcon;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbcon = new SQLController(this);
        dbcon.open();
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        // Colocar los datos en un ListView usando Crusor Adapter
        // Cursor es una zona buffer temporal que contiene los resultados de la consulta SQL
        Cursor cursor = dbcon.fetch();
        String[] alista = new String[] { DBhelper._ID, DBhelper.COLUM_1, DBhelper.COLUM_2 };
        int[] to = new int[] { R.id.id, R.id.titulo, R.id.contenido };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view, cursor, alista, to, 0);

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        // OnCLickListiner para los Items de la Lista
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTxt = (TextView) view.findViewById(R.id.id);
                TextView tituloTxt = (TextView) view.findViewById(R.id.titulo);
                TextView contenidoTxt = (TextView) view.findViewById(R.id.contenido);

                String id = idTxt.getText().toString();
                String titulo = tituloTxt.getText().toString();
                String contenido = contenidoTxt.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        ModificarActivity.class);
                modify_intent.putExtra("titulo", titulo);
                modify_intent.putExtra("contenido", contenido);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Intent add_mem = new Intent(this, AgregarActivity.class);
            startActivity(add_mem);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}