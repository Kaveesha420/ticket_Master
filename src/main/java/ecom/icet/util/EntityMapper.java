package ecom.icet.util;

import ecom.icet.Model.Dto.*;
import ecom.icet.Model.Entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EntityMapper {

    public UserDto toUserDTO(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .tier(user.getTier().toString())
                .build();
    }

    public EventDto toEventDTO(Event event) {
        if (event == null) return null;
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .basePrice(BigDecimal.valueOf(event.getBasePrice()))
                .isHighDemand(event.isHighDemand())
                .eventDate(event.getEventDate())
                .build();
    }

    public SeatDto toSeatDTO(Seat seat) {
        if (seat == null) return null;
        return SeatDto.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .status(seat.getStatus().toString())
                .eventId(seat.getEvent() != null ? seat.getEvent().getId() : null)
                .heldByUserId(seat.getHeldByUserId())
                .holdExpiry(seat.getHoldExpiry())
                .build();
    }

    public BookingDto toBookingDTO(Booking booking) {
        if (booking == null) return null;
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUser() != null ? booking.getUser().getId() : null)
                .seatId(booking.getSeat() != null ? booking.getSeat().getId() : null)
                .amountPaid(booking.getAmountPaid())
                .status(booking.getStatus().toString())
                .bookingTime(booking.getBookingTime())
                .build();
    }

    public AuditLogDto toAuditLogDTO(AuditLog log) {
        if (log == null) return null;
        return AuditLogDto.builder()
                .id(log.getId())
                .action(log.getAction())
                .userId(log.getUserId())
                .details(log.getDetails())
                .timestamp(log.getTimestamp())
                .build();
    }
}