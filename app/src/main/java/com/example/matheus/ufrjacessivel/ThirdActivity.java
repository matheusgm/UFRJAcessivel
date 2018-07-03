package com.example.matheus.ufrjacessivel;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.ufrjacessivel.DataBase.DadosOpenHelper;
import com.example.matheus.ufrjacessivel.Local.LocalRepositorio;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNome;

    private TextView lblAudicaoUp;
    private TextView lblAudicaoDown;
    private TextView lblMobilidadeUp;
    private TextView lblMobilidadeDown;
    private TextView lblVisaoUp;
    private TextView lblVisaoDown;

    private ImageView imgAudicaoUp, imgAudicaoDown, imgMobilidadeUp, imgMobilidadeDown, imgVisaoUp, imgVisaoDown;

    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    private LocalRepositorio localRepositorio;

    private int id;

    private final String VERMELHO = "VERMELHO";
    private final String VERDE = "VERDE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third__activity);


        String nome = getIntent().getStringExtra("NOME");
        id = getIntent().getIntExtra("ID",-1);

        getSupportActionBar().setTitle(nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setaValoresIniciais();

        String corAudicao = getIntent().getStringExtra("COR_Audicao");
        String corMobilidade = getIntent().getStringExtra("COR_Mobilidade");
        String corVisao = getIntent().getStringExtra("COR_Visao");

        setaCoresThumbs(corAudicao, imgAudicaoUp, imgAudicaoDown);
        setaCoresThumbs(corMobilidade, imgMobilidadeUp, imgMobilidadeDown);
        setaCoresThumbs(corVisao, imgVisaoUp, imgVisaoDown);

        criarConexao();

    }

    private void setaCoresThumbs(String cor, ImageView imgUp, ImageView imgDown){
        if(cor != null){
            switch (cor){
                case VERDE:
                    imgUp.setTag("LightGreen");
                    imgUp.setColorFilter(getResources().getColor(R.color.colorLightGreen));
                    break;
                case VERMELHO:
                    imgDown.setTag("LightRed");
                    imgDown.setColorFilter(getResources().getColor(R.color.colorLightRed));
                    break;
            }
        }
    }

    private void setaValoresIniciais(){
        lblAudicaoUp = findViewById(R.id.numThumbsUpAudicao);
        lblAudicaoDown = findViewById(R.id.numThumbsDownAudicao);
        lblMobilidadeUp = findViewById(R.id.numThumbsUpMobilidade);
        lblMobilidadeDown = findViewById(R.id.numThumbsDownMobilidade);
        lblVisaoUp = findViewById(R.id.numThumbsUpVisao);
        lblVisaoDown = findViewById(R.id.numThumbsDownVisao);

        imgAudicaoUp = findViewById(R.id.imgThumbsUpAudicao);
        imgAudicaoDown = findViewById(R.id.imgThumbsDownAudicao);
        imgMobilidadeUp = findViewById(R.id.imgThumbsUpMobilidade);
        imgMobilidadeDown = findViewById(R.id.imgThumbsDownMobilidade);
        imgVisaoUp = findViewById(R.id.imgThumbsUpVisao);
        imgVisaoDown = findViewById(R.id.imgThumbsDownVisao);

        String NumAudicaoUp = getIntent().getStringExtra("NumAudicaoUp");
        String NumAudicaoDown = getIntent().getStringExtra("NumAudicaoDown");
        String NumMobilidadeUp = getIntent().getStringExtra("NumMobilidadeUp");
        String NumMobilidadeDown = getIntent().getStringExtra("NumMobilidadeDown");
        String NumVisaoUp = getIntent().getStringExtra("NumVisaoUp");
        String NumVisaoDown = getIntent().getStringExtra("NumVisaoDown");

        lblAudicaoUp.setText(NumAudicaoUp);
        lblAudicaoDown.setText(NumAudicaoDown);
        lblMobilidadeUp.setText(NumMobilidadeUp);
        lblMobilidadeDown.setText(NumMobilidadeDown);
        lblVisaoUp.setText(NumVisaoUp);
        lblVisaoDown.setText(NumVisaoDown);

        imgAudicaoUp.setOnClickListener(this);
        imgAudicaoDown.setOnClickListener(this);
        imgMobilidadeUp.setOnClickListener(this);
        imgMobilidadeDown.setOnClickListener(this);
        imgVisaoUp.setOnClickListener(this);
        imgVisaoDown.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void ativaThumb(ImageView img, ImageView imgUp,ImageView imgDown, TextView txtUp, TextView txtDown, String nomeUp, String nomeDown, String cor){

        ContentValues contentValues = new ContentValues();
        String val1 = null;
        String val2 = null;
        if(img == imgUp){
            if(imgUp.getTag().equals("LightGreen")){
                val1 = Integer.toString(Integer.parseInt(txtUp.getText().toString())-1);
                txtUp.setText(val1);
                imgUp.setColorFilter(getResources().getColor(R.color.colorGreen200));
                imgUp.setTag("Green");

                contentValues.put(nomeUp,val1);
                contentValues.put(cor,(String) null);
            }else if(imgDown.getTag().equals("LightRed")){
                imgUp.setColorFilter(getResources().getColor(R.color.colorLightGreen));
                imgUp.setTag("LightGreen");
                val1 = Integer.toString(Integer.parseInt(txtUp.getText().toString())+1);
                txtUp.setText(val1);
                imgDown.setColorFilter(getResources().getColor(R.color.colorRed200));
                imgDown.setTag("Red");
                val2 = Integer.toString(Integer.parseInt(txtDown.getText().toString())-1);
                txtDown.setText(val2);

                contentValues.put(nomeUp,val1);
                contentValues.put(nomeDown,val2);
                contentValues.put(cor,VERDE);
            }else{
                imgUp.setColorFilter(getResources().getColor(R.color.colorLightGreen));
                imgUp.setTag("LightGreen");
                val1 = Integer.toString(Integer.parseInt(txtUp.getText().toString())+1);
                txtUp.setText(val1);

                contentValues.put(nomeUp,val1);
                contentValues.put(cor,VERDE);
            }
        }else if(img == imgDown){
            if(imgDown.getTag().equals("LightRed")){
                val2 = Integer.toString(Integer.parseInt(txtDown.getText().toString())-1);
                txtDown.setText(val2);
                imgDown.setColorFilter(getResources().getColor(R.color.colorRed200));
                imgDown.setTag("Red");

                contentValues.put(nomeDown,val2);
                contentValues.put(cor,(String) null);
            }else if(imgUp.getTag().equals("LightGreen")){
                imgDown.setColorFilter(getResources().getColor(R.color.colorLightRed));
                imgDown.setTag("LightRed");
                val2 = Integer.toString(Integer.parseInt(txtDown.getText().toString())+1);
                txtDown.setText(val2);
                imgUp.setColorFilter(getResources().getColor(R.color.colorGreen200));
                imgUp.setTag("Green");
                val1 = Integer.toString(Integer.parseInt(txtUp.getText().toString())-1);
                txtUp.setText(val1);

                contentValues.put(nomeUp,val1);
                contentValues.put(nomeDown,val2);
                contentValues.put(cor,VERMELHO);
            }else{
                imgDown.setColorFilter(getResources().getColor(R.color.colorLightRed));
                imgDown.setTag("LightRed");
                val2 = Integer.toString(Integer.parseInt(txtDown.getText().toString())+1);
                txtDown.setText(val2);

                contentValues.put(nomeDown,val2);
                contentValues.put(cor,VERMELHO);
            }
        }

        localRepositorio.update(contentValues,id);

    }

    @Override
    public void onClick(View v) {
        ImageView img = findViewById(v.getId());
        if(img == imgAudicaoUp || img == imgAudicaoDown){
            ativaThumb(img,imgAudicaoUp,imgAudicaoDown,lblAudicaoUp,lblAudicaoDown,"Num_Audicao_Up","Num_Audicao_Down","Cor_Audicao");
        }else if(img == imgMobilidadeUp || img == imgMobilidadeDown){
            ativaThumb(img,imgMobilidadeUp,imgMobilidadeDown,lblMobilidadeUp,lblMobilidadeDown,"Num_Mobilidade_Up","Num_Mobilidade_Down","Cor_Mobilidade");
        }else if(img == imgVisaoUp || img == imgVisaoDown){
            ativaThumb(img,imgVisaoUp,imgVisaoDown,lblVisaoUp,lblVisaoDown,"Num_Visao_Up","Num_Visao_Down","Cor_Visao");
        }

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
}
