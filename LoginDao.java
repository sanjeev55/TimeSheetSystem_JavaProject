package com.javaee.javaee2022teamnine.dao;

import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    
    private LoginDao() {
    };

    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    // 2.인스턴스를 직접 생성
    // 멤버변수 보호를 위해 private 접근제어자 사용
    // 인스턴스 생성없이 접근가능하도록 static 키워드 사용
    private static LoginDao instance = new LoginDao();

    // 3.생성된 인스턴스를 외부로리턴하는 Getter 정의
    public static LoginDao getInstance() {
        return instance;
    }

    // 로그인 기능수행을 위한 login()메서드 정의
    // 리턴 타입 : boolean, 파라미터 : id,pass
    public boolean login(String email, String password) {
        boolean b = false;
//        System.out.println(id+", "+pass);

        // java5 데이터베이스의 customer 테이블에 있는 id, pass와 비교하여
        // 둘다 일치하면 true, 아니면 false 리턴
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try(Connection connection = dbService.initDB()) {

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }

}
