package com.sample.Agora;

import io.agora.rtc.video.VideoEncoderConfiguration;

public class EngineConfig {
    public int mClientRole;
    public VideoEncoderConfiguration.VideoDimensions mVideoDimension;
    public int mUid;
    public String mChannel;

    public EngineConfig() {
    }

    public void reset() {
        mChannel = null;
    }

    public void setmUid(int mUid) {
        this.mUid = mUid;
    }
}

