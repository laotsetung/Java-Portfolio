package lao.hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarReservas {

    private Connection con;
    private PreparedStatement pStm;
    public BuscarReservas() throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.Conexao();

        pStm = con.prepareStatement("SELECT * FROM reservas");



    }

}
