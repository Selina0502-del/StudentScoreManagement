package student;

import java.util.List;

public interface StudentStorage {
    void save(List<Student> list);
    List<Student> load();
}