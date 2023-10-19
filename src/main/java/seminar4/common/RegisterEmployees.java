package seminar4.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterEmployees {
    List<Emploee> register;

    public RegisterEmployees() {
        this.register = new ArrayList<>(10);
    }

    /** Добавление сторудника в справочник
     * @param e класс Emploee
     */
    public void addEmploee(Emploee e) {
        register.add(e);
    }

    /** Поиск по стажу
     * @param experiece
     * @return
     */
    public List<Emploee> fingByExperience(int experiece) {
        return  register.stream()
                .filter(emploee -> emploee.getExperience()==experiece)
                .toList();
    }

    /** Поиск номера телефона по имени
     * @param name имя сотрудника
     * @return
     */
    public List<String> getPhoneNumberByName(String name) {
        List<String> result = new ArrayList<>();
        register.forEach(emploee -> {
            if (emploee.getName().equals(name)) {
                result.add(emploee.getPhoneNumber());
            }
        });
        return result;
    }

    /** Поиск по табельному номеру
     * @param tabel табельный номер
     * @return список сотрудников
     */
    public List<Emploee> findByTabelNumber(int tabel) {
        return register.stream()
                .filter(emploee -> emploee.tabelNumber == tabel)
                .toList();


    }
}
