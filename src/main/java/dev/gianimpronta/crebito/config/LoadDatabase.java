package dev.gianimpronta.crebito.config;

import dev.gianimpronta.crebito.entities.Cliente;
import dev.gianimpronta.crebito.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(ClienteRepository repository) {
        /*
        id	limite	saldo inicial
1	100000	0
2	80000	0
3	1000000	0
4	10000000	0
5	500000	0
         */
        return args -> {
            log.info("Populando DB inicial");
            log.info("Carregando " + repository.save(new Cliente(1L, 100000L, 0L)));
            log.info("Carregando " + repository.save(new Cliente(2L, 80000L, 0L)));
            log.info("Carregando " + repository.save(new Cliente(3L, 1000000L, 0L)));
            log.info("Carregando " + repository.save(new Cliente(4L, 10000000L, 0L)));
            log.info("Carregando " + repository.save(new Cliente(5L, 500000L, 0L)));
        };
    }

}
