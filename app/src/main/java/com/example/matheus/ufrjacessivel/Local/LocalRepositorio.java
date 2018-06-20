package com.example.matheus.ufrjacessivel.Local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Matheus on 2/26/2018.
 */

public class LocalRepositorio {

    private SQLiteDatabase conexao;

    public LocalRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Local local){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", local.getName());
        contentValues.put("TIPO", local.getTipo());
        contentValues.put("Num_Libras_Up","0");
        contentValues.put("Num_Libras_Down","0");
        contentValues.put("Num_Rampas_Up","0");
        contentValues.put("Num_Rampas_Down","0");
        contentValues.put("Num_Interpretes_Up","0");
        contentValues.put("Num_Interpretes_Down","0");
        contentValues.put("Tem_Mapa","0");


        conexao.insertOrThrow("LOCAL",null,contentValues);

    }

    public void update(ContentValues values, int id){
        conexao.update("LOCAL", values, "CODIGO = "+id, null);
    }

    public void atualizar(ArrayList<Local> itens){
        /*
        int ini;
        int fim;
        for(fim = materias.size() - 1; fim > 0; fim-- ){
            int troca = 0;
            for(ini = 0; ini < fim; ini++){
                if( Float.parseFloat(materias.get(ini).getMateriaCR()) > Float.parseFloat(materias.get(ini+1).getMateriaCR())){
                    Materia temp = materias.get(ini);
                    materias.set(ini, materias.get(ini + 1));
                    materias.set(ini + 1, temp);
                    troca++;
                }
            }
            if(troca==0){
                break;
            }
        }
        */
    }

    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("LOCAL","CODIGO = ?",parametros);
    }

    public Local buscarLocal(int id){

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM LOCAL ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                if(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")) == id) {
                    Local local = new Local();

                    local.setId(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                    local.setName(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                    local.setTipo(resultado.getString(resultado.getColumnIndexOrThrow("TIPO")));
                    local.setNumLibraUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Up")));
                    local.setNumLibraDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Down")));
                    local.setNumRampaUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Up")));
                    local.setNumRampaDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Down")));
                    local.setNumInterpretesUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Up")));
                    local.setNumInterpretesDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Down")));
                    local.setTemMapa(resultado.getString(resultado.getColumnIndexOrThrow("Tem_Mapa")));

                    return local;
                }
            }while(resultado.moveToNext());

        }
        return null;

    }

    public ArrayList<Local> buscarTodos(String S){

        ArrayList<Local> itens = new ArrayList<Local>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM LOCAL "+S);

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                Local local = new Local();

                local.setId(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                local.setName(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                local.setTipo(resultado.getString(resultado.getColumnIndexOrThrow("TIPO")));
                local.setNumLibraUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Up")));
                local.setNumLibraDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Down")));
                local.setNumRampaUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Up")));
                local.setNumRampaDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Down")));
                local.setNumInterpretesUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Up")));
                local.setNumInterpretesDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Down")));
                local.setTemMapa(resultado.getString(resultado.getColumnIndexOrThrow("Tem_Mapa")));
                itens.add(local);

            }while(resultado.moveToNext());

        }

        return itens;
    }

    public void verBanco(){

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM LOCAL ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                System.out.print(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("TIPO")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Libras_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Rampas_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Interpretes_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Tem_Mapa")));
                System.out.println("");

            }while(resultado.moveToNext());

        }
    }


}
