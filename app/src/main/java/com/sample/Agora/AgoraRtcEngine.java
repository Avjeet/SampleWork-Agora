package com.sample.Agora;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;

import com.sample.MyApplication;
import com.sample.R;

import java.io.File;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

/**
 * Created by Avjeet on 10-05-2019.
 */
public class AgoraRtcEngine {
    private RtcEngine mRtcEngine;
    private String app_id;
    private EngineConfig mEngineConfig;
    private AgoraRtcEventHandler mAgoraRtcEventHandler;
    private Application appContext = MyApplication.getInstance();

    private static AgoraRtcEngine agoraRtcEngine;

    private AgoraRtcEngine() {
        initializeAgoraEngine();
    }

    /**
     * Singleton function proving the instance
     *
     * @return AgoraRtcEngine instance
     */
    public static AgoraRtcEngine getAgoraInstance() {
        if (agoraRtcEngine == null) {
            agoraRtcEngine = new AgoraRtcEngine();
        }
        return agoraRtcEngine;
    }


    private void initializeAgoraEngine() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(appContext);

        this.mEngineConfig = new EngineConfig();
        this.mEngineConfig.mUid = pref.getInt(ConstantApp.PrefManager.PREF_PROPERTY_UID, 0);
        this.mAgoraRtcEventHandler = new AgoraRtcEventHandler();
        ensureRtcEngineReadyLock();
    }

//    /**
//     * @return RtcEngine instance
//     */
//    public RtcEngine getRtcEngine(){
//        return mRtcEngine;
//    }

   // public void setUpEngine

    /**
     * Method to join a channel.
     *
     * @param channelName channel ID that identifies the channel. Users that input the
     *                    same channel ID enter into the same channel.
     *
     * @param uid user ID that identifies the user. Each user in a channel requires a unique uid.
     *            If you want to join the same channel on different devices, ensure that
     *            different uids are used for each device.
     */
    public void joinChannel(String channelName, int uid){
        ensureRtcEngineReadyLock();
        mRtcEngine.joinChannel(null, channelName, null, uid);

        mEngineConfig.mChannel = channelName;
    }


    /**
     * Method to leave the current channel
     */
    public void leaveChannel(){
        if(mRtcEngine!=null){
            mRtcEngine.leaveChannel();
        }

        mEngineConfig.reset();
    }

    private RtcEngine ensureRtcEngineReadyLock() {
        if (mRtcEngine == null) {
            app_id = appContext.getResources().getString(R.string.private_app_id);

            if (TextUtils.isEmpty(app_id)) {
                throw new RuntimeException("Need to use your App ID, get your own ID at https://dashboard.agora.io/");
            }
            try {
                mRtcEngine = RtcEngine.create(appContext, app_id, mAgoraRtcEventHandler);
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), Log.getStackTraceString(e));

                throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
            }

            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);

            mRtcEngine.setLogFile(Environment.getExternalStorageDirectory()
                    + File.separator
                    + appContext.getPackageName()
                    + "/log/agora_rtc.log");

            mRtcEngine.enableDualStreamMode(false);
        }
        return mRtcEngine;
    }


    public final void configEngine(int cRole) {

        if(mEngineConfig.mClientRole!= cRole){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(appContext);
            int prefIndex = pref.getInt(ConstantApp.PrefManager.PREF_PROPERTY_PROFILE_IDX, ConstantApp.DEFAULT_PROFILE_IDX);
            if (prefIndex > ConstantApp.VIDEO_DIMENSIONS.length - 1) {
                prefIndex = ConstantApp.DEFAULT_PROFILE_IDX;
            }
            VideoEncoderConfiguration.VideoDimensions dimension = ConstantApp.VIDEO_DIMENSIONS[prefIndex];

            mEngineConfig.mClientRole = cRole;
            mEngineConfig.mVideoDimension = dimension;

            mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(dimension,
                    VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24,
                    VideoEncoderConfiguration.STANDARD_BITRATE,
                    VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE));

            mRtcEngine.setClientRole(cRole);
        }
    }

    public SurfaceView getLocalPreview(int uid){
            mRtcEngine.enableAudio();
            mRtcEngine.enableVideo();
            SurfaceView surfaceView = RtcEngine.CreateRendererView(appContext);
            mRtcEngine.setupLocalVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid));
            mRtcEngine.startPreview();

            return surfaceView;
    }

    public void stopPreview() {
        mRtcEngine.stopPreview();
    }
}
