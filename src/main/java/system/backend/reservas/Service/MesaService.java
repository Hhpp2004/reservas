package system.backend.reservas.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import system.backend.reservas.DTO.MesaDTO;
import system.backend.reservas.Model.Mesas;
import system.backend.reservas.Model.Reserva;
import system.backend.reservas.Model.StatusMesa;
import system.backend.reservas.Model.StatusReserva;
import system.backend.reservas.Repository.MesaRepository;
import system.backend.reservas.Repository.ReservaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MesaService {
    private final MesaRepository mr;
    private final ReservaRepository rr;

    //ok
    public long createMesa(MesaDTO newMesa) {
        Mesas novaMesa = newMesa.create();
        return mr.save(novaMesa).getId();
    }

    //ok
    public List<Mesas> lista() {
        return mr.findAll();
    }

    //ok
    public Mesas atualizacao(MesaDTO novosDados,long id) {
        Mesas mesa = mr.findById(id).get();
        if(novosDados.capacidade() != 0)
        {
            mesa.setCapacidade(novosDados.capacidade());
        }
        else if(novosDados.nome() != null)
        {
            mesa.setNome(novosDados.nome());
        }
        return mr.save(mesa);
    }

    //ok
    public boolean delete(long apagarMesas)
    {
        Mesas mesa = mr.findById(apagarMesas).get();
        Reserva reserva = rr.findByMesa(mesa).get();
        reserva.setStatus(StatusReserva.cancelado);
        mesa.setStatus(StatusMesa.inativa);
        rr.save(reserva);
        mr.save(mesa);
        return true;
    }
}
