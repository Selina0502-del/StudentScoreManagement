package student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStudentStorage implements StudentStorage {

    private static final String FILE_NAME = "student.txt";

    @Override
    public void save(List<Student> list) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(list);
        } catch (Exception e) {
            System.out.println("保存失败");
        }
    }

    @Override
    public List<Student> load() {
        File file = new File(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) in.readObject();
        } catch (Exception e) {
            System.out.println("读取失败");
            return new ArrayList<>();
        }
    }
}