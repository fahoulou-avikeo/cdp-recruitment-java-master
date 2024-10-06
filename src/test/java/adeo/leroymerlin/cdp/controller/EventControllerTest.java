package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.repository.EventRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindEvents() throws Exception {
        this.mockMvc.perform(get("/api/events/")).andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void testFindEventsWithSearch() throws Exception {
        this.mockMvc.perform(get("/api/events/search/{query}", "Wa"))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(1)))
                            .andExpect(jsonPath("$[0].title", Matchers.equalTo("GrasPop Metal Meeting [5]")))
                            .andDo(print());
    }

    @Test
    void testDeleteEventById() throws Exception {
        this.mockMvc.perform(delete("/api/events/{id}", 1001L))
                                .andExpect(status().isOk());
        assertThat(this.eventRepository.findAll()).hasSize(4);
    }


}
