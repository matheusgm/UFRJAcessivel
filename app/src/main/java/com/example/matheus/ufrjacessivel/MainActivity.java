package com.example.matheus.ufrjacessivel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.matheus.ufrjacessivel.Local.Local;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnServicos;
    private Button btnAlimentacao;
    private Button btnAulas;
    private Button btnContribuir;
    private Button btnEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnServicos = findViewById(R.id.btnServicos);
        btnAlimentacao = findViewById(R.id.btnAlimentacao);
        btnAulas = findViewById(R.id.btnAulas);
        btnContribuir = findViewById(R.id.btnContribuir);
        btnEventos = findViewById(R.id.btnEventos);

        btnServicos.setOnClickListener(this);
        btnAlimentacao.setOnClickListener(this);
        btnAulas.setOnClickListener(this);
        btnContribuir.setOnClickListener(this);
        btnEventos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button btn = findViewById(v.getId());
        System.out.println(btn.getText());
        String name = btn.getText().toString();
        if(name.equals("Contribuir")){
            Intent intent = new Intent(this, ContribuirActivity.class);
            startActivity(intent);
        }else if(name.equals("Eventos")){
            Intent intent = new Intent(this, EventosActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("TIPO_NOME",name);
            startActivity(intent);
        }
    }

}
