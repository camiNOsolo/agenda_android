package com.example.canio.sqliteprueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private SQLController dbController;
    private EditText tituloEditText;
    private EditText contenidoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Añadir registro");

        setContentView(R.layout.activity_add);

        tituloEditText = (EditText) findViewById(R.id.titulo_edittext);
        contenidoEditText = (EditText) findViewById(R.id.contenido_edittext);

        addTodoBtn = (Button) findViewById(R.id.add);

        dbController = new SQLController(this);
        dbController.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                final String titu = tituloEditText.getText().toString();
                final String conte = contenidoEditText.getText().toString();
                dbController.insert(titu, conte);

                Intent main = new Intent(AgregarActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            default:
                break;
        }
    }

}
