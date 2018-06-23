package com.example.matheus.ufrjacessivel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private TextView txtNome;

    private TextView lblLibraUp;
    private TextView lblLibraDown;
    private TextView lblRampaUp;
    private TextView lblRampaDown;
    private TextView lblInterpretesUp;
    private TextView getLblInterpretesDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third__activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtNome = findViewById(R.id.txtNome);

        String nome = getIntent().getStringExtra("NOME");
        txtNome.setText(nome);

        startValores();

    }

    private void startValores(){
        lblLibraUp = findViewById(R.id.numThumbsUpLibras);
        lblLibraDown = findViewById(R.id.numThumbsDownLibras);
        lblRampaUp = findViewById(R.id.numThumbsUpRampas);
        lblRampaDown = findViewById(R.id.numThumbsDownRampas);
        lblInterpretesUp = findViewById(R.id.numThumbsUpInterpretes);
        getLblInterpretesDown = findViewById(R.id.numThumbsDownInterpretes);

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
        getLblInterpretesDown.setText(NumInterpretesDown);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
