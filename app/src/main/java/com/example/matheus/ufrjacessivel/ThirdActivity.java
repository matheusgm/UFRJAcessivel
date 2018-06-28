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

    private TextView lblLibraUp;
    private TextView lblLibraDown;
    private TextView lblRampaUp;
    private TextView lblRampaDown;
    private TextView lblInterpretesUp;
    private TextView lblInterpretesDown;

    private ImageView imgLibraUp, imgLibraDown, imgRampasUp, imgRampasDown, imgInterpretesUp, imgInterpretesDown;

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

        String corLibras = getIntent().getStringExtra("COR_LIBRAS");
        String corRampas = getIntent().getStringExtra("COR_RAMPAS");
        String corInterpretes = getIntent().getStringExtra("COR_INTERPRETES");

        setaCoresThumbs(corLibras, imgLibraUp, imgLibraDown);
        setaCoresThumbs(corRampas, imgRampasUp, imgRampasDown);
        setaCoresThumbs(corInterpretes, imgInterpretesUp, imgInterpretesDown);

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
        lblLibraUp = findViewById(R.id.numThumbsUpLibras);
        lblLibraDown = findViewById(R.id.numThumbsDownLibras);
        lblRampaUp = findViewById(R.id.numThumbsUpRampas);
        lblRampaDown = findViewById(R.id.numThumbsDownRampas);
        lblInterpretesUp = findViewById(R.id.numThumbsUpInterpretes);
        lblInterpretesDown = findViewById(R.id.numThumbsDownInterpretes);

        imgLibraUp = findViewById(R.id.imgThumbsUpLibras);
        imgLibraDown = findViewById(R.id.imgThumbsDownLibras);
        imgRampasUp = findViewById(R.id.imgThumbsUpRampas);
        imgRampasDown = findViewById(R.id.imgThumbsDownRampas);
        imgInterpretesUp = findViewById(R.id.imgThumbsUpInterpretes);
        imgInterpretesDown = findViewById(R.id.imgThumbsDownInterpretes);

        String NumLibraUp = getIntent().getStringExtra("NumLibraUp");
        String NumLibraDown = getIntent().getStringExtra("NumLibraDown");
        String NumRampaUp = getIntent().getStringExtra("NumRampaUp");
        String NumRampaDown = getIntent().getStringExtra("NumRampaDown");
        String NumInterpretesUp = getIntent().getStringExtra("NumInterpretesUp");
        String NumInterpretesDown = getIntent().getStringExtra("NumInterpretesDown");

        lblLibraUp.setText(NumLibraUp);
        lblLibraDown.setText(NumLibraDown);
        lblRampaUp.setText(NumRampaUp);
        lblRampaDown.setText(NumRampaDown);
        lblInterpretesUp.setText(NumInterpretesUp);
        lblInterpretesDown.setText(NumInterpretesDown);

        imgLibraUp.setOnClickListener(this);
        imgLibraDown.setOnClickListener(this);
        imgRampasUp.setOnClickListener(this);
        imgRampasDown.setOnClickListener(this);
        imgInterpretesUp.setOnClickListener(this);
        imgInterpretesDown.setOnClickListener(this);
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
        if(img == imgLibraUp || img == imgLibraDown){
            ativaThumb(img,imgLibraUp,imgLibraDown,lblLibraUp,lblLibraDown,"Num_Libras_Up","Num_Libras_Down","Cor_Libras");
        }else if(img == imgRampasUp || img == imgRampasDown){
            ativaThumb(img,imgRampasUp,imgRampasDown,lblRampaUp,lblRampaDown,"Num_Rampas_Up","Num_Rampas_Down","Cor_Rampas");
        }else if(img == imgInterpretesUp || img == imgInterpretesDown){
            ativaThumb(img,imgInterpretesUp,imgInterpretesDown,lblInterpretesUp,lblInterpretesDown,"Num_Interpretes_Up","Num_Interpretes_Down","Cor_Interpretes");
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
