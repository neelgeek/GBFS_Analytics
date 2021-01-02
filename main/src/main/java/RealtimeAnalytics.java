import com.google.gson.JsonArray;
import org.gbfsanalytics.reader.impl.RealtimeGBFSReader;
import org.gbfsanalytics.reader.impl.constants;

import java.util.function.Consumer;

public class RealtimeAnalytics {
    public static void main(String[] args) {
        RealtimeGBFSReader realtimeGBFSReader = new RealtimeGBFSReader(
                "https://gbfs.bluebikes.com/gbfs/en/", 5L, constants.STATION_STATUS,
                new Consumer<JsonArray>() {
                    @Override
                        public void accept(JsonArray jsonObject) {
                        System.out.println(jsonObject.toString());
                    }
                }
        );

        realtimeGBFSReader.startPolling();
    }
}
