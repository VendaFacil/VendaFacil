package br.com.vendafacil.vendafacil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CreuzaFDR on 03/06/2016.
 */
public class DataBaseController {
    SQLiteDatabase db;
    DataBaseCreator vendafacilDB;
    private String[] ALL_FIELDS = {DataBaseCreator.NOMEBANCO,DataBaseCreator.ID,DataBaseCreator.TELEFONE
    ,DataBaseCreator.ENDERECO,DataBaseCreator.DIVIDA,DataBaseCreator.VALORPARCELA,DataBaseCreator.VENCIMENTO};

    DataBaseController(Context context){
        vendafacilDB = new DataBaseCreator(context); //inicializar o banco de dados
    }

    /**
     * Método salvar insere um novo cliente no bando caso ele não exista e caso ele ja exista só atualiza seus campos
     * @param cliente
     * @return
     */
    public boolean salvar(Cliente cliente){
        if(cliente.getId() == 0){ //se não existe id então
            return inserirRegistro(cliente);
        }
        else{
            return updateRegistro(cliente);
        }
    }
    /**
     * Recebe um cliente e insere no banco de dados
     *
     * @param cliente
     * @return true se inseriu e false caso contrario
     */
    public boolean inserirRegistro(Cliente cliente){
        long result;
       //settar content values do cliente
        cliente.setValues();
        //abrindo bando em modo leitura e inserindo dados na tabela
        db = vendafacilDB.getWritableDatabase();
        result = db.insert(DataBaseCreator.TABELA,null,cliente.getValues());
        db.close();

        if(result == -1)
            Log.i("result insert", String.valueOf(result));

        return result > 0;
    }

    /**
     * recebe um cliente e atualiza seus campos no banco de dados
     * retorna true se atualizou e false caso contrario
     * @param cliente
     * @return
     */
    public boolean updateRegistro(Cliente cliente){
        String where = DataBaseCreator.ID +"=" + cliente.getId();
        db = vendafacilDB.getWritableDatabase();
        if(db.update(DataBaseCreator.TABELA,cliente.getValues(),where,null) > 0)
            return true;
        return false;
    }

    /**
     * consulta total no banco de dados
     * @return retorna um cursor com todos os registros no bando
     */
    public Cursor consultarRegistros(){
        try{
            db = vendafacilDB.getReadableDatabase();
            Cursor cursor = db.query(DataBaseCreator.TABELA,ALL_FIELDS,null,null,null,null,null);
            db.close();
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cliente searchByID(int id){
        Cursor cursor = consultarRegistros(); //retornar um cursor com todos os registros
        Cliente novo = new Cliente();

        if(cursor.moveToFirst()){
            do {
                if(cursor.getInt(0) == id)
                    
            }while (cursor.moveToNext());
        }
    }
}
