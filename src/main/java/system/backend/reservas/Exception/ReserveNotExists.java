package system.backend.reservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ReserveNotExists extends ReservaException{
    public ProblemDetail tProblemDetail()
    {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setDetail("Reserva não encontrado");
        return pb;
    }
}
