package lao.hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class loginDAO {

    private Connection con;

    public loginDAO() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        con = connectionFactory.Conexao();

    }

    public String[] selectUser(String user) throws SQLException {
        String[] resultado = new String[2];
        resultado[0] = user;
        String sql = "SELECT PASS FROM users WHERE USUARIO = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setString(1,user);
            pStm.execute();
            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    resultado[1] = res.getString(1);
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return resultado;
    }



}
