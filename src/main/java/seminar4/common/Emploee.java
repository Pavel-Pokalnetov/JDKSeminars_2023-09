package seminar4.common;

public class Emploee {
    int tabelNumber;
    String phoneNumber;
    String name;
    int experience;

    public Emploee(int tabel,
                   String phone,
                   String name,
                   int experience) {
        this.tabelNumber = tabel;
        this.phoneNumber = phone;
        this.name = name;
        this.experience = experience;
    }

    public int getTabelNumber() {
        return tabelNumber;
    }

    public void setTabelNumber(int tabelNumber) {
        this.tabelNumber = tabelNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Emploe{" +
                "tabelNumber=" + tabelNumber +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", experience=" + experience +
                '}';
    }
}
