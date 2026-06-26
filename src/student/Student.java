package student;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;       // 学号
    private String name;     // 姓名
    private String className;// 班级

    private float math;
    private float english;
    private float cs;
    private float pe;

    private float total;
    private float avg;

    // getter/setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public float getMath() { return math; }
    public void setMath(float math) { this.math = math; }

    public float getEnglish() { return english; }
    public void setEnglish(float english) { this.english = english; }

    public float getCs() { return cs; }
    public void setCs(float cs) { this.cs = cs; }

    public float getPe() { return pe; }
    public void setPe(float pe) { this.pe = pe; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public float getAvg() { return avg; }
    public void setAvg(float avg) { this.avg = avg; }

    @Override
    public String toString() {
        return id + " " + name + " " + className +
                " | 数学:" + math +
                " 英语:" + english +
                " 计导:" + cs +
                " 体育:" + pe +
                " 总分:" + total +
                " 平均:" + avg;
    }
}