package br.com.vendafacil.vendafacil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CreuzaFDR on 03/06/2016.
 */
public class DataBaseController {
    SQLiteDatabase db;
    DataBaseCreator vendafacilDB;
    private String[] ALL_FIELDS = {DataBaseCreator.ID, DataBaseCreator.NOMECLIENTE, DataBaseCreator.TELEFONE
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

        //abrindo bando em modo leitura e inserindo dados na tabela
        db = vendafacilDB.getWritableDatabase();

        result = db.insert(DataBaseCreator.TABELA,null,cliente.getValues());
        //db.close(); do not close data base because it will be used on next call and the android kernel will close when its not needed

        if(result == -1)
            Log.i("result insert", String.valueOf(result));

        return result > 0;
    }

    /**
     * recebe um cli
     * ente e atualiza seus campos no banco de dados
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
            //db.close(); do not close data base ''cause it wiill be used on next call
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Este metodo recebe os campos do banco de dados que será feita a consulta
     * E retorna um cursor com todos os registros organizados pelo paramento campos
     *
     * @param campos
     * @return
     */
    public Cursor consultarCampos(String[] campos) {

        try {
            db = vendafacilDB.getReadableDatabase();
            Cursor cursor = db.query(DataBaseCreator.TABELA, campos, null, null, null, null, null);
            //db.close(); do not close database because it will be used on next call
            cursor.moveToFirst();
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Cliente searchByID(int id){
        Cursor cursor = consultarRegistros(); //retornar um cursor com todos os registros
        Cliente novo = new Cliente();

        if(cursor.moveToFirst()){
            do {
                if(cursor.getInt(0) == id)
                {
                    ContentValues values = new ContentValues();
                    novo.setNome(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCreator.NOMECLIENTE)));
                    novo.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCreator.TELEFONE)));
                    novo.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCreator.ENDERECO)));
                    novo.setDivida(cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseCreator.DIVIDA)));
                    novo.setValor_parcela(cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseCreator.VALORPARCELA)));
                    novo.setData_vencimento(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCreator.VENCIMENTO)));
                    novo.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCreator.COMPLEMENTO)));
                    novo.setId(id);
                    return novo;
                }
                    
            }while (cursor.moveToNext());
        }

        return null;
    }


    public void delete(int id){
        String where = DataBaseCreator.ID + "="+ id ;
        db = vendafacilDB.getReadableDatabase();
        db.delete(DataBaseCreator.TABELA,where,ALL_FIELDS);
        // db.close(); do not close data base because it will be used on next call and the android kernel will close when its not needed
    }
}
