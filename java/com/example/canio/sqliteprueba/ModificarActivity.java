package com.example.canio.sqliteprueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModificarActivity extends Activity implements OnClickListener {

    private EditText titulo;
    private Button updateBtn, deleteBtn;
    private long _id;
    private SQLController dbController;
    private EditText contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Titulo Ventana
        setTitle("Modificar Registro");

        setContentView(R.layout.activity_modify);

        dbController = new SQLController(this);
        dbController.open();

        // Controles de la Ventana
        titulo = (EditText) findViewById(R.id.titulo_edittext);
        contenido = (EditText) findViewById(R.id.contenido_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("titulo");
        String desc = intent.getStringExtra("contenido");

        _id = Long.parseLong(id);

        titulo.setText(name);
        contenido.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String titu = titulo.getText().toString();
                String conte = contenido.getText().toString();

                dbController.update(_id, titu, conte);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbController.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}