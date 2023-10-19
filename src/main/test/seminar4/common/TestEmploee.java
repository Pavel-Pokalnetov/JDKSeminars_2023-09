package seminar4.common;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestEmploee {
    @Test
    void testEmploee(){
        Emploee e = new Emploee(123,"+79123456789","Иванов Иван Иванович",12);
        assertThat(e.getName());
    }

}
