package com.sample.Agora;

import com.sample.models.AGEventHandler;

import java.util.concurrent.ConcurrentHashMap;

import io.agora.rtc.IRtcEngineEventHandler;

/**
 * Created by Avjeet on 10-05-2019.
 */
public class AgoraRtcEventHandler extends IRtcEngineEventHandler {

    public AgoraRtcEventHandler() {

    }

    private final ConcurrentHashMap<AgoraEventHandler, Integer> mEventHandlerList = new ConcurrentHashMap<>();

    /**
     *  @// TODO: 13-05-2019 Call this method in the activity before establishing connection
     *
     * Method to adds the handler instance to the HashMap before establishing the channel.
     * When a overridden method is called in AgoraEventHandler interface it will be sent
     * to all the method of all the handler in the list.
     *
     * @param handler instance of AgoraEventHandler implemented in an Activity
     */
    public void addEventHandler(AgoraEventHandler handler) {
        this.mEventHandlerList.put(handler, 0);
    }

    /**
     * Method to remove the handler from teh list when the channel is closed.
     * @param handler instace of AgoraEventHandler to be removed
     */
    public void removeEventHandler(AGEventHandler handler) {
        this.mEventHandlerList.remove(handler);
    }



    @Override
    public void onUserJoined(int uid, int elapsed) {
        super.onUserJoined(uid, elapsed);

        for (AgoraEventHandler handler : mEventHandlerList.keySet()) {
            handler.onUserJoined(uid, elapsed);
        }
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        super.onJoinChannelSuccess(channel, uid, elapsed);

        for (AgoraEventHandler handler : mEventHandlerList.keySet()) {
            handler.onJoinChannelSuccess(channel, uid, elapsed);
        }
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        super.onUserOffline(uid, reason);

        for (AgoraEventHandler handler : mEventHandlerList.keySet()) {
            handler.onUserOffline(uid, reason);
        }
    }
}
