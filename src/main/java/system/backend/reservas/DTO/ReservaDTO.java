package system.backend.reservas.DTO;

import java.time.LocalDateTime;

import system.backend.reservas.Model.Mesas;
import system.backend.reservas.Model.Reserva;
import system.backend.reservas.Model.StatusReserva;
import jakarta.validation.constraints.NotBlank;

public record ReservaDTO(@NotBlank Mesas mesa, @NotBlank LocalDateTime horario, @NotBlank int capacidade) {
    public Reserva createReserva()
    {
        return new Reserva(mesa, horario, StatusReserva.ativo);
    }
}
