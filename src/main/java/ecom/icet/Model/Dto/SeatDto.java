package ecom.icet.Model.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class SeatDto {
    private Long id;
    private String seatNumber;
    private String status;
    private Long eventId;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
}