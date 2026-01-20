package ecom.icet.Service;

import ecom.icet.aspect.AuditFailure;
import ecom.icet.Model.Entity.Seat;
import ecom.icet.Model.Entity.SeatStatus;
import ecom.icet.Repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    @AuditFailure
    public String holdSeat(Long seatId, Long userId) {
            Seat seat = seatRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.SOLD) {
            throw new RuntimeException("Seat is already SOLD");
        }

        LocalDateTime now = LocalDateTime.now();

        if (seat.getStatus() == SeatStatus.HELD) {
            if (seat.getHoldExpiry().isAfter(now)) {
                long seconds = Duration.between(now, seat.getHoldExpiry()).getSeconds();
                throw new RuntimeException("SeatLockedException: Wait " + seconds + "s");
            }
        }

        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(10));

        seatRepository.save(seat);
        return "Seat HELD successfully";
    }
}