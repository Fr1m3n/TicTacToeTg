package Utils;

import java.sql.*;

public class DatabaseManager {
    // Переменные для работы с базой данных
    public static Connection con;
    public static Statement statemnt;
    public static final String DB_HOST = "localhost";
    public static final String DB_PASS = "1";
    public static final String DB_USER = "postgres";
    public static final String DB_NAME = "tictactoe_db";
    public static final String DB_PORT = "5432";
    public static final String DB_USER_TABLE_NAME = "user";

    public void init(){
        String url = generateUrl();
        try {
            DatabaseManager.con = DriverManager.getConnection(
                    url,
                    DB_USER,
                    DB_PASS
            );
        } catch (SQLException e) {
            // TODO: обработать исключение
            e.printStackTrace();
        }
    }

    private String generateUrl(){
        StringBuilder sb = new StringBuilder("jdbc:postgresql://");
        sb.append(DB_HOST);
        sb.append(":");
        sb.append(DB_PORT);
        sb.append("/");
        sb.append(DB_NAME);
        return sb.toString();
    }
}
