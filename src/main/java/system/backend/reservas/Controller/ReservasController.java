package system.backend.reservas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import system.backend.reservas.DTO.ReservaDTO;
import system.backend.reservas.Model.Reserva;
import system.backend.reservas.Model.User;
import system.backend.reservas.Repository.UserRepository;
import system.backend.reservas.Service.ReservasService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ReservasController {
    private final ReservasService rs;
    private final UserRepository ur;

    @GetMapping("/reservas")
    @PreAuthorize("hasAuthority('SCOPE_CLIENTE')")
    public ResponseEntity<List<Reserva>> lista(JwtAuthenticationToken user) 
    {   
        Optional<User> aux = ur.findById(Long.valueOf(user.getName()));
        if(aux.isPresent())
        {
            List<Reserva> lista = rs.lista(aux);
            return ResponseEntity.ok(lista);
        }
        else
        {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/lista-todos")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<Reserva>> lista()
    {
        List<Reserva> lista = rs.listaAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/reserva")
    @PreAuthorize("hasAuthority('SCOPE_CLIENTE')")
    public ResponseEntity<String> create(@RequestBody ReservaDTO novaReserva,JwtAuthenticationToken user) throws Exception
    {
        Optional<User> aux = ur.findById(Long.valueOf(user.getName()));
        long flag = rs.createReserva(novaReserva,aux.get());
        return ResponseEntity.ok("Reserva criada "+flag);
    }

    @PatchMapping("/reserva/{id}/cancelar")
    @PreAuthorize("hasAuthority('SCOPE_CLIENTE')")
    public ResponseEntity<String> cancelar(@PathVariable long id,JwtAuthenticationToken user) throws Exception
    {
        Optional<User> aux = ur.findById(Long.valueOf(user.getName()));
        boolean flag = rs.cancelar(id, aux.get());
        return ResponseEntity.ok("Reserva cancelado: "+flag);
    }
}
