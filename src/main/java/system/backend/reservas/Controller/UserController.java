package system.backend.reservas.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.backend.reservas.DTO.CreateUser;
import system.backend.reservas.DTO.LoginReq;
import system.backend.reservas.DTO.LoginRes;
import system.backend.reservas.Service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UserController {
    private final UserService us;

    @PostMapping("/cadastro")
    public ResponseEntity<String> createUser(@RequestBody CreateUser newUser) throws Exception
    {
        Long id = us.createUser(newUser);
        return ResponseEntity.ok("User "+id+" create");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq login) throws Exception
    {
        LoginRes user = us.login(login);
        return ResponseEntity.ok(user);
    }
}
