package Entities;

import Utils.DatabaseManager;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private static final String CREATE_USER_QUERRY = "insert into user(tg_id) values(%s);";

    private Long id;
    private String condition;
    private Boolean inSearch;
    private Long tgId;

    User(Long id) {
        if(!isExist()){

        }
    }

    public Boolean isExist() {
        try {
            Statement st = DatabaseManager.con.createStatement();
            if(st == null){
                throw new RuntimeException("Statement is null");
            }
            ResultSet rs = st.executeQuery(
                    String.format(
                            "select count(*) from user where tg_id=%s",
                            tgId
                    )
            );
            if(rs == null){
                throw new RuntimeException("ResultSet is null");
            }
            rs.next();
            Boolean res = rs.getInt(0) == 1;
            rs.close();
            st.close();
            return res;
        } catch (SQLException e) {
            // TODO: обработка исключения
            e.printStackTrace();
            throw new RuntimeException("Error while creating statement");
        }
    }

    public void createUser(){
        try {
            Statement st = DatabaseManager.con.createStatement();
            st.executeUpdate(String.format(CREATE_USER_QUERRY, tgId));
            st.close();
        } catch (SQLException e){
            // TODO: обработка исключения
            e.printStackTrace();
        }
    }
}
