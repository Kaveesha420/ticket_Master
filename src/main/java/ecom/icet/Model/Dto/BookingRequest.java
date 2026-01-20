package ecom.icet.Model.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingRequest {
    private Long userId;
    private Long seatId;
}