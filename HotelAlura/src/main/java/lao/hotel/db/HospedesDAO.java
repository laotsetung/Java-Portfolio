package lao.hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospedesDAO {

    private Connection con;

    public HospedesDAO() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        con = connectionFactory.Conexao();
    }

    public List<Hospede> listar() throws SQLException {
        List<Hospede> hospedes1 = new ArrayList<Hospede>();
        String sql = "SELECT * FROM hospedes";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.execute();

            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Hospede h = new Hospede(res.getInt(1),res.getString(2),
                                            res.getString(3), res.getString(4),
                                            res.getString(5), res.getString(6),
                                            res.getInt(7));

                    hospedes1.add(h);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao selecionar registros de Hospedes!");
            throw new RuntimeException(ex);
        }
        return hospedes1;
    }

    public List<Hospede> listarBusca(String nome) throws SQLException {
        List<Hospede> hospedes1 = new ArrayList<Hospede>();
        String sql = "SELECT * FROM hospedes WHERE NOME LIKE '%"+nome+"%' OR SOBRENOME LIKE '%"+nome+"%'";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.execute();

            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Hospede h = new Hospede(res.getInt(1),res.getString(2),
                            res.getString(3), res.getString(4),
                            res.getString(5), res.getString(6),
                            res.getInt(7));

                    hospedes1.add(h);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao selecionar registros de Hospedes!");
            throw new RuntimeException(ex);
        }
        return hospedes1;
    }

    public void deletar(int id) throws SQLException {
        int idRes = 9999999;
        String sql1 = "SELECT ID_RESERVA FROM hospedes WHERE ID_HOS = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql1)) {
            pStm.setInt(1,id);

            pStm.execute();

            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    idRes = res.getInt(1);
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

            pStm.setInt(1, id);
            pStm.execute();

        }catch (SQLException ex){
            System.out.println("Erro ao deletar hospede");
            throw new RuntimeException(ex);
        }

        String sql3 = "DELETE FROM reservas WHERE ID_RES = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql3)) {
            pStm.setInt(1, idRes);
            pStm.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao deletar reserva");
            throw new RuntimeException(ex);
        }

    }

    public Hospede selectHospedeIdReserva(int id) throws SQLException{

        String sql = "SELECT * FROM hospedes WHERE ID_RESERVA = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setInt(1,id);
            pStm.execute();
            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Hospede h = new Hospede(res.getInt(1),res.getString(2),
                            res.getString(3), res.getString(4),
                            res.getString(5), res.getString(6),
                            res.getInt(7));
                    return h;
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return null;
    }

    public Hospede selectHospede(int id) throws SQLException{

        String sql = "SELECT * FROM hospedes WHERE ID_HOS = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setInt(1,id);
            pStm.execute();
            try (ResultSet res = pStm.getResultSet()) {
                while (res.next()) {
                    Hospede h = new Hospede(res.getInt(1),res.getString(2),
                            res.getString(3), res.getString(4),
                            res.getString(5), res.getString(6),
                            res.getInt(7));
                    return h;
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void updateReg(Hospede novoHosp) throws SQLException {
        String sql = "UPDATE hospedes SET NOME = ?, SOBRENOME = ?, DATA_NASC = ?, NACIONALIDADE = ?, TELEFONE = ? WHERE ID_HOS = ?";

        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setString(1,novoHosp.getNome());
            pStm.setString(2,novoHosp.getsNome());
            pStm.setString(3,novoHosp.getDataNasc());
            pStm.setString(4,novoHosp.getNacionalidade());
            pStm.setString(5,novoHosp.getTelefone());
            pStm.setInt(6, novoHosp.getIdHos());

            boolean execute = pStm.execute();
            System.out.println("Hospedes resultado edit : " + execute);

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
