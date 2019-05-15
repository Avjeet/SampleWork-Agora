package com.sample.Agora;

/**
 * Created by Avjeet on 10-05-2019.
 *
 * Common interface declaring Agora callback function
 * which can be overrided among different instances which
 * are added in the list.
 */
public interface AgoraEventHandler {
    void onJoinChannelSuccess(String channel, int uid, int elapsed);

    void onUserOffline(int uid, int reason);

    void onUserJoined(int uid, int elapsed);
}
