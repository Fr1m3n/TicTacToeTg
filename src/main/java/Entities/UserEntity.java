package Entities;

import Utils.DatabaseManager;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для работы с записью о пользователе в БД
 */
public class UserEntity {
    /**
     * Заготовки для запросов
     */
    private static final String CREATE_USER_QUERRY = "insert into user(tg_id) values(%s);";
    private static final String GET_USER_QUERRY = "select * from user where id=%s;";
    private static final String UPDATE_USER_QUERRY = "update user set %s=%s where %s;";

    /**
     * Поля записи
     */
    private Long id;
    private String condition;
    private Boolean inSearch;
    private Long tgId;

    /**
     * Конструктор собирает значения из БЖ в поля объекта.
     * В случае отсутствия записи о нём - создаёт.
     * @param id id учётной записи telegram
     */
    public UserEntity(Long id) {
        if(!isExist()){
            createUser();
        }
        try{
            Statement st = DatabaseManager.con.createStatement();
            ResultSet rs = st.executeQuery(
                    String.format(
                            GET_USER_QUERRY,
                            tgId)
            );
            while(rs.next()){
                inSearch = rs.getBoolean("insearch");
                id = rs.getLong("id");
                condition = rs.getString("condition");
            }
        } catch (SQLException e){
            // TODO: обработка исключения
            e.printStackTrace();
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

    public Long getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public Boolean getInSearch() {
        return inSearch;
    }

    public Long getTgId() {
        return tgId;
    }

    /**
     * Функция, которая изменяет значения этой записи в БД
     * @param columnName - название изменияемой колонки
     * @param value - значение для записи
     */
    public void set(String columnName, String value){
        try {
            Statement st = DatabaseManager.con.createStatement();
            st.executeUpdate(
                    String.format(
                            UPDATE_USER_QUERRY,
                            columnName,
                            value,
                            id
                    )
            );
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: обработать исключния
        }
    }
}
