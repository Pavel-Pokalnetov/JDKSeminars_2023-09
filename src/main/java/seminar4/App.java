package seminar4;

import seminar4.common.Emploee;
import seminar4.common.RegisterEmployees;

public class App {
    public static void main(String[] args) {
        RegisterEmployees register = new RegisterEmployees(5);
        setRegister(register);//добавляем сотрудников

        System.out.println("Число сотрудников: " + register.size());
        System.out.println("\n============= Поиск телефона по имени ============");
        printFindPhoneByName("Алексей Петров", register);
        printFindPhoneByName("Антон Иванов", register);

        System.out.println("\n============= Поиск по табельному номеру==========");
        printFindByTabelNumber(10, register);
        printFindByTabelNumber(12, register);

        System.out.println("\n============= Поиск по стажу =====================");
        prinFindByExperience(5, register);
        prinFindByExperience(2, register);
    }

    private static void prinFindByExperience(int exp, RegisterEmployees reg) {
        System.out.println("Сотрудники со стажем " + exp);
        for (Emploee emploee : reg.fingByExperience(exp)) {
            System.out.println("\t"+emploee);
        }
        System.out.println();
    }

    private static void printFindByTabelNumber(int tabel, RegisterEmployees reg) {
        System.out.println("Табельный номер: " + tabel);
        reg.findByTabelNumber(tabel).forEach(e -> {
            System.out.println("\t"+e);
        });
        System.out.println();
    }

    private static void printFindPhoneByName(String name, RegisterEmployees reg) {
        System.out.println("Имя: " + name + "\nНайденные телефоны:");
        reg.getPhoneNumberByName(name).forEach(e -> {
            System.out.println("\t" + e);
        });
        System.out.println();
    }

    private static void setRegister(RegisterEmployees register) {
        register.addEmploee(new Emploee(1, "+7 (123) 456-7890", "Иван Иванов", 5));
        register.addEmploee(new Emploee(2, "+7 (234) 567-8901", "Алексей Петров", 10));
        register.addEmploee(new Emploee(3, "+7 (345) 678-9012", "Елена Смирнова", 3));
        register.addEmploee(new Emploee(4, "+7 (456) 789-0123", "Анна Ковалева", 8));
        register.addEmploee(new Emploee(5, "+7 (567) 890-1234", "Дмитрий Николаев", 15));
        register.addEmploee(new Emploee(6, "+7 (678) 901-2345", "Ольга Соколова", 2));
        register.addEmploee(new Emploee(7, "+7 (789) 012-3456", "Алексей Петров", 2));
        register.addEmploee(new Emploee(8, "+7 (890) 123-4567", "Екатерина Петрова", 12));
        register.addEmploee(new Emploee(9, "+7 (901) 234-5678", "Александра Смирнова", 4));
        register.addEmploee(new Emploee(10, "+7 (012) 345-6789", "Игорь Ковалев", 9));
        register.addEmploee(new Emploee(11, "+7 (123) 456-7890", "Наталья Николаева", 16));
        register.addEmploee(new Emploee(12, "+7 (234) 567-8901", "Сергей Соколов", 2));
        register.addEmploee(new Emploee(13, "+7 (345) 678-9012", "Антон Иванов", 6));
        register.addEmploee(new Emploee(14, "+7 (456) 789-0123", "Людмила Петрова", 1));
        register.addEmploee(new Emploee(15, "+7 (567) 890-1234", "Павел Смирнов", 3));
    }
}
