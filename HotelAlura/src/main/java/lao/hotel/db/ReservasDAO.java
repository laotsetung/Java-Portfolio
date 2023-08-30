package lao.hotel.db;

import lao.hotel.db.Reservas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    private Connection con;

    public ReservasDAO() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        con = connectionFactory.Conexao();

    }

    public Reservas selectReserva(int id) throws SQLException {

        String sql = "SELECT * FROM reservas WHERE ID_RES = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setInt(1,id);
            pStm.execute();
            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Reservas r = new Reservas(res.getInt(1), res.getString(2), res.getString(3),res.getDouble(4), res.getString(5));
                    return r;
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return null;
    }



    public List<Reservas> listar() throws SQLException {
        List<Reservas> reservas1 = new ArrayList<Reservas>();
        String sql = "SELECT * FROM reservas";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.execute();

            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Reservas p = new Reservas(res.getInt(1), res.getString(2), res.getString(3),res.getDouble(4), res.getString(5));

                    reservas1.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao selecionar registros!");
        }
        return reservas1;
    }


    public void deletar(int id) throws SQLException {
        int idHos = 9999999;
        String sql1 = "SELECT ID_HOS FROM hospedes WHERE ID_RESERVA = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql1)) {
            pStm.setInt(1,id);

            pStm.execute();

            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    idHos = res.getInt(1);
                }
            }catch (SQLException ex){
                System.out.println("Erro ao achar id da reserva");
                throw new RuntimeException(ex);
            }

        }catch (SQLException ex){
            System.out.println("Erro ao achar id da reserva");
            throw new RuntimeException(ex);
        }

        String sql = "DELETE FROM hospedes WHERE ID_HOS = ?";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {

            pStm.setInt(1, idHos);
            pStm.execute();

        }catch (SQLException ex){
            System.out.println("Erro ao deletar hospede");
            throw new RuntimeException(ex);
        }

        String sql3 = "DELETE FROM reservas WHERE ID_RES = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql3)) {
            pStm.setInt(1, id);
            pStm.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao deletar reserva");
            throw new RuntimeException(ex);
        }

    }


    public void updateReg(Reservas novaReserva) throws SQLException {
        String sql = "UPDATE reservas SET DATA_ENTRADA = ?, DATA_SAIDA = ?, VALOR = ?, PAGAMENTO = ? WHERE ID_RES = ?";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setString(1,novaReserva.getDtEntrada());
            pStm.setString(2,novaReserva.getDtSaida());
            pStm.setDouble(3,novaReserva.getValor());
            pStm.setString(4,novaReserva.getForma_pag());
            pStm.setInt(5,novaReserva.getID_RES());

            boolean execute = pStm.execute();
            System.out.println("Reservas resultado edit : " + execute);

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
