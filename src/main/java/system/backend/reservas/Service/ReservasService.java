package system.backend.reservas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import system.backend.reservas.DTO.ReservaDTO;
import system.backend.reservas.Exception.Cancelamento;
import system.backend.reservas.Exception.Capacidade;
import system.backend.reservas.Exception.Inativo;
import system.backend.reservas.Exception.NotFoundTable;
import system.backend.reservas.Exception.Reservado;
import system.backend.reservas.Model.Mesas;
import system.backend.reservas.Model.Reserva;
import system.backend.reservas.Model.StatusMesa;
import system.backend.reservas.Model.StatusReserva;
import system.backend.reservas.Model.User;
import system.backend.reservas.Repository.MesaRepository;
import system.backend.reservas.Repository.ReservaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservasService {
    private final ReservaRepository rr;
    private final MesaRepository mr;

    // ok
    public List<Reserva> lista(Optional<User> aux) {
        List<Reserva> lista = rr.findByUser(aux.get());
        return lista;
    }

    // ok
    public List<Reserva> listaAll()
    {
        if (!rr.findAll().isEmpty()) {
            return rr.findAll();
        } else {
            return null;
        }
    }

    // ok
    public long createReserva(ReservaDTO reserva, User user) throws Exception {
        Mesas mesa = mr.findByNome(reserva.mesa().getNome())
                .orElseThrow(() -> new NotFoundTable());
        if (!mesa.getStatus().equals(StatusMesa.inativa)) {
            if (rr.existsByMesaAndHorario(mesa, reserva.horario())) {
                throw new Reservado();
            }

            if (mesa.getCapacidade() <= reserva.capacidade()) {
                throw new Capacidade();
            }
            Reserva reservado = reserva.createReserva();
            reservado.setUser(user);
            mesa.setStatus(StatusMesa.reservada);
            reservado.setMesa(mesa);
            reservado.setStatus(StatusReserva.ativo);
            return rr.save(reservado).getId();
        }
        else
        {
            throw new Inativo();
        }

    }

    // ok
    public boolean cancelar(long id, User user) throws Exception {
        Reserva reserva = rr.findById(id).get();
        if (reserva.getUser().getId() == user.getId()) {
            reserva.setStatus(StatusReserva.cancelado);
            Mesas mesa = reserva.getMesa();
            mesa.setStatus(StatusMesa.disponivel);
            mr.save(mesa);
            rr.save(reserva);
            return true;
        } else {
            throw new Cancelamento();
        }
    }
}
