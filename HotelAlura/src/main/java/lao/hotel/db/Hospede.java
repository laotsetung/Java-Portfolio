package lao.hotel.db;

import lao.hotel.extras.DateManipulation;

import java.util.Date;

public class Hospede {
    private String nome;
    private String sNome;
    private String telefone;
    private String dataNasc;
    private int id_res;
    private String nacionalidade;

    public int getIdHos() {
        return idHos;
    }

    public void setIdHos(int idHos) {
        this.idHos = idHos;
    }

    private int idHos;

    private DateManipulation dm = new DateManipulation();

    public Hospede(int idHos, String nome, String sNome, String dataNasc, String nacionalidade, String telefone, int id_res){

        this.nome = nome;
        this.sNome = sNome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.id_res = id_res;
        this.nacionalidade = nacionalidade;
        this.idHos = idHos;
    }
    public Hospede(String nome, String sNome, String telefone, Date dataNasc, int id_res, String nacionalidade){

        this.nome = nome;
        this.sNome = sNome;
        this.telefone = telefone;
        this.dataNasc = dm.DateToString(dataNasc);
        this.id_res = id_res;
        this.nacionalidade = nacionalidade;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getsNome() {
        return sNome;
    }

    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dm.DateToString(dataNasc);
    }
    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
