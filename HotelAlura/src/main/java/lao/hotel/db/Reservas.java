package lao.hotel.db;

import lao.hotel.extras.DateManipulation;

import java.util.Date;

public class Reservas {

    private int ID_RES;
    private String DtEntrada;
    private String DtSaida;
    private double Valor;
    private String forma_pag;
    private DateManipulation dm = new DateManipulation();

    /*
    Construtor usado para criar o objeto reserva que VEM da base de dados!
     */
    public Reservas (int id, String dte, String dts, double val, String f_pag){

        this.ID_RES = id;
        this.DtEntrada = dte;
        this.DtSaida = dts;
        this.Valor = val;
        this.forma_pag = f_pag;
    }

    /*construtor usado para criar uma nova reserva
    a partir da infos inseridas pelo usuario
    com o valor sendo uma String, que será convertida para double
    * */
    public Reservas (Date dte, Date dts, String val, String f_pag){

        System.out.println(val);

        val = val.replace("R$","");

        System.out.println(val);
        this.DtEntrada = dm.DateToString(dte);
        this.DtSaida = dm.DateToString(dts);
        this.Valor = Double.parseDouble(val);
        this.forma_pag = f_pag;
    }

    public int getID_RES() {
        return ID_RES;
    }

    public void setID_RES(int ID_RES) {
        this.ID_RES = ID_RES;
    }

    public String getDtEntrada() {
        return DtEntrada;
    }

    public void setDtEntrada(Date dtEntrada) {
        DtEntrada = dm.DateToString(dtEntrada);
    }
    public void setDtEntrada(String dtEntrada) {
        DtEntrada = dtEntrada;
    }

    public String getDtSaida() {
        return DtSaida;
    }

    public void setDtSaida(Date dtSaida) {
        DtSaida = dm.DateToString(dtSaida);
    }
    public void setDtSaida(String dtSaida) {
        DtSaida = dtSaida;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public String getForma_pag() {
        return forma_pag;
    }

    public void setForma_pag(String forma_pag) {
        this.forma_pag = forma_pag;
    }
}
