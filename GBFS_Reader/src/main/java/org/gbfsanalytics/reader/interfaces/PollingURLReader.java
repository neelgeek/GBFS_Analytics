package org.gbfsanalytics.reader.interfaces;

import java.net.URL;

/**
 * Defines the interface for a class that will poll data from an URL after a defined time duration.
 * This is intended to be used for reading realtime GBFS feeds over HTTP/HTTPS.
 */
public interface PollingURLReader {

    /**
     * Every GBFS Feed has an auto discovery file, which provides links to other GBFS files.
     * This method sets the URL for GBFS auto discovery file.
     *
     * @param autoDiscoveryURL - The URL to the auto discovery file.
     */
    void setAutoDiscoveryURL(URL autoDiscoveryURL);

    /**
     * The reader polls the GBFS file, which is set using setFeedFileType every polling interval.
     * This method sets that polling interval.
     *
     * @param seconds - The polling time interval in seconds
     */
    void setPollingInterval(Long seconds);

    /**
     * A GBFS Feed is a collection of different files, that provide updates on different aspects of a system.
     * Check details here - https://github.com/NABSA/gbfs/blob/master/gbfs.md#files
     * This method is used to set the feed file type for which the reader will poll the data.
     *
     * @param fileName - The name of the file from which the data will be polled.x`
     */
    void setFeedFileType(String fileName);

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
