package ecom.icet.Model.Dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {
    private Long id;
    private Long userId;
    private Long seatId;
    private BigDecimal amountPaid;
    private String status;
    private LocalDateTime bookingTime;
}