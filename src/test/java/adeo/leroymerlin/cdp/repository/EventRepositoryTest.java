package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testEventDeleteById() {
        var event = entityManager.find(Event.class, 1000L);
        eventRepository.delete(event);
        assertThat(entityManager.find(Event.class, 1000L)).isNull();
    }
}
