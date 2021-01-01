package org.gbfsanalytics.reader.interfaces;

import java.net.URL;

/**
 * Defines the interface for a class that will poll data from an URL after a defined time duration.
 * This is intended to be used for reading realtime GBFS feeds over HTTP/HTTPS.
 */
public interface PollingURLReader {

    /**
     * Starts the GBFS reader polling;
     *
     * @throws IllegalStateException - If the polling interval, auto discovery url or feed file type is not set.
     */
    void startPolling() throws IllegalStateException;

    /**
     * Stops the GBFS reader polling;
     */
    void stopPolling();

}
