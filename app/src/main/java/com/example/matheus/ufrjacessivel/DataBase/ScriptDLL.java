package com.example.matheus.ufrjacessivel.DataBase;

/**
 * Created by Matheus on 2/24/2018.
 */

public class ScriptDLL {

    public static String getCreateTableLocal() {
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS LOCAL ( ");
        sql.append(" CODIGO INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" NOME VARCHAR (200) NOT NULL DEFAULT(''), "); // Nome do local
        sql.append(" TIPO VARCHAR (200), "); // Servico, Aula, Restaurante...
        sql.append(" Num_Audicao_Up VARCHAR (10), ");
        sql.append(" Num_Audicao_Down VARCHAR (10), ");
        sql.append(" Cor_Audicao VARCHAR (10), "); // VERM, VERD, NULL
        sql.append(" Num_Mobilidade_Up VARCHAR (10), ");
        sql.append(" Num_Mobilidade_Down VARCHAR (10), ");
        sql.append(" Cor_Mobilidade VARCHAR (10), "); // VERM, VERD, NULL
        sql.append(" Num_Visao_Up VARCHAR (10), ");
        sql.append(" Num_Visao_Down VARCHAR (10), ");
        sql.append(" Cor_Visao VARCHAR (10), "); // VERM, VERD, NULL
        sql.append(" Tem_Mapa VARCHAR (1) ) ");  // 0 = FALSE e 1 = TRUE

        return sql.toString();
    }

}
