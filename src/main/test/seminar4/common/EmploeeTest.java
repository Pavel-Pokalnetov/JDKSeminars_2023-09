package seminar4.common;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmploeeTest {
    @Test
    void testEmploee(){
        Emploee e = new Emploee(123,"+79123456789","Иванов Иван Иванович",12);
        assertEquals(e.getTabelNumber(),123);
        assertEquals(e.getPhoneNumber(),"+79123456789");
        assertEquals(e.getName(),"Иванов Иван Иванович");
        assertEquals(e.getExperience(),12);
    }
}
