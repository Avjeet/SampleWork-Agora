package com.sample.Agora.Adapter;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.Agora.UI.LiveBroadcastActivity;
import com.sample.R;

/**
 * Created by Your name on 15-05-2019.
 */
public class SelectMatchAdapter extends RecyclerView.Adapter<SelectMatchAdapter.MyViewHolder> {

    private static final int ROW1 = 0;
    private static final int ROW2 = 1;
    private Activity activity;
    private boolean first = true;

    public SelectMatchAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.match_item, parent, false);

//        if (viewType == ROW2) {
//            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            if (first) {
//                first = false;
//                buttonLayoutParams.setMargins(100, 40, 10, 10);
//            }else{
//                buttonLayoutParams.setMargins(10, 40, 10, 10);
//            }
//
//            view.setLayoutParams(buttonLayoutParams);
//        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LiveBroadcastActivity) activity).setUpEditTitleUi();
            }
        });
    }

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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
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
