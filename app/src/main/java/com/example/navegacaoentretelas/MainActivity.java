package com.example.navegacaoentretelas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.NumberKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Objeto> objetos;
    RecyclerView lista;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnAdd, btnEdit;
    EditText etId;
    int ADICIONAR = 1, EDITAR = 2, id = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.botaoAdd);
        btnEdit = (Button) findViewById(R.id.botaoEdit);
        lista = (RecyclerView) findViewById(R.id.rvLista);
        etId = (EditText) findViewById(R.id.etId);
        lista.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(layoutManager);

        objetos = new ArrayList<Objeto>();
        objetos.add(new Objeto("teste","Teste de funcionalidade",1));

        adapter = new ObjetoAdapter(this,objetos);
        lista.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.navegacaoentretelas.GestorDeObjetos.class);
                intent.putExtra("id",id);
                intent.putExtra("requestCode",ADICIONAR);
                startActivityForResult(intent,ADICIONAR);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.navegacaoentretelas.GestorDeObjetos.class);
                int idObjeto = Integer.parseInt(etId.getText().toString());
                for(Objeto obj : objetos) {
                    if(obj.getId() == idObjeto){
                        intent.putExtra("id",idObjeto);
                        intent.putExtra("posicao",objetos.indexOf(obj));
                        intent.putExtra("requestCode",EDITAR);
                        intent.putExtra("nome",obj.getNome());
                        intent.putExtra("descricao",obj.getDescricao());
                        startActivityForResult(intent,EDITAR);
                        return;
                    }
                }

            }
        });

        etId.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                btnEdit.setEnabled(true);
                if(etId.getText().toString().isEmpty()) {
                    btnEdit.setEnabled(false);
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADICIONAR){
            if(resultCode == RESULT_OK){
                Objeto obj = new Objeto(data.getStringExtra("nome"),data.getStringExtra("descricao"),id);
                objetos.add(obj);
                adapter.notifyItemInserted(id-1);
                id++;
            }
        }else if( requestCode == EDITAR){
            if(resultCode == RESULT_OK){
                Objeto obj = objetos.get(data.getIntExtra("posicao",0));
                adapter.notifyItemChanged(data.getIntExtra("posicao",0));
                obj.setNome(data.getStringExtra("nome"));
                obj.setDescricao(data.getStringExtra("descricao"));
                objetos.set(data.getIntExtra("posicao",0), obj);
            }
        }
    }
}