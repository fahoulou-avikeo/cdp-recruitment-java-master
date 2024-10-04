package adeo.leroymerlin.cdp.helper;

import adeo.leroymerlin.cdp.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventFormattingHelper {

    private final BandFormattingHelper bandFormattingHelper;

    public EventFormattingHelper(BandFormattingHelper bandFormattingHelper) {
        this.bandFormattingHelper = bandFormattingHelper;
    }

    public void formatEvent(Event event) {
        String newTitle = event.getTitle() +
                " [" +
                event.getBands().size() +
                "]";
        event.setTitle(newTitle);
        event.getBands().forEach(bandFormattingHelper::formatBand);
    }

}
