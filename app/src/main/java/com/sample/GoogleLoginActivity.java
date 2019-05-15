package com.sample;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sample.Agora.ConstantApp;
import com.sample.Agora.UI.LiveBroadcastActivity;
import com.sample.ui.LiveRoomActivity;

import io.agora.rtc.Constants;

import static com.sample.Agora.ConstantApp.ACTION_KEY_CROLE;

public class GoogleLoginActivity extends AppCompatActivity implements OnClickListener {


    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        aSwitch = findViewById(R.id.switch1);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: {
                MyApplication.getInstance().initWorkerThread();
                Intent intent = new Intent(this, LiveRoomActivity.class);
                intent.putExtra(ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE);
                intent.putExtra(ConstantApp.ACTION_KEY_ROOM_NAME, getString(R.string.channel_name));
                intent.putExtra(ConstantApp.ACTION_KEY_BROADCAST_TYPE, aSwitch.isChecked()?"audio":"video");
                startActivity(intent);
            }
            break;

            case R.id.button2: {
                MyApplication.getInstance().initWorkerThread();
                Intent intent = new Intent(this, LiveBroadcastActivity.class);
                intent.putExtra(ACTION_KEY_CROLE, Constants.CLIENT_ROLE_BROADCASTER);
                intent.putExtra(ConstantApp.ACTION_KEY_BROADCAST_TYPE, aSwitch.isChecked()?"audio":"video");
                startActivity(intent);
            }
            break;
        }
    }
}
