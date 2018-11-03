package com.example.matheus.ufrjacessivel.Evento;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EventoRepositorio {


    private SQLiteDatabase conexao;

    public EventoRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Evento evento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", evento.getNome());

        conexao.insertOrThrow("EVENTO",null,contentValues);

    }

    public void inserir2TESTE(Evento evento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", evento.getNome());

        conexao.insertOrThrow("EVENTO",null,contentValues);

    }

    public void update(ContentValues values, int id){
        conexao.update("EVENTO", values, "CODIGO = "+id, null);
    }

    public void ordenarPorData(ArrayList<Evento> evento){

        int ini;
        int fim;
        for(fim = evento.size() - 1; fim > 0; fim-- ){
            int troca = 0;
            for(ini = 0; ini < fim; ini++){
                /*
                if( (Integer.parseInt( x ) < ( y ) ){
                    Evento temp = evento.get(ini);
                    evento.set(ini, evento.get(ini + 1));
                    evento.set(ini + 1, temp);
                    troca++;
                }else if( ( x ) == ( y ) ){

                    if( Integer.parseInt( h ) < Integer.parseInt( h ) ){
                        Evento temp = evento.get(ini);
                        evento.set(ini, evento.get(ini + 1));
                        evento.set(ini + 1, temp);
                        troca++;
                    }

                }
                */
            }
            if(troca==0){
                break;
            }
        }
    }

    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("EVENTO","CODIGO = ?",parametros);
    }

    public Evento buscarEvento(int id){

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM EVENTO ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                if(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")) == id) {
                    Evento evento = new Evento();

                    evento.setId(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                    evento.setNome(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));

                    return evento;
                }
            }while(resultado.moveToNext());

        }
        return null;

    }

    public ArrayList<Evento> buscarTodos(){

        ArrayList<Evento> itens = new ArrayList<Evento>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM EVENTO ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                Evento evento = new Evento();

                evento.setId(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                evento.setNome(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));

                itens.add(evento);

            }while(resultado.moveToNext());

        }

        return itens;
    }

    public void verBanco(){

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append("   FROM EVENTO ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                System.out.println("");
                System.out.print(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                System.out.print(" ");
                System.out.print(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                System.out.print(" ");

            }while(resultado.moveToNext());

        }
    }

}
