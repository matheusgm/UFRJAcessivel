package com.example.matheus.ufrjacessivel;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.matheus.ufrjacessivel.DataBase.DadosOpenHelper;
import com.example.matheus.ufrjacessivel.Local.Local;
import com.example.matheus.ufrjacessivel.Local.LocalListAdpter;
import com.example.matheus.ufrjacessivel.Local.LocalRepositorio;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lstViewItem;
    private TextView lblLibra;
    private TextView lblRampas;
    private TextView lblInterpretes;
    private TextView lblAtual = null;

    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    private LocalRepositorio localRepositorio;

    private ArrayList<Local> itens;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        return true;
    }

    private void visualizarLista(String S){

        itens = localRepositorio.buscarTodos(S);

        if(lblAtual == lblLibra) {
            localRepositorio.ordenarLibras(itens);
        }else if(lblAtual == lblInterpretes) {
            localRepositorio.ordenarInterpretes(itens);
        }else if(lblAtual == lblRampas) {
            localRepositorio.ordenarRampas(itens);
        }

        LocalListAdpter arrayAdapter = new LocalListAdpter(this,itens);
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
