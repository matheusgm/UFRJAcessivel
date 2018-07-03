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
        contentValues.put("Num_Audicao_Up","0");
        contentValues.put("Num_Audicao_Down","0");
        contentValues.put("Cor_Audicao", (String) null);
        contentValues.put("Num_Mobilidade_Up","0");
        contentValues.put("Num_Mobilidade_Down","0");
        contentValues.put("Cor_Mobilidade", (String) null);
        contentValues.put("Num_Visao_Up","0");
        contentValues.put("Num_Visao_Down","0");
        contentValues.put("Cor_Visao", (String) null);
        contentValues.put("Tem_Mapa","0");


        conexao.insertOrThrow("LOCAL",null,contentValues);

    }

    public void inserir2TESTE(Local local){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", local.getName());
        contentValues.put("TIPO", local.getTipo());
        contentValues.put("Num_Audicao_Up",local.getNumAudicaoUp());
        contentValues.put("Num_Audicao_Down",local.getNumAudicaoDown());
        contentValues.put("Cor_Audicao",(String) null);
        contentValues.put("Num_Mobilidade_Up",local.getNumMobilidadeUp());
        contentValues.put("Num_Mobilidade_Down",local.getNumMobilidadeDown());
        contentValues.put("Cor_Mobilidade",(String) null);
        contentValues.put("Num_Visao_Up",local.getNumVisaoUp());
        contentValues.put("Num_Visao_Down",local.getNumVisaoDown());
        contentValues.put("Cor_Visao",(String) null);
        contentValues.put("Tem_Mapa","0");




        conexao.insertOrThrow("LOCAL",null,contentValues);

    }

    public void update(ContentValues values, int id){
        conexao.update("LOCAL", values, "CODIGO = "+id, null);
    }

    public void ordenarAudicao(ArrayList<Local> local){

        int ini;
        int fim;
        for(fim = local.size() - 1; fim > 0; fim-- ){
            int troca = 0;
            for(ini = 0; ini < fim; ini++){

                if( (Integer.parseInt(local.get(ini).getNumAudicaoUp()) - Integer.parseInt(local.get(ini).getNumAudicaoDown())) < (Integer.parseInt(local.get(ini + 1).getNumAudicaoUp()) - Integer.parseInt(local.get(ini + 1).getNumAudicaoDown())) ){
                    Local temp = local.get(ini);
                    local.set(ini, local.get(ini + 1));
                    local.set(ini + 1, temp);
                    troca++;
                }else if( (Integer.parseInt(local.get(ini).getNumAudicaoUp()) - Integer.parseInt(local.get(ini).getNumAudicaoDown())) == (Integer.parseInt(local.get(ini + 1).getNumAudicaoUp()) - Integer.parseInt(local.get(ini + 1).getNumAudicaoDown())) ){

                    if( Integer.parseInt(local.get(ini).getNumAudicaoUp()) < Integer.parseInt(local.get(ini + 1).getNumAudicaoDown()) ){
                        Local temp = local.get(ini);
                        local.set(ini, local.get(ini + 1));
                        local.set(ini + 1, temp);
                        troca++;
                    }

                }
            }
            if(troca==0){
                break;
            }
        }
    }

    public void ordenarVisao(ArrayList<Local> local){

        int ini;
        int fim;
        for(fim = local.size() - 1; fim > 0; fim-- ){
            int troca = 0;
            for(ini = 0; ini < fim; ini++){

                if( (Integer.parseInt(local.get(ini).getNumVisaoUp()) - Integer.parseInt(local.get(ini).getNumVisaoDown())) < (Integer.parseInt(local.get(ini + 1).getNumVisaoUp()) - Integer.parseInt(local.get(ini + 1).getNumVisaoDown())) ){
                    Local temp = local.get(ini);
                    local.set(ini, local.get(ini + 1));
                    local.set(ini + 1, temp);
                    troca++;
                }else if( (Integer.parseInt(local.get(ini).getNumVisaoUp()) - Integer.parseInt(local.get(ini).getNumVisaoDown())) == (Integer.parseInt(local.get(ini + 1).getNumVisaoUp()) - Integer.parseInt(local.get(ini + 1).getNumVisaoDown())) ){

                    if( Integer.parseInt(local.get(ini).getNumVisaoUp()) < Integer.parseInt(local.get(ini + 1).getNumVisaoDown()) ){
                        Local temp = local.get(ini);
                        local.set(ini, local.get(ini + 1));
                        local.set(ini + 1, temp);
                        troca++;
                    }

                }
            }
            if(troca==0){
                break;
            }
        }
    }

    public void ordenarMobilidade(ArrayList<Local> local){

        int ini;
        int fim;
        for(fim = local.size() - 1; fim > 0; fim-- ){
            int troca = 0;
            for(ini = 0; ini < fim; ini++){

                if( (Integer.parseInt(local.get(ini).getNumMobilidadeUp()) - Integer.parseInt(local.get(ini).getNumMobilidadeDown())) < (Integer.parseInt(local.get(ini + 1).getNumMobilidadeUp()) - Integer.parseInt(local.get(ini + 1).getNumMobilidadeDown())) ){
                    Local temp = local.get(ini);
                    local.set(ini, local.get(ini + 1));
                    local.set(ini + 1, temp);
                    troca++;
                }else if( (Integer.parseInt(local.get(ini).getNumMobilidadeUp()) - Integer.parseInt(local.get(ini).getNumMobilidadeDown())) == (Integer.parseInt(local.get(ini + 1).getNumMobilidadeUp()) - Integer.parseInt(local.get(ini + 1).getNumMobilidadeDown())) ){

                    if( Integer.parseInt(local.get(ini).getNumMobilidadeUp()) < Integer.parseInt(local.get(ini + 1).getNumMobilidadeDown()) ){
                        Local temp = local.get(ini);
                        local.set(ini, local.get(ini + 1));
                        local.set(ini + 1, temp);
                        troca++;
                    }

                }
            }
            if(troca==0){
                break;
            }
        }
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
                    local.setNumAudicaoUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Up")));
                    local.setNumAudicaoDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Down")));
                    local.setCorAudicao(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Audicao")));
                    local.setNumMobilidadeUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Up")));
                    local.setNumMobilidadeDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Down")));
                    local.setCorMobilidade(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Mobilidade")));
                    local.setNumVisaoUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Up")));
                    local.setNumVisaoDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Down")));
                    local.setCorVisao(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Visao")));
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
                local.setNumAudicaoUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Up")));
                local.setNumAudicaoDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Down")));
                local.setCorAudicao(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Audicao")));
                local.setNumMobilidadeUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Up")));
                local.setNumMobilidadeDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Down")));
                local.setCorMobilidade(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Mobilidade")));
                local.setNumVisaoUp(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Up")));
                local.setNumVisaoDown(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Down")));
                local.setCorVisao(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Visao")));
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
                System.out.println("");
                System.out.print(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("TIPO")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Audicao_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Audicao")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Mobilidade_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Mobilidade")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Up")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Num_Visao_Down")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Cor_Visao")));
                System.out.println("");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("Tem_Mapa")));
                System.out.println("");

            }while(resultado.moveToNext());

        }
    }


}
