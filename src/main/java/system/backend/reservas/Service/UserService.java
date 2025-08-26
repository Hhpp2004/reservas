package system.backend.reservas.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import system.backend.reservas.DTO.CreateUser;
import system.backend.reservas.DTO.LoginReq;
import system.backend.reservas.DTO.LoginRes;
import system.backend.reservas.Exception.BadCrendential;
import system.backend.reservas.Exception.EmailJaEmUso;
import system.backend.reservas.Exception.RunTimeError;
import system.backend.reservas.Model.Role;
import system.backend.reservas.Model.User;
import system.backend.reservas.Repository.RoleRepository;
import system.backend.reservas.Repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService  {

    private final PasswordEncoder encoder;
    private final UserRepository ur;
    private final RoleRepository rr;
    private final JwtEncoder jwtEncoder;

    //ok
    public Long createUser(CreateUser newUser) throws Exception {
        Role userRole = rr.findByNome(Role.Valores.CLIENTE.name())
                .orElseThrow(() -> new RunTimeError());
        long id = 0l;
        if (!ur.existsByEmail(newUser.email())) {
            User user = newUser.createUserClient(encoder, userRole);
            user.setRole(Set.of(userRole));
            id = ur.save(user).getId();
            return id;
        }
        else
        {
            throw new EmailJaEmUso();
        }
    }

    //ok
    public LoginRes login(LoginReq user) throws Exception {
        if (ur.existsByEmail(user.email())) {
            Optional<User> lista = ur.findByEmail(user.email());
            if (lista.isPresent() && lista.get().isLoginCorrect(user, encoder)) {
                Instant now = Instant.now();
                Long tempo = 3600L;
                var scope = lista.get().getRole().stream().map(Role::getNome).collect(Collectors.joining(""));
                var claims = JwtClaimsSet.builder()
                        .issuer("Security")
                        .subject(lista.get().getId().toString())
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(tempo))
                        .claim("scope", scope)
                        .build();
                var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
                return new LoginRes(jwtValue, tempo);
            } else {
                throw new BadCrendential();
            }
        } else {
            throw new BadCrendential();
        }
    }
}