package student;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static StudentStorage storage = new FileStudentStorage();

    private static List<Student> list = storage.load();

    public static void main(String[] args) {
        menu();
    }

    // 菜单
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
                case 7: addTestStudents(); break;
                case 8: clearAllStudents(); break;
                case 0:
                    storage.save(list);
                    System.out.println("数据已保存，系统已退出。");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }
    // 打印菜单
    public static void printMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("              学生成绩管理系统");
        System.out.println("============================================");
        System.out.println("1.录入学生信息");
        System.out.println("2.查询学生信息");
        System.out.println("3.修改学生信息");
        System.out.println("4.删除学生信息");
        System.out.println("5.显示全部学生");
        System.out.println("6.查询课程不及格学生名单");
        System.out.println("7.生成100名测试学生数据");
        System.out.println("8.清空全部学生数据");
        System.out.println("0.保存并退出系统");
        System.out.println("============================================");
    }
    // 1.录入学生信息
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

    // 2.查询学生信息
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

    // 3.修改学生信息
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

    // 4.删除学生信息（按学号 避免姓名重复导致多删）
    public static void delete() {
        System.out.println("\n========== 删除学生信息 ==========");
        System.out.print("请输入要删除学生的学号：");
        String id = sc.next();

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);

            if (s.getId().equals(id)) {
                System.out.println("找到学生信息：");
                System.out.println(s);

                System.out.print("确认删除该学生全部信息吗？请输入 是 确认：");
                String confirm = sc.next();

                if (confirm.equalsIgnoreCase("是")) {
                    list.remove(i);
                    storage.save(list);
                    System.out.println("删除成功");
                } else {
                    System.out.println("已取消删除。");
                }
                return;
            }
        }

        System.out.println("未找到该学号对应的学生");
    }

    // 5.显示全部学生信息
    public static void showAll() {
        System.out.println("\n========== 全部学生信息 ==========");
        if (list.isEmpty()) {
            System.out.println("当前系统中暂无学生信息。");
            return;
        }
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("当前学生总人数：" + list.size());
    }

    // 6.查询指定课程的不及格学生
    public static void failList() {
        System.out.println("\n========== 查询课程不及格学生名单 ==========");
        System.out.println("请选择课程：");
        System.out.println("1.高等数学");
        System.out.println("2.大学英语");
        System.out.println("3.计算机导论");
        System.out.println("4.体育");
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
            float score;

            switch (course) {
                case 1:
                    score = s.getMath();
                    break;
                case 2:
                    score = s.getEnglish();
                    break;
                case 3:
                    score = s.getCs();
                    break;
                case 4:
                    score = s.getPe();
                    break;
                default:
                    return;
            }

            if (score < 60) {
                System.out.println(
                        s.getName() +
                                " [学号] " + s.getId() +
                                " [班级] " + s.getClassName() +
                                " | " + courseName + ":" + score
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("该课程没有不及格学生");
        }
    }

    // 7.生成100名测试学生，用于验证系统至少能够管理100名学生
    public static void addTestStudents() {
        System.out.println("\n========== 生成100名测试学生 ==========");

        list.clear();

        for (int i = 1; i <= 100; i++) {
            Student s = new Student();

            s.setId(String.format("202512%03d", i));
            s.setName("测试学生" + i);
            s.setClassName("计科2503");

            s.setMath(60 + i % 40);
            s.setEnglish(55 + i % 45);
            s.setCs(50 + i % 50);
            s.setPe(65 + i % 35);

            float total = s.getMath() + s.getEnglish() + s.getCs() + s.getPe();
            s.setTotal(total);
            s.setAvg(total / 4);

            list.add(s);
        }

        storage.save(list);
        System.out.println("已生成100名测试学生，并完成保存。");
    }
    // 8.清空全部学生数据
    public static void clearAllStudents() {
        System.out.println("\n========== 清空全部学生数据 ==========");
        System.out.print("确认清空全部学生信息吗？请输入 是 确认：");
        String confirm = sc.next();

        if (confirm.equals("是")) {
            list.clear();
            storage.save(list);
            System.out.println("全部学生数据已清空。");
        } else {
            System.out.println("已取消清空操作。");
        }
    }
}