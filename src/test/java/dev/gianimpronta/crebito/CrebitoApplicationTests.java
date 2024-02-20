package dev.gianimpronta.crebito;

import dev.gianimpronta.crebito.controllers.ClienteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CrebitoApplicationTests {
    @Autowired
    ClienteController clienteController;

    @Test
    void contextLoads() {
        assertThat(clienteController).isNotNull();
    }

}
