package com.sample.Agora.Adapter;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.Agora.UI.LiveBroadcastActivity;
import com.sample.R;

import java.util.ArrayList;

/**
 * Created by Your name on 15-05-2019.
 */
public class SelectMatchAdapter extends RecyclerView.Adapter<SelectMatchAdapter.MyViewHolder> {

    private static final int ROW1 = 0;
    private static final int ROW2 = 1;


    private static Integer lastCheckedPos;

    private ArrayList<Boolean> list = new ArrayList<>();

    private Activity activity;
    private LiveBroadcastActivity liveBroadcastActivity;

    public SelectMatchAdapter(Activity activity) {
        this.activity = activity;
        liveBroadcastActivity = (LiveBroadcastActivity) activity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.match_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (lastCheckedPos != null && lastCheckedPos == position) {
            holder.checkTick.setVisibility(View.VISIBLE);
        } else {
            holder.checkTick.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toChange = 0;
                if (lastCheckedPos != null) {
                    toChange = lastCheckedPos;
                }
                lastCheckedPos = holder.getAdapterPosition();
                notifyItemChanged(toChange);

                holder.checkTick.setVisibility(View.VISIBLE);

                liveBroadcastActivity.showEditTitleUi();
            }
        });
    }

//    private void checkItem(int pos, ImageView view) {
//        lastChecked = null;
//    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ROW1;
        } else {
            return ROW2;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView checkTick;
        TextView liveTag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkTick = itemView.findViewById(R.id.check_tick);
            this.liveTag = itemView.findViewById(R.id.live_tag);
        }
    }

    public static class CustomItemDecorator extends RecyclerView.ItemDecoration {
        public CustomItemDecorator() {
        }


        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int row_num = parent.getChildAdapterPosition(view) % 2;

            switch (row_num) {
                case ROW1: {
                    outRect.left = 20;
                    outRect.bottom = 10;
                    outRect.right = 20;
                    break;
                }

                case ROW2: {
                    if (parent.getChildAdapterPosition(view) == 1) {
                        outRect.left = 100;
                    } else {
                        outRect.left = 20;
                    }
                    outRect.right = 20;
                    outRect.top = 40;
                }
            }
        }
    }
}
