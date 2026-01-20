package ecom.icet.Repository;

import ecom.icet.Model.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Long, Booking> {
}
