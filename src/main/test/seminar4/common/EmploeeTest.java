package seminar4.common;


import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmploeeTest {


    @Test
    void testEmploeeGettersSetters(){
        Emploee e = new Emploee(0, "", "", 0);
        e.setTabelNumber(123);
        e.setName("Иванов Иван Иванович");
        e.setPhoneNumber("+79123456789");
        e.setExperience(12);

        assertEquals(e.getTabelNumber(), 123);
        assertEquals(e.getPhoneNumber(), "+79123456789");
        assertEquals(e.getName(), "Иванов Иван Иванович");
        assertEquals(e.getExperience(), 12);
    }


    @Test
    void testEmploeeConstructor() {
        Emploee e = new Emploee(123, "+79123456789", "Иванов Иван Иванович", 12);
        assertEquals(e.getTabelNumber(), 123);
        assertEquals(e.getPhoneNumber(), "+79123456789");
        assertEquals(e.getName(), "Иванов Иван Иванович");
        assertEquals(e.getExperience(), 12);
    }

    @Test
    void testToString() {
        Emploee e = new Emploee(123, "+79123456789", "Иванов Иван Иванович", 12);
        assertThat(e.toString())
                .isEqualTo("Emploe{таб.№=123, " +
                        "имя='Иванов Иван Иванович', " +
                        "тел.номер='+79123456789', " +
                        "стаж=12}");
    }
}
