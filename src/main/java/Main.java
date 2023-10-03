import java.io.BufferedReader;
import java.io.FileReader;

import com.opencsv.CSVReader;
import repository.CourseRatingRepository;
import repository.CourseRepository;
import repository.StudentRepository;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "info.csv";
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(csvFilePath));
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                String courseName = record[0];
                System.out.println(courseName);
                String studentName = record[1];
                System.out.println(studentName);
                String timestamp = record[2];
                System.out.println(timestamp);
                String rating = record[3];
                System.out.println(rating);
                String comment = record[4];
                System.out.println(comment);

                StudentRepository studentRepository = new StudentRepository();
                int studentId = studentRepository.save(studentName);

                CourseRepository courseRepository = new CourseRepository();
                int courseId = courseRepository.save(courseName);

                CourseRatingRepository courseRatingRepository = new CourseRatingRepository();
                courseRatingRepository.save(studentId, courseId, rating, comment, timestamp);
            }

            csvReader.close();
            fileReader.close();
            System.out.println("Data imported successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
