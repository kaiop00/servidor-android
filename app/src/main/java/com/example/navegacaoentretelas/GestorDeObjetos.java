package com.example.navegacaoentretelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GestorDeObjetos extends AppCompatActivity {
    TextView tvAcao;
    Button btnAcao,btnCancelar;
    EditText etNome, etDescricao;
    int codigo, ADICIONAR = 1, EDITAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_de_objetos);

        tvAcao = (TextView) findViewById(R.id.tvAcao);
        btnAcao = (Button) findViewById(R.id.btnAcao);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        etNome = (EditText) findViewById(R.id.etNome);
        etDescricao = (EditText) findViewById(R.id.etDescricao);

        codigo = getIntent().getIntExtra("requestCode",0);

        if(codigo == ADICIONAR){
            tvAcao.setText(R.string.sAdicionar);
            btnAcao.setText(R.string.sAdicionar);
        }else if(codigo == EDITAR){
            tvAcao.setText(R.string.sEditar);
            btnAcao.setText(R.string.sEditar);
            etNome.setText(getIntent().getStringExtra("nome"));
            etDescricao.setText(getIntent().getStringExtra("descricao"));
        }

        btnAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNome.getText().toString().isEmpty() || etDescricao.getText().toString().isEmpty()){
                    Toast.makeText(GestorDeObjetos.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("id",getIntent().getIntExtra("id",0));
                    intent.putExtra("nome", etNome.getText().toString());
                    intent.putExtra("descricao", etDescricao.getText().toString());
                    intent.putExtra("posicao",getIntent().getIntExtra("posicao",0));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}