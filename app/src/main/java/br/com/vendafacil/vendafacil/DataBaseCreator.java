package br.com.vendafacil.vendafacil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * Created by CreuzaFDR on 03/06/2016.
 */
public class DataBaseCreator extends SQLiteOpenHelper{

    protected static String NOMEBANCO = "venda.db";
    protected static String TABELA = "clientes";
    protected static String NOMECLIENTE = "nomecliente";
    protected static String ID = "_id";
    protected static String TELEFONE = "telefone";
    protected static String ENDERECO = "endereco";
    protected static String DIVIDA = "divida";
    protected static String VENCIMENTO = "vencimento";
    protected static String VALORPARCELA = "valorparcela";
    protected static String COMPLEMENTO = "complemento";
    private static  int VERSAO = 1;

    /**
     * CHAMA CONSTRUTOR DA CLASSE PAI CRIANDO UM OPENHELPER DATA BASE
     * @param context
     */
    DataBaseCreator(Context context){
        super(context,NOMEBANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"(" +
                ID + " integer primary key," +
                NOMECLIENTE+" text," +
                TELEFONE + " text,"+
                ENDERECO + " text," +
                DIVIDA +" real,"+
                VENCIMENTO + " TEXT,"+
                VALORPARCELA + " REAL,"+
                COMPLEMENTO + " text" + ")";
        db.execSQL(sql);
        Log.i("OnDBcreation",sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
