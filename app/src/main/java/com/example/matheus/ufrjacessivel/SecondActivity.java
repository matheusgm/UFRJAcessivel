package com.example.matheus.ufrjacessivel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheus.ufrjacessivel.DataBase.DadosOpenHelper;
import com.example.matheus.ufrjacessivel.Local.Local;
import com.example.matheus.ufrjacessivel.Local.LocalListAdpter;
import com.example.matheus.ufrjacessivel.Local.LocalRepositorio;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lstViewItem;
    private TextView lblAudicao;
    private TextView lblMobilidade;
    private TextView lblVisao;
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

        lblAudicao = findViewById(R.id.lblAudicao);
        lblMobilidade = findViewById(R.id.lblMobilidade);
        lblVisao = findViewById(R.id.lblVisao);

        lblAudicao.setOnClickListener(this);
        lblMobilidade.setOnClickListener(this);
        lblVisao.setOnClickListener(this);

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
                intent.putExtra("ID",locais.get(i).getId());
                intent.putExtra("NOME",locais.get(i).getName());
                intent.putExtra("NumAudicaoUp",locais.get(i).getNumAudicaoUp());
                intent.putExtra("NumAudicaoDown",locais.get(i).getNumAudicaoDown());
                intent.putExtra("COR_Audicao",locais.get(i).getCorAudicao());
                intent.putExtra("NumMobilidadeUp",locais.get(i).getNumMobilidadeUp());
                intent.putExtra("NumMobilidadeDown",locais.get(i).getNumMobilidadeDown());
                intent.putExtra("COR_Mobilidade",locais.get(i).getCorMobilidade());
                intent.putExtra("NumVisaoUp",locais.get(i).getNumVisaoUp());
                intent.putExtra("NumVisaoDown",locais.get(i).getNumVisaoDown());
                intent.putExtra("COR_Visao",locais.get(i).getCorVisao());
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

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Local> tempList = new ArrayList<>();

                for(Local temp: locais){
                    if(temp.getName().toLowerCase().contains(newText.toLowerCase())){
                        tempList.add(temp);
                    }
                }

                LocalListAdpter arrayAdapter = new LocalListAdpter(SecondActivity.this,tempList);
                lstViewItem.setAdapter(arrayAdapter);

                return true;
            }
        });

        return true;
    }

    private void visualizarLista(String S){

        locais = localRepositorio.buscarTodos(S);

        if(lblAtual == lblAudicao) {
            localRepositorio.ordenarAudicao(locais);
        }else if(lblAtual == lblVisao) {
            localRepositorio.ordenarVisao(locais);
        }else if(lblAtual == lblMobilidade) {
            localRepositorio.ordenarMobilidade(locais);
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

            clearLabel(lblAudicao);
            clearLabel(lblVisao);
            clearLabel(lblMobilidade);

            lbl.setTypeface(ResourcesCompat.getFont(this,R.font.vast_shadow_regular));
            //lbl.setTextSize(20);
            lbl.setBackground(ContextCompat.getDrawable(this,R.drawable.border));

        }

        visualizarLista(clauseMain);

    }

    private void clearLabel(TextView lbl){
        lbl.setTypeface(ResourcesCompat.getFont(this,R.font.vast_shadow_regular));
        lbl.setTextSize(15);
        lbl.setBackground(null);
    }



}
