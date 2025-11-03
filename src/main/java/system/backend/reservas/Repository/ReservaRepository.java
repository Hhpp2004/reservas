package system.backend.reservas.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system.backend.reservas.Model.Mesas;
import system.backend.reservas.Model.Reserva;
import system.backend.reservas.Model.User;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUser(User user);

    Optional<Reserva> findByMesa(Mesas mesa);
    
    boolean existsByMesaAndHorario(Mesas mesa, LocalDateTime horario);
}
