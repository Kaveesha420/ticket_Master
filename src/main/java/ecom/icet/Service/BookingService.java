package ecom.icet.Service;

import ecom.icet.Model.Dto.*;
import ecom.icet.Model.Entity.*;
import ecom.icet.Repository.*;
import ecom.icet.util.EntityMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    private EntityMapper mapper;

    @Transactional
    public BookingDto createBooking(Long userId, Long seatId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() != SeatStatus.HELD) {
            throw new RuntimeException("Seat must be HELD before booking");
        }

        if (!seat.getHeldByUserId().equals(userId)) {
            throw new RuntimeException("This seat is held by another user");
        }

        if (seat.getHoldExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Hold expired! Please hold again.");
        }

        Event event = seat.getEvent();
        var finalPrice = priceCalculatorService.calculate(user, event);
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(finalPrice);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingTime(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        seat.setStatus(SeatStatus.SOLD);
        seat.setHeldByUserId(null);
        seat.setHoldExpiry(null);
        seatRepository.save(seat);

        return mapper.toBookingDTO(savedBooking);
    }

    public List<BookingDto> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        return bookings.stream()
                .map(mapper::toBookingDTO)
                .collect(Collectors.toList());
    }
}