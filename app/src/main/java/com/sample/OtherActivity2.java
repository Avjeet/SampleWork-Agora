package com.sample;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class OtherActivity2 extends AppCompatActivity implements OnClickListener {
    int timer = 0;
    ArrayList<String> files;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_other);
        RecyclerView recyclerView = findViewById(R.id.rv_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterNames());
        files = new ArrayList<>();
        handler = new Handler();
    }

    public void takeScreenshotOfResultScreenPeriodically(final String gameId, final Handler handler) {
//        if (startCron) {
        Observable.just(1)
                .delay(2000, TimeUnit.MILLISECONDS)
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        int totalScroll = 10;
                        Log.i("Periodic", timer + " timer");
                        if (timer == 0) {
                            timer++;
                        } else if (timer > 0 && timer <= totalScroll) {
                            try {
                                Process p2 = Runtime.getRuntime().exec("screencap -p " + Environment.getExternalStorageDirectory() + File.separator + "eRooter-admin" + File.separator + "screens"
                                        + File.separator + gameId + "_result_" + timer + ".png \n");
                               /* Process p2 = Runtime.getRuntime().exec("/system/bin/screencap " + Environment.getExternalStorageDirectory() + File.separator + "eRooter-admin" + File.separator + "screens"
                                        + File.separator + gameId + "_result_" + timer + ".png \n");
                                InputStreamReader reader = new InputStreamReader(p2.getInputStream());
                                BufferedReader bufferedReader = new BufferedReader(reader);
                                int numRead;
                                char[] buffer = new char[5000];
                                StringBuffer commandOutput = new StringBuffer();
                                while ((numRead = bufferedReader.read(buffer)) > 0) {
                                    commandOutput.append(buffer, 0, numRead);
                                }*/
                                p2.waitFor();
                                if (timer < totalScroll) {
                                    /*Shell.SU.run("input swipe " + ((int) (1980 / 1.5f)) + " " + 938 + " "
                                            + ((int) (1980 / 1.5f)) + " " + 198 + " 4000");*/
                                    Process p3 = Runtime.getRuntime().exec("input swipe " + ((int) (1980 / 1.5f)) + " " + 940 + " "
                                            + ((int) (1980 / 1.5f)) + " " + 195 + " 4000 \n");
                                    p3.waitFor();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            timer++;
                        }
                        if (timer == totalScroll + 1) {
                            for (int i = 1; i < timer; i++) {
                                files.add(Environment.getExternalStorageDirectory() + File.separator + "eRooter-admin" + File.separator + "screens" + File.separator + gameId + "_result_" + i + ".png");
                            }
                            timer = 0;
                        } else {
                            takeScreenshotOfResultScreenPeriodically(gameId, handler);
                        }
                        return true;
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_red:
                timer = 0;
//                takeScreenshotOfResultScreenPeriodically("10", handler);
        }
    }

    private void addNames(ArrayList<String> names) {
        names.add("Җa2eϬC4vÆbBvÔ");
        names.add("NwM×hsRxQaÔϬh");
        names.add("b367⚔QT9Edmqx");
        names.add("dk༒WϬRǣCLcJDj");
        names.add("KIZU️ydjr§lSM");
        names.add("EkÆOSglfCORO×");
        names.add("eNd£jWW");
        names.add("7U️tELB");
        names.add("l8lcRLÔLo");
        names.add("PIhvopTbd");
        names.add("UCHOuhipn");
        names.add("wYFxWO7");
        names.add("uv4×äBMXp");
        names.add("ϬDarZ彡");
        names.add("4DBBuI");
        names.add("xHV1S★");
        names.add("eAKn0x");
        names.add("FSD£io");
        names.add("अक्षत");
        names.add("मोईन-उड़-डीन");
        names.add("कमला彡नेहरू");
        names.add("मुंशी彡प्रेमचंद");
        names.add("älfJde");
        names.add("K1HObHyÆ");
        names.add("Ui3Җ0NUU");
        names.add("naM0★DW0");
        names.add("axЪЪä4bO");
        names.add("0kEDL⚔Pl");
        names.add("ePϬTbd★u");
        names.add("Nǣog0★ql");
        names.add("iҖknc️Jo");
        names.add("⚔MiDacIz");
        names.add("ML§lSIÆH");
        names.add("GG7sAlRR");
        names.add("YUYzSEJe");
        names.add("h️nPJSa6");
        names.add("n43®jJ0c");
        names.add("2bOvb9YϬ");
        names.add("AÆp40V彡e8m£");
        names.add("wgRysv️1gGA");
        names.add("Pci§hqLze7y");
        names.add("VNlBPA⚔cwEB");
        names.add("Xo2VRWKgUa3");
        names.add("RcXkYEFjr️G");
        names.add("RҖPW£euOcCP");
        names.add("zyҖ×fGÆNFmϬ");
        names.add("hsq5t×3P4Mä");
        names.add("D★£5WwvB️OQ");
        names.add("z®️M0hQlmTl");
        names.add("p16BHe彡iE3r");
        names.add("di8彡y2fyIx★");
        names.add("JhZpWEÆ03qL");
        names.add("r9k️QyVNY9b");
        names.add("Ih42Æg1dWMQ");
        names.add("u£wC®mYU6£6");
        names.add("DVlOQrDRL1Q");
        names.add("Eu6hJ£wNAWP");
        names.add("yv8KDlKT£j9");
        names.add("n彡04ZxM7kpy");
        names.add("lbMMQBcfL️Ϭ");
        names.add("PmgaloWYJP7");
        names.add("9£L02bOr4Æf");
        names.add("mNPÆ®uZrDZs");
        names.add("NRvA×dZirgJ");
        names.add("AR5O×wtNtAO");
        names.add("I7z4jnlNgFh");
        names.add("WY9qz£XWs5o");
        names.add("mXTWfvC7ypt");
        names.add("7JrZMde®Vbk");
        names.add("mkkXXdrdepr");
        names.add("Ry×HbfxcQR6");
        names.add("a0FNoϬVbmnl");
        names.add("LHnnQhxnCVm");
        names.add("jN★GpsI");
        names.add("0f96RX9");
        names.add("VI6U️WHF");
        names.add("3SunrdKW");
        names.add("tҖkvfxEC");
        names.add("p4JIbNp8");
        names.add("aq彡RS6Mi");
        names.add("Edky★Ohb");
        names.add("VP0m4LgR");
        names.add("qBa2lv0f");
        names.add("e️0Ϭ6️⚔8");
        names.add("77彡wfϬI9");
        names.add("Ϭs0Hz7Oi");
        names.add("lTbYcÆV1YR");
        names.add("mIe4Җ7CE");
        names.add("Q★BCO0uOt9");
        names.add("lvcYeK5彡34");
        names.add("彡༒Ds★f3mIe");
        names.add("r8UϬnrPiP3");
        names.add("MzϬ×cXcY彡p");
        names.add("mrnRWm6mI彡");

        /*names.add("lTbYcÆV1YR");
        names.add("ZsÆmDyUcU5");
        names.add("sbh3P1EQLK");
        names.add("0TERVtOwGf");*/
    }

    class AdapterNames extends RecyclerView.Adapter<AdapterNames.MyHolder> {
        LayoutInflater layoutInflater;
        ArrayList<String> names;

        public AdapterNames() {
            names = new ArrayList<>();
            addNames(names);

            layoutInflater = LayoutInflater.from(OtherActivity2.this);
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(layoutInflater.inflate(R.layout.item_result, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tvRank.setText("" + (position + 7));
            holder.tvPlayer.setText(names.get(position));
            holder.tvKills.setText((position % 15) + " kills");
        }

        @Override
        public int getItemCount() {
            return names.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            TextView tvRank, tvPlayer, tvKills;

            public MyHolder(View itemView) {
                super(itemView);
                tvRank = itemView.findViewById(R.id.tv_rank);
                tvPlayer = itemView.findViewById(R.id.tv_player);
                tvKills = itemView.findViewById(R.id.tv_kills);
            }
        }
    }

    /*  public void loadVideo(final String id) {
      youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        if(getSupportFragmentManager().findFragmentByTag("youtube")!=null){
            getSupportFragmentManager().beginTransaction().
            remove((YouTubePlayerSupportFragment)getSupportFragmentManager().findFragmentByTag("youtube")).commitAllowingStateLoss();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, youTubePlayerFragment, "youtube")
                .commitAllowingStateLoss();
        youTubePlayerFragment.initialize(getString(R.string.api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }*/
}

 /* rvYouTube = findViewById(R.id.rv_you_tube);
        rvYouTube.setLayoutManager(new LinearLayoutManager(this));
        rvYouTube.setAdapter(new AdapterYouTube(this));
        youTubeThumbnailView = findViewById(R.id.you_tube_thumb);
        youTubeThumbnailView.initialize(getString(R.string.api_key), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo("YU_Sti_RXqg");
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });*/
/*
        final MultiSlider multiSlider = findViewById(R.id.range_slider5);
        multiSlider.setMin(0);
        multiSlider.setMax(10);
        multiSlider.setStepsThumbsApart(6);
        multiSlider.setDrawThumbsApart(true);

        countDownTimer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long l) {
                if (l % 500000 > 1) {

                }
            }

            @Override
            public void onFinish() {

            }
        };
        multiSlider.setOnTrackingChangeListener(new MultiSlider.OnTrackingChangeListener() {
            @Override
            public void onStartTrackingTouch(MultiSlider multiSlider, MultiSlider.Thumb thumb, int value) {
                Log.i("slider_start", value + "");
                Log.i("start", thumb.getThumbOffset() + "; " + thumb.getMax() + "; " + thumb.getMin() + "; " + multiSlider.getScaleSize());
            }

            @Override
            public void onStopTrackingTouch(MultiSlider multiSlider, MultiSlider.Thumb thumb, int value) {
                Log.i("slider_stop", value + "");
                Log.i("stop", thumb.getThumbOffset() + "; " + thumb.getMax() + "; " + thumb.getMin() + "; " + multiSlider.getScaleSize());
            }
        });
        multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                Log.i("thumb", value + "");
                Log.i("change", thumbIndex + "; " + thumb.getThumbOffset() + "; " + thumb.getMax() + "; " + thumb.getMin() + "; " + value);
            }
        });*/