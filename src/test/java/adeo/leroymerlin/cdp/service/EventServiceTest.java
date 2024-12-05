package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;


public class EventServiceTest {

    private Event event;

    @BeforeEach
    public void setUp() throws Exception {
        event = new Event();
        event.setId(1L);
        event.setTitle("Test");
        var band = new Band();
        band.setName("Band");
        var member = new Member();
        member.setName("Member");
        band.setMembers(Set.of(member));
        event.setBands(Set.of(band));
    }
}
