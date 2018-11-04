package com.example.matheus.ufrjacessivel;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.matheus.ufrjacessivel.DataBase.DadosOpenHelper;
import com.example.matheus.ufrjacessivel.Evento.Evento;
import com.example.matheus.ufrjacessivel.Evento.EventoListAdpter;
import com.example.matheus.ufrjacessivel.Evento.EventoRepositorio;
import com.example.matheus.ufrjacessivel.Local.LocalRepositorio;

import java.util.ArrayList;

public class EventosActivity extends AppCompatActivity {

    private ListView lstViewItem;
    private ArrayList<Evento> eventos;

    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    private EventoRepositorio eventoRepositorio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_activity);

        lstViewItem = findViewById(R.id.eventoLstViewItem);

        criarConexao();

        getSupportActionBar().setTitle("Eventos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Evento e = new Evento();
        e.setNome("Evento Teste");
        e.setDataEvento("01/12/2018");
        e.setEndereco("Universidade Federal do Rio de Janeiro");
        e.setHorario("13:00");
        //eventoRepositorio.inserir(e);

        visualizarLista();

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void visualizarLista(){

        eventos = eventoRepositorio.buscarTodos();

        EventoListAdpter arrayAdapter = new EventoListAdpter(this,eventos);
        lstViewItem.setAdapter(arrayAdapter);

        eventoRepositorio.verBanco();
    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            //Toast.makeText(this, "Banco no "+this.getLocalClassName()+" criado com sucesso!", Toast.LENGTH_LONG).show();

            eventoRepositorio = new EventoRepositorio(conexao);

        }catch(SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro ao criar banco no "+this.getLocalClassName());
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

}
