package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static StudentStorage storage = new FileStudentStorage();

    private static List<Student> list = storage.load();

    public static void main(String[] args) {
        menu();
    }

    // ===== 菜单 =====
    public static void menu() {
        while (true) {
            printMenu();
            System.out.print("请选择功能编号：");
            int c = sc.nextInt();

            switch (c) {
                case 1: add(); break;
                case 2: find(); break;
                case 3: update(); break;
                case 4: delete(); break;
                case 5: showAll(); break;
                case 6: failList(); break;
                case 0:
                    storage.save(list);
                    System.exit(0);
            }
        }
    }
    // ===== 打印系统菜单 =====
    public static void printMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("              学生成绩管理系统");
        System.out.println("============================================");
        System.out.println("  1. 录入学生信息");
        System.out.println("  2. 查询学生信息");
        System.out.println("  3. 修改学生信息");
        System.out.println("  4. 删除学生信息");
        System.out.println("  5. 显示全部学生");
        System.out.println("  6. 查询课程不及格学生名单");
        System.out.println("  0. 保存并退出系统");
        System.out.println("============================================");
    }
    // ===== 1 添加（限制100人）=====
    public static void add() {
        if (list.size() >= 1000) {
            System.out.println("系统容量已满");
            return;
        }

        Student s = new Student();

        System.out.print("学号:");
        s.setId(sc.next());

        System.out.print("姓名:");
        s.setName(sc.next());

        System.out.print("班级:");
        s.setClassName(sc.next());

        System.out.print("高数:");
        s.setMath(sc.nextFloat());

        System.out.print("英语:");
        s.setEnglish(sc.nextFloat());

        System.out.print("计导:");
        s.setCs(sc.nextFloat());

        System.out.print("体育:");
        s.setPe(sc.nextFloat());

        float total = s.getMath() + s.getEnglish() + s.getCs() + s.getPe();
        s.setTotal(total);
        s.setAvg(total / 4);

        list.add(s);
        storage.save(list);

        System.out.println("添加成功");
    }

    // ===== 2 查询（按姓名）=====
    public static void find() {
        System.out.print("输入姓名:");
        String name = sc.next();

        for (Student s : list) {
            if (s.getName().equals(name)) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("未找到");
    }

    // ===== 3 修改 =====
    public static void update() {
        System.out.print("输入姓名:");
        String name = sc.next();

        for (Student s : list) {
            if (s.getName().equals(name)) {

                System.out.print("新学号:");
                s.setId(sc.next());

                System.out.print("新姓名:");
                s.setName(sc.next());

                System.out.print("新班级:");
                s.setClassName(sc.next());

                System.out.print("高数:");
                s.setMath(sc.nextFloat());

                System.out.print("英语:");
                s.setEnglish(sc.nextFloat());

                System.out.print("计导:");
                s.setCs(sc.nextFloat());

                System.out.print("体育:");
                s.setPe(sc.nextFloat());

                float total = s.getMath()+s.getEnglish()+s.getCs()+s.getPe();
                s.setTotal(total);
                s.setAvg(total/4);

                storage.save(list);
                System.out.println("修改成功");
                return;
            }
        }

        System.out.println("未找到");
    }

    // ===== 4 删除 =====
    public static void delete() {
        System.out.print("输入姓名:");
        String name = sc.next();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
                storage.save(list);
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("未找到");
    }

    // ===== 5 显示全部 =====
    public static void showAll() {
        for (Student s : list) {
            System.out.println(s);
        }
    }

    // ===== 6 按课程查询不及格学生名单 =====
    public static void failList() {
        System.out.println("\n========== 查询课程不及格学生名单 ==========");
        System.out.println("请选择课程：");
        System.out.println("1. 高等数学");
        System.out.println("2. 大学英语");
        System.out.println("3. 计算机导论");
        System.out.println("4. 体育");
        System.out.print("请输入课程编号：");
        int course = sc.nextInt();

        boolean found = false;
        String courseName;

        switch (course) {
            case 1:
                courseName = "高等数学";
                break;
            case 2:
                courseName = "大学英语";
                break;
            case 3:
                courseName = "计算机导论";
                break;
            case 4:
                courseName = "体育";
                break;
            default:
                System.out.println("课程编号输入错误！");
                return;
        }

        System.out.println("\n" + courseName + "不及格学生名单：");
        System.out.println("--------------------------------------------");

        for (Student s : list) {
            boolean fail = false;

            switch (course) {
                case 1:
                    fail = s.getMath() < 60;
                    break;
                case 2:
                    fail = s.getEnglish() < 60;
                    break;
                case 3:
                    fail = s.getCs() < 60;
                    break;
                case 4:
                    fail = s.getPe() < 60;
                    break;
            }

            if (fail) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            System.out.println("该课程没有不及格学生。");
        }
    }

}