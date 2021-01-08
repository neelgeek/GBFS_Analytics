package org.gbfsanalytics.reader.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
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
        this.updateFeedFileURL();
    }

    public RealtimeGBFSReader(String autoDiscoverURL, Long pollingInterval, String feedFileType, Consumer<JsonArray> callBack) {
        this.autoDiscoverURL = autoDiscoverURL;
        this.pollingInterval = pollingInterval;
        this.feedFileType = feedFileType;
        this.callBack = callBack;
        this.updateFeedFileURL();
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

        if (this.pollTimer != null) {
            LOGGER.info("pollTimer is already started, cannot start again.");
            return;
        }
        this.pollTimer = new Timer();
        pollTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    URL feedFileULRObj = new URL(feedFileURL);
                    URLConnection feedFileConnection = feedFileULRObj.openConnection();
                    feedFileConnection.connect();
                    InputStream feedFileStream =  feedFileConnection.getInputStream();
                    InputStreamReader feedFileStreamReader = new InputStreamReader(feedFileStream, Charset.forName("UTF-8"));
                    JsonObject feedDataObj = JsonParser.parseReader(feedFileStreamReader).getAsJsonObject();
                    JsonObject data= feedDataObj.getAsJsonObject(constants.DATA_NODE);
                    JsonArray stations = data.getAsJsonArray("stations");
                    callBack.accept(stations);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, this.pollingInterval * 1000);


    }

    @Override
    public void stopPolling() {
        if (this.pollTimer == null) {
            LOGGER.info("pollTimer is not started, call startPolling first before calling this method");
            return;
        }
        this.pollTimer.cancel();
    }

    @Override
    protected void updateFeedFileURL() throws NullPointerException {
        if (this.autoDiscoverURL == null) {
            throw new NullPointerException("Auto discovery URL is null");
        }
        this.feedFileURL = this.autoDiscoverURL + this.feedFileType;
    }


}

