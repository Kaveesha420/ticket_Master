package ecom.icet.Controller;

import ecom.icet.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/seats/{id}/hold")
    public ResponseEntity<String> holdSeat(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(seatService.holdSeat(id, userId));
    }
}