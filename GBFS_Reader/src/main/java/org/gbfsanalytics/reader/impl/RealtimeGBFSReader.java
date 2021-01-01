package org.gbfsanalytics.reader.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class RealtimeGBFSReader extends AbstractPollingGBFSReader {
    Logger LOGGER = LoggerFactory.getLogger(RealtimeGBFSReader.class);
    private Consumer<JsonArray> callBack;
    private Timer pollTimer;

    public RealtimeGBFSReader(String autoDiscoverURL, Long pollingInterval, String feedFileType) {
        this.autoDiscoverURL = autoDiscoverURL;
        this.pollingInterval = pollingInterval;
        this.feedFileType = feedFileType;
    }

    public RealtimeGBFSReader(String autoDiscoverURL, Long pollingInterval, String feedFileType, Consumer<JsonArray> callBack) {
        this.autoDiscoverURL = autoDiscoverURL;
        this.pollingInterval = pollingInterval;
        this.feedFileType = feedFileType;
        this.callBack = callBack;
    }

    public void setCallBack(Consumer<JsonArray> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void startPolling() throws IllegalStateException {
        if (this.autoDiscoverURL == null || this.pollingInterval == null || this.feedFileType == null ||
                this.callBack == null) {
            throw new IllegalStateException("Required GBFS Information is missing, please check the constructor for " +
                    "required info.");
        }

        if(this.pollTimer!=null){
            LOGGER.info("pollTimer is already started, cannot start again.");
            return;
        }

        pollTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Get the feedFileURL
                // Read from it and pass the result to the callBack
            }
        },0,this.pollingInterval*1000);


    }

    @Override
    public void stopPolling() {
        if(this.pollTimer==null){
            LOGGER.info("pollTimer is not started, call startPolling first before calling this method");
            return;
        }
        this.pollTimer.cancel();
    }

    @Override
    protected void updateFeedFileURL() {

    }
}

