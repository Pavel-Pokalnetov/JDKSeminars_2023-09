package seminar4.common;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class RegisterEmployeesTest {
    RegisterEmployees reg = new RegisterEmployees();


    public RegisterEmployeesTest() {
        reg.addEmploee(new Emploee(1, "10111", "Charlie", 7));
        reg.addEmploee(new Emploee(2, "15441", "Sophia", 5));
        reg.addEmploee(new Emploee(3, "14331", "John", 7));
        reg.addEmploee(new Emploee(4, "15441", "Jane", 3));
    };

    @Test
    void testRegisterEmployeesCreate(){
        assertThat(reg).isInstanceOf(RegisterEmployees.class);
        assertThat(reg.size()).isEqualTo(4);
    }


    @Test
    void testFindByTabelNumber(){
        assertThat(reg.findByTabelNumber(3)).isInstanceOf(List.class);
        assertThat(reg.findByTabelNumber(3).size())
                .isEqualTo(1);

        Emploee emploee = reg.findByTabelNumber(3).get(0);

        assertThat(emploee.getTabelNumber()).isEqualTo(3);
        assertThat(emploee.getPhoneNumber()).isEqualTo("14331");
        assertThat(emploee.getName()).isEqualTo("John");
        assertThat(emploee.getExperience()).isEqualTo(7);
    }

    @Test
    void testGetPhoneNumberByName(){

        assertThat(reg.getPhoneNumberByName("Jane")).isInstanceOf(List.class);
        assertThat(reg.getPhoneNumberByName("Jane").size()).isEqualTo(1);
        assertThat(reg.getPhoneNumberByName("Charlie").get(0)).isEqualTo("10111");
    }

    @Test
    void testFingByExperience(){
        assertThat(reg.fingByExperience(100)).isInstanceOf(List.class);
        assertThat(reg.fingByExperience(100).size()).isEqualTo(0);
        assertThat(reg.fingByExperience(7).size()).isEqualTo(2);
        assertThat(reg.fingByExperience(5).size()).isEqualTo(1);
        assertThat(reg.fingByExperience(7).get(0).getName()).isEqualTo("Charlie");
        assertThat(reg.fingByExperience(7).get(1).getName()).isEqualTo("John");

    }



}
