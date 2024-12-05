package adeo.leroymerlin.cdp.helper;

import adeo.leroymerlin.cdp.entity.Band;
import org.springframework.stereotype.Component;

@Component
public class BandFormattingHelper {

    public void formatBand(Band band) {
        String newBandName = band.getName() +
                " [" +
                band.getMembers().size() +
                "]";
        band.setName(newBandName);
    }
}
