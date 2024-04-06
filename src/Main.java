import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = readUser();
        printUser(user);
    }

    public static User readUser() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.print("Фамилия: ");
        user.setSurname(scanner.nextLine());
        System.out.print("Имя: ");
        user.setName(scanner.nextLine());
        System.out.print("Отчество: ");
        user.setPatronymic(scanner.nextLine());
        System.out.print("Дата рождения (дд-мм-гггг): ");
        user.setBirthday(extractDate(scanner.nextLine()));
        return user;
    }

    public static LocalDate extractDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public static void printUser(User user) {
        String userFullName = "ФИО: " + user.getSurname() + " " + extractInitials(user) + "\n";
        String gender = "Пол: " + extractGender(user) + "\n";
        String fullYears = "Возраст: " + extractFullYears(user);
        String userInfo = userFullName + gender + fullYears;
        System.out.println(userInfo);
    }

    public static String extractInitials(User user) {
        char firstCapitalNameLetter = Character.toTitleCase(user.getName().charAt(0));
        char firstCapitalPatronymicLetter = Character.toTitleCase(user.getPatronymic().charAt(0));
        return firstCapitalNameLetter + ". " + firstCapitalPatronymicLetter + ".";
    }

    public static String extractGender(User user) {
        String patronymic = user.getPatronymic();
        if (patronymic.endsWith("вна")) {
            return "женский";
        } else {
            return "мужской";
        }
    }

    public static long extractFullYears(User user) {
        LocalDate birthday = user.getBirthday();
        LocalDate now = LocalDate.now();
        return ChronoUnit.YEARS.between(birthday, now);
    }
}