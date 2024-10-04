package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    void deleteById(Long eventId);
}
