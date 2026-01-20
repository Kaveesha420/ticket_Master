package ecom.icet.Repository;

import ecom.icet.Model.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Long, Event> {
}
