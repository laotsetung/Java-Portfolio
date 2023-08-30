package lao.hotel.db;

import java.sql.*;
import java.util.Date;

public class InsertHospede implements AutoCloseable {

    private Connection con;
    private int id_insert;
    private PreparedStatement pStm;

    private int id_res;
    private String nome;
    private String sNome;
    private String telefone;
    private String dataNasc;
    private String nacionalidade;

    public int getId_insert() {
        return id_insert;
    }

    public InsertHospede(Hospede h, int id_reserva)  {
        id_res = id_reserva;
        nome = h.getNome();
        sNome = h.getsNome();
        telefone = h.getTelefone();
        dataNasc = h.getDataNasc();
        nacionalidade = h.getNacionalidade();
    }

    public void inserir() throws SQLException{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.Conexao();

        con.setAutoCommit(false);
        pStm = con.prepareStatement("INSERT INTO hospedes (NOME, SOBRENOME, DATA_NASC, NACIONALIDADE, TELEFONE, ID_RESERVA) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        pStm.setString(1,nome);
        pStm.setString(2, sNome);
        pStm.setString(3, dataNasc);
        pStm.setString(4,nacionalidade);
        pStm.setString(5,telefone);
        pStm.setInt(6, id_res);

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
