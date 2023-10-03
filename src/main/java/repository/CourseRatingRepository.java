package repository;

import config.DBConfig;

import java.sql.*;

public class CourseRatingRepository {
    public void save(int studentId, int courseID, String timeStamp, String rating, String comment) throws SQLException {
        Connection connection = DBConfig.getConnection();
        String sql = "INSERT INTO studentCourseRating (student_id, course_id, rating, comment, timestamp) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prs = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prs.setInt(1, studentId);
        prs.setInt(2, courseID);
        prs.setString(3, rating);
        prs.setString(4, comment);
        prs.setString(5, timeStamp);
        int affectedRows = prs.executeUpdate();
        connection.close();
        if (affectedRows == 0) {
            throw new SQLException("Creating course rating failed, no rows affected.");
        }
    }
}
