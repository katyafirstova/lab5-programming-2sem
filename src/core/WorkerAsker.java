package core;

import model.Color;
import model.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WorkerAsker {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public String askName() {

        String name = new String();
        try {
            System.out.println("Введите имя:");
            name = scanner.nextLine();
            System.out.format("Вы ввели значение: %s\n", name);
            if (name.equals("")) {
                throw new IllegalArgumentException("Имя не может быть пустым!");
            }

        } catch (NoSuchElementException elementException) {
            System.out.println("Имя не распознано!");
        }
        return name;
    }

    public float askX() {

        float x = 0.0f;
        try {
            System.out.println("Введите координату X:");
            x = scanner.nextFloat();
            System.out.format("Вы ввели значение: %s\n", x);
        } catch (NoSuchElementException elementException) {
            System.out.println("Координата не распознана!");
        }
        return x;

    }

    public int askY() {
        int y = 0;
        try {

        System.out.println("Введите координату Y:");
        y = scanner.nextInt();
        System.out.format("Вы ввели значение: %s\n", y);
        if (y == 0) throw new IllegalArgumentException("Поле не может быть пустым!");
        if (y > 92) throw new IllegalArgumentException("Значение поля не может превышать 92!");
        } catch (NoSuchElementException e) {
            System.out.println("Координата не распознана!");
        }
        return y;
    }


    public float askHeight() {

        float height;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение роста:");
        height = scanner.nextFloat();
        System.out.format("Вы ввели значение: %s\n", height);
        if (height < 0) throw new IllegalArgumentException("Значения поле не может быть меньше нуля!");
        if (height == 0) throw new IllegalArgumentException("Значение поля не может быть равным нулю!");

        return height;

    }

    public int askWeight() {

        int weight;
        System.out.println("Введите значение веса:");
        weight = scanner.nextInt();
        System.out.format("Вы ввели значение: %s\n", weight);
        if (weight < 0) throw new IllegalArgumentException("Значение поля не может быть меньше нуля!");

        return weight;

    }

    public Color askColor() {
        System.out.println("Введите одно из возможных значений для цвета волос:" + "\n" +Color.BLACK + "\n" +
                Color.BROWN + "\n" + Color.WHITE);
        String c = scanner.next();
        System.out.format("Вы ввели значение: %s\n", c);

        return Color.fromStr(c);
    }

    public Status askStatus() {

        System.out.println("Введите одно из возможных значений для статуса:" + "\n" + Status.FIRED + "\n"
        + Status.RECOMMENDED_FOR_PROMOTION + "\n" + Status.PROBATION + "\n" +
        Status.REGULAR + "\n" + Status.HIRED);
        String st = scanner.next();
        System.out.format("Вы ввели значение: %s\n", st);

        return Status.fromStr(st);

    }

    public int askSalary() {

        int salary;
        System.out.println("Введите значение зарплаты");
        salary = scanner.nextInt();
        System.out.format("Вы ввели значение: %s\n", salary);
        if (salary == 0) throw new IllegalArgumentException("Значение поля не может равняться нулю!");
        if (salary < 0) throw new IllegalArgumentException("Значение поля не может быть меньше нуля!");

        return salary;

    }

    public LocalDate askStartDate() {

        LocalDate newStartDate = null;
        try {
            String startDate;

            System.out.println("Введите значение поля StartDate в формате год-месяц-число:");
            startDate = scanner.next();

            newStartDate = LocalDate.parse(startDate);
            System.out.println("Введена дата : " + newStartDate);

        } catch (Exception e) {
            System.out.println(e);
        }
        return newStartDate;
    }

    public Date askEndDate() {

        Date newEndDate = null;

        try {

            String endDate;

            System.out.println("Введите значение поля EndDate в формате год-месяц-число часы:минуты:секунды");
            endDate = scanner.next();

            System.out.println("Введена дата : " + endDate);
            newEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
            System.out.println("Введена дата : " + newEndDate);

        } catch (Exception e) {
            System.out.println(e);
        }
        return newEndDate;


    }


    public long askKey() {
        long key;
        System.out.println("Введите значение ключа для удаления элемента из коллекции:");
        key = scanner.nextLong();
        System.out.format("Вы ввели значение: %s\n", key);
        if (key < 0) throw new IllegalArgumentException("Значение поля не может быть меньше нуля!");

        return key;
    }

    public long askId() {
        long id;
        System.out.println("Введите значение ID:");
        id = scanner.nextLong();
        if (id < 0) throw new IllegalArgumentException("Значение id не может быть меньше нуля!");

        return id;
    }

    public String askFileName() {
        String name = new String();
        try {
            System.out.println("Введите имя файла с расширением .xml:");
            name = scanner.nextLine();
            System.out.format("Вы ввели значение: %s\n", name);
            if (!name.contains(".xml")) {
                throw new IllegalArgumentException("Имя должно содержать расширение .xml!");
            }

        } catch (NoSuchElementException elementException) {
            System.out.println("Имя не распознано!");
        }
        return name;
    }

}

