package student;

import java.util.List;

// AI辅助优化接口抽象设计（约10%-20%）
//该接口设计目的是为了持久化储存学生数据，使Main类只依赖于接口，不依赖具体实现
public interface StudentStorage {
    //用于保存学生数据
    void save(List<Student> list);
    //用于读取学生数据
    List<Student> load();
}