package com.example.matheus.ufrjacessivel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheus.ufrjacessivel.DataBase.DadosOpenHelper;
import com.example.matheus.ufrjacessivel.Local.Local;
import com.example.matheus.ufrjacessivel.Local.LocalListAdpter;
import com.example.matheus.ufrjacessivel.Local.LocalRepositorio;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lstViewItem;
    private TextView lblLibra;
    private TextView lblRampas;
    private TextView lblInterpretes;
    private TextView lblAtual = null;

    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    private LocalRepositorio localRepositorio;

    private ArrayList<Local> locais;

    private String tipo;
    private String clauseMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lstViewItem = findViewById(R.id.lstViewItem);

        lblLibra = findViewById(R.id.lblLibra);
        lblRampas = findViewById(R.id.lblRampas);
        lblInterpretes = findViewById(R.id.lblInterpretes);

        lblLibra.setOnClickListener(this);
        lblRampas.setOnClickListener(this);
        lblInterpretes.setOnClickListener(this);

        criarConexao();

        tipo = getIntent().getStringExtra("TIPO_NOME");
        clauseMain = "WHERE TIPO IN ('"+tipo+"')";
        getSupportActionBar().setTitle(tipo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        visualizarLista(clauseMain);

        lstViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("NOME",locais.get(i).getName());
                intent.putExtra("NumLibraUp",locais.get(i).getNumLibraUp());
                intent.putExtra("NumLibraDown",locais.get(i).getNumLibraDown());
                intent.putExtra("NumRampaUp",locais.get(i).getNumRampaUp());
                intent.putExtra("NumRampaDown",locais.get(i).getNumRampaDown());
                intent.putExtra("NumInterpretesUp",locais.get(i).getNumInterpretesUp());
                intent.putExtra("NumInterpretesDown",locais.get(i).getNumInterpretesDown());
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:

                visualizarLista(clauseMain);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        return true;
    }

    private void visualizarLista(String S){

        locais = localRepositorio.buscarTodos(S);

        if(lblAtual == lblLibra) {
            localRepositorio.ordenarLibras(locais);
        }else if(lblAtual == lblInterpretes) {
            localRepositorio.ordenarInterpretes(locais);
        }else if(lblAtual == lblRampas) {
            localRepositorio.ordenarRampas(locais);
        }

        LocalListAdpter arrayAdapter = new LocalListAdpter(this,locais);
        lstViewItem.setAdapter(arrayAdapter);

        localRepositorio.verBanco();
    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            //Toast.makeText(this, "Banco no "+this.getLocalClassName()+" criado com sucesso!", Toast.LENGTH_LONG).show();

            localRepositorio = new LocalRepositorio(conexao);

        }catch(SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro ao criar banco no "+this.getLocalClassName());
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    @Override
    public void onClick(View v) {

        TextView lbl = v.findViewById(v.getId());

        if(lbl == lblAtual){
            clearLabel(lbl);
            lblAtual = null;
        }else{

            lblAtual = lbl;

            clearLabel(lblLibra);
            clearLabel(lblInterpretes);
            clearLabel(lblRampas);

            lbl.setTypeface(Typeface.DEFAULT_BOLD);
            lbl.setTextSize(20);

        }

        visualizarLista(clauseMain);

    }

    private void clearLabel(TextView lbl){
        lbl.setTypeface(Typeface.DEFAULT);
        lbl.setTextSize(15);
    }



}
