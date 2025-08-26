package system.backend.reservas.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginReq(@NotBlank String senha, @NotBlank String email) {
    
}
