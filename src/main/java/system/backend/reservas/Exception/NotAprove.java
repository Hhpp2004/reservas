package system.backend.reservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotAprove extends ReservaException {
    public ProblemDetail toProblemDetail()
    {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
        pb.setDetail("Reserva não aprovada");
        return pb;
    }
}
