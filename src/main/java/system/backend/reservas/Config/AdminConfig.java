package system.backend.reservas.Config;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import system.backend.reservas.Model.Role;
import system.backend.reservas.Model.User;
import system.backend.reservas.Repository.RoleRepository;
import system.backend.reservas.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AdminConfig implements CommandLineRunner {
    private UserRepository ur;
    private RoleRepository rr;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role role = rr.findByNome(Role.Valores.ADMIN.name()).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setNome(Role.Valores.ADMIN.name());
            return rr.save(newRole);
        });
        Role role2 = rr.findByNome(Role.Valores.CLIENTE.name()).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setNome(Role.Valores.CLIENTE.name());
            return rr.save(newRole);
        });
        System.out.println(role2.toString());
        Optional<User> lista = ur.findByNome("Admin");

        lista.ifPresentOrElse(user -> {
            System.out.println("administrador Existe "+user.getNome());
        }, () -> {
            User user = new User();

            user.setNome("Admin");
            user.setEmail("admin@gmail.com");
            user.setSenha(passwordEncoder.encode("123"));
            user.setRole(Set.of(role));
            ur.save(user);
            System.out.println("Admin create "+user.getNome());
        });
    }    
}