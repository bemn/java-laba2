import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

class Main {
    Scanner sc = new Scanner(System.in, "Cp866");

    public int read_Int() {
        while (true) {
            try {
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Error: not an integer.");
            }
        }
    }
    
    public long read_Long() {
        while (true) {
            try {
                return Long.parseLong(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Error: not a long integer.");
            }
        }
    }

    public String read_String() {
        return sc.hasNextLine() ? sc.nextLine().trim() : "";
    }

    public String read_wordString() {
        while (true) {
            if (!sc.hasNextLine()) return "";

            String s = sc.nextLine().trim();

            if (!s.isEmpty() && !s.equals("-")) {
                return s;
            }
        }
    }

    public char read_Char(){
        while (sc.hasNext()) {
            String first = sc.next();
            if (first.length() == 1) {
                return first.charAt(0);
            } else {
                System.out.println("Error: enter a single character");
            }
        }
        return ' ';
    }

    public char read_numChar(){
        while (sc.hasNext()) {
            String first = sc.next();
            if (first.length() == 1 && Character.isDigit(first.charAt(0))) {
                return first.charAt(0);
            } else {
                System.out.println("Error: enter a single digit character");
            }
        }
        return ' ';
    }


    public static class Dom {
        private int floors;
        public Dom(int floors) {
            this.floors = floors;
        }
        
        public String toString() {
            int lastTwoDigits = floors % 100;
            int lastDigit = floors % 10;
            String word;

            if (lastDigit == 1 && (lastTwoDigits < 11 || lastTwoDigits > 14)) {
                word = "этажом";
            } else {
                word = "этажами";
            }
            return "Дом с " + floors + " " + word;
        }
    }

    public static class Imya {
        String firstName;
        String surname;
        String middleName;

        // Только личное имя
        public Imya(String firstName) {
            this.firstName = firstName;
        }

        // Личное имя и фамилия
        public Imya(String firstName, String surname) {
            this.firstName = firstName;
            this.surname = surname;
        }

        // Личное имя, фамилия и отчество
        public Imya(String firstName, String surname, String middleName) {
            this.firstName = firstName;
            this.surname = surname;
            this.middleName = middleName;
        }

        public static Imya create(String firstName, String surname, String middleName) {
            if (surname == null || surname.isEmpty()) {
                return new Imya(firstName);
            }

            if (middleName == null || middleName.isEmpty()) {
                return new Imya(firstName, surname);
            }

            return new Imya(firstName, surname, middleName);
        }

        public String toString() {
            String result = firstName;

            if (middleName != null) {
                result += " " + middleName;
            }

            if (surname != null) {
                result += " " + surname;
            }

            return result;
        }
    }
        
        
    public static class Otdel {
        String name;
        Sotrudnik boss;
        ArrayList<Sotrudnik> employees = new ArrayList<>();


        public Otdel(String name) {
            this.name = name;
        }

        public void addEmployee(Sotrudnik employee) {
            employees.add(employee);
        }

        public Sotrudnik findEmployee(String name) {
            for (Sotrudnik employee : employees) {
                if (employee.name.equals(name)) {
                    return employee;
                }
            }
            return null;
        }

        public void setBossByName(String name) {
            for (Sotrudnik employee : employees) {
                if (employee.name.equals(name)) {
                    boss = employee;
                    // System.out.println(employee.name + " назначен начальником.");
                    return;
                }
            }
        }

        public String toString() {
            return "Отдел " + name + ", начальник: "
                    + (boss == null ? "не назначен" : boss.name);
        }
    }

    public static class Sotrudnik {
        String name;
        Otdel otdel;

        public Sotrudnik(String name, Otdel otdel) {
            this.name = name;
            this.otdel = otdel;
            otdel.addEmployee(this);
        }

        public void printColleagues() {
            for (Sotrudnik employee : otdel.employees) {
                if (employee != this) {
                    System.out.println(employee.name);
                }
            }
        }

        public String toString() {
            if (otdel.boss == this) {
                return name + " начальник отдела " + otdel.name;
            }

            return name + " работает в отделе " + otdel.name
                    + ", начальник которого "
                    + (otdel.boss == null ? "не назначен" : otdel.boss.name);
        }
    }

    public static class Drob {
        int numerator;
        int denominator;

        public Drob(int numerator, int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator cannot be zero.");
            }
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public String toString() {
            return numerator + "/" + denominator;
        }

        public Drob sum(Drob other) {
            return new Drob(
                numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator
            );
        }

        public Drob sum(int number) {
            return new Drob(
                numerator + number * denominator,
                denominator
            );
        }

        public Drob minus(Drob other) {
            return new Drob(
                numerator * other.denominator - other.numerator * denominator,
                denominator * other.denominator
            );
        }

        public Drob minus(int number) {
            return new Drob(
                numerator - number * denominator,
                denominator
            );
        }

        public Drob multiply(Drob other) {
            return new Drob(
                numerator * other.numerator,
                denominator * other.denominator
            );
        }

        public Drob multiply(int number) {
            return new Drob(
                numerator * number,
                denominator
            );
        }

        public Drob div(Drob other) {
            if (other.numerator == 0) {
                throw new ArithmeticException("Division by zero!");
            }
            return new Drob(
                numerator * other.denominator,
                denominator * other.numerator
            );
        }

        public Drob div(int number) {
            if (number == 0) {
                throw new ArithmeticException("Division by zero!");
            }
            return new Drob(
                numerator,
                denominator * number
            );
        }
    }
        


    public static void main(String[] args){
        Main obj = new Main();

        System.out.print("==1.3+4.5 Task==\n");

        Imya name1 = new Imya("Клеопатра");
        Imya name2 = new Imya("Александр","Пушкин", "Сергеевич");
        Imya name3 = new Imya("Владимир","Маяковский");
        Imya name4 = new Imya("Христофор","Бонифатьевич");
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(name4);

        System.out.print("Enter first name: ");
        String firstName = obj.read_wordString();

        System.out.print("Enter surname: ");
        String surname = obj.read_String();

        String middleName = null;
        if (!surname.isEmpty()) {
            System.out.print("Enter middle name: ");
            middleName = obj.read_String();
        }
        Imya fullname = Imya.create(firstName, surname, middleName);
        System.out.println("Received name: " + fullname);


        System.out.print("==1.5 Task==\n");
        Dom house1 = new Dom(1);
        Dom house5 = new Dom(5);
        Dom house23 = new Dom(23);
        System.out.println(house1);
        System.out.println(house5);
        System.out.println(house23);
        
        System.out.print("Enter the number of floors: ");
        Dom houseInput = new Dom(obj.read_Int());
        System.out.println(houseInput);

        
        System.out.print("==2.4+3.4 Task==\n");

        Otdel it = new Otdel("IT");
        Sotrudnik petrov = new Sotrudnik("Петров", it);
        Sotrudnik kozlov = new Sotrudnik("Козлов", it);
        Sotrudnik sidorov = new Sotrudnik("Сидоров", it);
        it.boss = kozlov;
        System.out.println(petrov);
        System.out.println(kozlov);
        System.out.println(sidorov);

        System.out.print("\nВведите название отдела: ");
        Otdel otdel = new Otdel(obj.read_wordString());
        
        System.out.print("Введите количество сотрудников: ");
        int n = obj.read_Int();
        for (int i = 0; i < n; i++) {
            System.out.print("Введите имя сотрудника №" + (i + 1) + ": ");
            String name = obj.read_wordString();
            new Sotrudnik(name, otdel);
        }

        System.out.print("Введите имя начальника: ");
        while (otdel.boss == null) {
            String bossName = obj.read_wordString();
            otdel.setBossByName(bossName);

            if (otdel.boss == null) {
                System.out.println("Cотрудник не найден");
            }
        }

        Sotrudnik selected = null;
        String employeeName = null;
        System.out.print("Введите имя сотрудника для просмотра коллег: ");
        while (selected == null) {
            employeeName = obj.read_wordString();
            selected = otdel.findEmployee(employeeName);

            if (selected == null) {
                System.out.println("Cотрудник не найден");
            }
        }

        System.out.println("\n--- Информация об отделе ---");
        System.out.println("Название отдела: " + otdel.name);
        System.out.println("Начальник отдела: " + otdel.boss.name);
        System.out.println("\nСотрудники отдела:");
        for (Sotrudnik employee : otdel.employees) {
            System.out.println(employee.name);
        }

        System.out.println("\nКоллеги " + employeeName + ":");
        selected.printColleagues();


        System.out.print("==5.5 Task==\n");
        Drob f1 = new Drob(1, 3);
        Drob f2 = new Drob(2, 3);
        Drob f3 = new Drob(3, 4);

        System.out.println(f1 + " + " + f2 + " = " + f1.sum(f2));
        System.out.println(f1 + " + 2 = " + f1.sum(2));

        System.out.println(f1 + " - " + f2 + " = " + f1.minus(f2));
        System.out.println(f1 + " - 2 = " + f1.minus(2));

        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
        System.out.println(f1 + " * 2 = " + f1.multiply(2));

        System.out.println(f1 + " / " + f2 + " = " + f1.div(f2));
        System.out.println(f1 + " / 2 = " + f1.div(2));

        Drob result = f1.sum(f2).div(f3).minus(5);
        System.out.println("\nРезультат цепочки: " + result);

        System.out.print("\n1) Enter numerator and denominator: ");
        String[] input1 = obj.read_wordString().split(" ");
        Drob f4 = new Drob(
            Integer.parseInt(input1[0]),
            Integer.parseInt(input1[1])
        );

        System.out.print("2) Enter numerator and denominator: ");
        String[] input2 = obj.read_wordString().split(" ");
        Drob f5 = new Drob(
            Integer.parseInt(input2[0]),
            Integer.parseInt(input2[1])
        );

        System.out.print("3) Enter numerator and denominator: ");
        String[] input3 = obj.read_wordString().split(" ");
        Drob f6 = new Drob(
            Integer.parseInt(input3[0]),
            Integer.parseInt(input3[1])
        );

        System.out.println("\n--- Результаты операций ---");

        System.out.println(f4 + " + " + f5 + " = " + f4.sum(f5));
        System.out.println(f4 + " + 2 = " + f4.sum(2));

        System.out.println(f4 + " - " + f5 + " = " + f4.minus(f5));
        System.out.println(f4 + " - 2 = " + f4.minus(2));

        System.out.println(f4 + " * " + f5 + " = " + f4.multiply(f5));
        System.out.println(f4 + " * 2 = " + f4.multiply(2));

        System.out.println(f4 + " / " + f5 + " = " + f4.div(f5));
        System.out.println(f4 + " / 2 = " + f4.div(2));

        Drob result2 = f4.sum(f5).div(f6).minus(5);

        System.out.println("\n--- Цепочка операций ---");
        System.out.println(f4 + " + " + f5 + " / " + f6 + " - 5 = " + result2);

        System.out.print("==The end==");
        
    }
}