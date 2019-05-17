package com.sample.Agora.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.sample.Agora.Adapter.SelectMatchAdapter;
import com.sample.Agora.AgoraEventHandler;
import com.sample.Agora.AgoraRtcEngine;
import com.sample.Agora.AgoraRtcEventHandler;
import com.sample.R;

import io.agora.rtc.Constants;

public class LiveBroadcastActivity extends AppCompatActivity implements AgoraEventHandler {

    AgoraRtcEngine rtcEngineInstance;
    AgoraRtcEventHandler agoraRtcEventHandler;
    private ViewStub vs_select_match;
    private ViewStub vs_edit_title;
    private Button continueButton;
    private ImageView switchCamera;
    private ImageView settings;
    private ImageView cancel;
    private boolean isEditTitleUiActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_broadcast_two);
        agoraRtcEventHandler = new AgoraRtcEventHandler();

        rtcEngineInstance = AgoraRtcEngine.getAgoraInstance();
        rtcEngineInstance.configEngine(Constants.CLIENT_ROLE_BROADCASTER);
        agoraRtcEventHandler.addEventHandler(this);


        SurfaceView surfaceV = rtcEngineInstance.getLocalPreview(0);
        ((FrameLayout) findViewById(R.id.layout_broadcast)).addView(surfaceV);

        setUpPreUI();


//        Fragment fragment  = BroadcastFragment.newInstance("p1","p2");
//        getSupportFragmentManager().beginTransaction().add(fragment,"Fragment1").commit();

    }

    private void setUpPreUI() {
        continueButton = findViewById(R.id.btn_continue);

        switchCamera = findViewById(R.id.switch_camera);
        switchCamera.setOnClickListener(v -> rtcEngineInstance.switchCamera());

        settings = findViewById(R.id.settings);

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            if (isEditTitleUiActive) {
                showSelectMatchUI();
            } else {
                finish();
            }
        });
        continueButton.setOnClickListener(v ->
                Toast.makeText(this, "Please select match to continue!!", Toast.LENGTH_LONG).show());

        showSelectMatchUI();
    }

    private void showSelectMatchUI() {
        if (isEditTitleUiActive) {
            isEditTitleUiActive = false;
            vs_edit_title.setVisibility(View.GONE);
            vs_edit_title.invalidate();
            vs_select_match.setVisibility(View.VISIBLE);
            return;
        }

        vs_select_match = findViewById(R.id.viewStub_select);

        View view = vs_select_match.inflate();

        RecyclerView selectMatchRV = view.findViewById(R.id.select_match_rv);
        selectMatchRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        selectMatchRV.addItemDecoration(new SelectMatchAdapter.CustomItemDecorator());
        selectMatchRV.setAdapter(new SelectMatchAdapter(this));
    }

    public void showEditTitleUi() {
        isEditTitleUiActive = true;
        if (vs_edit_title == null) {
            vs_edit_title = findViewById(R.id.viewStub_edit);
        }

        vs_select_match.setVisibility(View.GONE);

        vs_edit_title.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        rtcEngineInstance.stopPreview();
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        Log.d("called", "onJoinChannelSuccess");
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        Log.d("called", "onUserOffline");
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        Log.d("called", "onUserJoined");
    }


}
