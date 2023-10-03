package repository;

import config.DBConfig;

import java.sql.*;

public class CourseRepository {
    public int save(String courseName) throws SQLException {
        Connection connection = DBConfig.getConnection();
        String sql = "INSERT INTO course (course_name) VALUES (?)";
        PreparedStatement prs = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prs.setString(1, courseName);
        int affectedRows = prs.executeUpdate();
        connection.close();
        if (affectedRows == 0) {
            throw new SQLException("saving course name failed, no rows affected.");
        }

        try (ResultSet generatedKeys = prs.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("saving course name failed, no ID obtained.");
            }
        }
    }
}
