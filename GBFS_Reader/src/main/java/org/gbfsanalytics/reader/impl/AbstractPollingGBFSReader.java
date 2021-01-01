package org.gbfsanalytics.reader.impl;

import com.google.gson.JsonArray;
import org.gbfsanalytics.reader.interfaces.PollingURLReader;

import java.util.function.Consumer;

abstract class AbstractPollingGBFSReader implements PollingURLReader {
    protected String autoDiscoverURL;
    protected Long pollingInterval;
    protected String feedFileType;
    protected String feedFileURL;

    public void setAutoDiscoverURL(String autoDiscoverURL) {
        this.autoDiscoverURL = autoDiscoverURL;
    }

    public void setPollingInterval(Long pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public void setFeedFileType(String feedFileType) {
        this.feedFileType = feedFileType;
    }

    public String getAutoDiscoverURL() {
        return autoDiscoverURL;
    }

    public Long getPollingInterval() {
        return pollingInterval;
    }

    public String getFeedFileType() {
        return feedFileType;
    }


    protected void updateFeedFileURL(){

    }
}
