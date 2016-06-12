package br.com.vendafacil.vendafacil;

import android.content.ContentValues;

/**
 * Created by CreuzaFDR on 04/06/2016.
 */
public class Cliente {
    private ContentValues values;
    private String nome = "";
    private int id = 0; //padrão é zero pois indica que não existe esse cliente ainda
    private String telefone = "";
    private String endereco = "";
    private float divida = 0 ;
    private float valor_parcela = 0;
    private String data_vencimento = "";
    private String complemento = "";

    Cliente(){
        this.values = new ContentValues();
    }

    Cliente(ContentValues values){
        setAll(values);
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public float getDivida() {
        return divida;
    }

    public void setDivida(float divida) {
        this.divida = divida;
    }

    public float getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(float valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContentValues getValues() {
        return values;
    }

    /**
     * Este metodo deve ser chamado apos todos os campos do cliente serem settados
     */

    public void setValues() {
        //inserindo valores no dicionario
        values.put(DataBaseCreator.NOMECLIENTE,getNome());
        values.put(DataBaseCreator.ID,getId());
        values.put(DataBaseCreator.TELEFONE,getTelefone());
        values.put(DataBaseCreator.ENDERECO,getEndereco());
        values.put(DataBaseCreator.DIVIDA,getDivida());
        values.put(DataBaseCreator.VALORPARCELA,getValor_parcela());
        values.put(DataBaseCreator.VENCIMENTO,getData_vencimento());
        values.put(DataBaseCreator.COMPLEMENTO,getComplemento());
    }

    /**
     * recebe um dicionario contentvalues e setta todos os campos do cliente
     * @param values
     */
    public void setAll(ContentValues values){
        this.nome = values.getAsString(DataBaseCreator.NOMECLIENTE);
        this.telefone = values.getAsString(DataBaseCreator.TELEFONE);
        this.endereco = values.getAsString(DataBaseCreator.ENDERECO);
        this.divida = values.getAsFloat(DataBaseCreator.DIVIDA);
        this.valor_parcela = values.getAsFloat(DataBaseCreator.VALORPARCELA);
        this.data_vencimento = values.getAsString(DataBaseCreator.VENCIMENTO);
        this.complemento = values.getAsString(DataBaseCreator.COMPLEMENTO);
    }
}
