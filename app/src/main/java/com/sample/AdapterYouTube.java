package com.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterYouTube extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    String[] ids;
    OtherActivity2 otherActivity2;

    public AdapterYouTube(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        otherActivity2 = (OtherActivity2) context;
        ids = context.getResources().getStringArray(R.array.video_id_array);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(layoutInflater.inflate(R.layout.item_youtube, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            final MyHolder myHolder = (MyHolder) holder;
            final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                }

                @Override
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    youTubeThumbnailView.setVisibility(View.VISIBLE);
                }
            };
            myHolder.youTubeThumbnailView.initialize(ids[position], new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(ids[position]);
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
            myHolder.youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(ids[position]);
                  /*  Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, context.getString(R.string.api_key), ids[position]);
                    context.startActivity(intent);*/
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ids.length;
    }

    void showDialog(final String id) {
        /*Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_you_tube);
        YouTubePlayerView youTubePlayerView = dialog.findViewById(R.id.you_tube_player);
        youTubePlayerView.initialize(id, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });*/
//        otherActivity2.loadVideo(id);
//        dialog.show();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        YouTubeThumbnailView youTubeThumbnailView;
        TextView tvTime;

        public MyHolder(View itemView) {
            super(itemView);
            youTubeThumbnailView = itemView.findViewById(R.id.thumbnail);
            tvTime = itemView.findViewById(R.id.video_duration_label);
        }
    }
}
