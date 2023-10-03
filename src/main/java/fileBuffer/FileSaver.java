package fileBuffer;

import com.opencsv.CSVReader;
import repository.CourseRatingRepository;
import repository.CourseRepository;
import repository.StudentRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSaver {
    public static void save() {
        String csvFilePath = "info.csv";
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(csvFilePath));
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;

            List<String> combinedLines = new ArrayList<>();
            while ((record = csvReader.readNext()) != null) {
                Collections.addAll(combinedLines, record);
                if (combinedLines.size() >= 5) {

                    String courseName = combinedLines.get(0);
                    System.out.println(courseName);
                    String studentName = combinedLines.get(1);
                    System.out.println(studentName);
                    Timestamp timestamp = Timestamp.valueOf(combinedLines.get(2) + " ".concat(combinedLines.get(3)));
                    System.out.println(timestamp);
                    Double rating = Double.parseDouble(combinedLines.get(4));
                    System.out.println(rating);
                    String comment = combinedLines.get(5);
                    System.out.println(comment);

                    StudentRepository studentRepository = new StudentRepository();
                    int studentId = studentRepository.save(studentName);

                    CourseRepository courseRepository = new CourseRepository();
                    int courseId = courseRepository.save(courseName);

                    CourseRatingRepository courseRatingRepository = new CourseRatingRepository();
                    courseRatingRepository.save(studentId, courseId, rating, comment, timestamp);

                    combinedLines.clear();
                }
            }

            csvReader.close();
            fileReader.close();
            System.out.println("Data imported successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
