package repository;

import config.DBConfig;

import java.sql.*;

public class StudentRepository {
    public int save(String studentName) throws SQLException {
        Connection connection = DBConfig.getConnection();
        String sql = "INSERT INTO student (student_name) VALUES (?)";
        PreparedStatement prs = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prs.setString(1, studentName);
        int affectedRows = prs.executeUpdate();
        connection.close();
        if (affectedRows == 0) {
            throw new SQLException("saving student name failed, no rows affected.");
        }
        try (ResultSet generatedKeys = prs.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("saving student name failed, no ID obtained.");
            }
        }
    }
}
