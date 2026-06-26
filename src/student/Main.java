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
            System.out.println("\n1添加 2查询 3修改 4删除 5显示全部 6不及格查询 0退出");
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

    // ===== 1 添加（限制100人）=====
    public static void add() {

        if (list.size() >= 100) {
            System.out.println("已满100人");
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

    // ===== 6 不及格查询（要求3）=====
    public static void failList() {
        System.out.println("不及格学生:");

        for (Student s : list) {
            if (s.getMath() < 60 ||
                    s.getEnglish() < 60 ||
                    s.getCs() < 60 ||
                    s.getPe() < 60) {

                System.out.println(s);
            }
        }
    }
}