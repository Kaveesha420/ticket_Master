package ecom.icet.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldByUserId;
    private LocalDateTime holdExpiry;
}

