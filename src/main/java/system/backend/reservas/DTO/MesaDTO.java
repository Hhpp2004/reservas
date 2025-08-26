package system.backend.reservas.DTO;

import system.backend.reservas.Model.Mesas;
import system.backend.reservas.Model.StatusMesa;
import jakarta.validation.constraints.NotBlank;

public record MesaDTO(@NotBlank String nome,@NotBlank int capacidade,@NotBlank StatusMesa status) {
    public Mesas create()
    {
        return new Mesas(nome, capacidade, status);
    }
}
