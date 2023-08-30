package lao.hotel.db;

import lao.hotel.db.ConnectionFactory;

import java.sql.*;

public class InsertReserva implements AutoCloseable {

    private Connection con;
    private String dte;
    private String dts;
    private double val;
    private String forma_pag;
    private int id_insert;
    private PreparedStatement pStm;

    public int getId_insert() {
        return id_insert;
    }

    public InsertReserva(Reservas r)  {

        //int id = r.getID_RES();
        dte = r.getDtEntrada();
        dts = r.getDtSaida();
        val = r.getValor();
        forma_pag = r.getForma_pag();
    }

    public void inserir() throws SQLException{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.Conexao();

        con.setAutoCommit(false);
        pStm = con.prepareStatement("INSERT INTO reservas (DATA_ENTRADA, DATA_SAIDA, VALOR, PAGAMENTO) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        pStm.setString(1, dte);
        pStm.setString(2, dts);
        pStm.setDouble(3, val);
        pStm.setString(4,forma_pag);

        pStm.executeUpdate();

        try (ResultSet rs = pStm.getGeneratedKeys()) {
            if (rs.next()) {
                id_insert = rs.getInt(1);
            }
        }
    }

    public void comita() throws SQLException {
        con.commit();
        con.close();
    }

    public void rollB() throws SQLException {
        con.rollback();
    }


    @Override
    public void close() throws Exception {
        con.close();
    }
}
