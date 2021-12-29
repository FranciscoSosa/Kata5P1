package kata5p1;

import java.sql.*;

public class Kata5P1 {
    public static void main(String[] args) {
        addEmailTable();
    }

    private static Connection connect(){
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void query(){
        String selectAll = "SELECT * FROM PEOPLE";
        try(Connection conn = connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(selectAll)){
            while (rs.next()){
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Apellidos") + "\t" +
                        rs.getString("Departamento") + "\t");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addEmailTable(){
        String createEmail = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " direccion text NOT NULL);";
        try(Connection conn = connect();
            Statement stmt = conn.createStatement()){
            stmt.execute(createEmail);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
