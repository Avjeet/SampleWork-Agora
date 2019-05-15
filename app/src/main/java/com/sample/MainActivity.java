package com.sample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final int USER_INITIATED = 0x1000;
    public static final int APP_INITIATED = 0x2000;
    public static final int SERVER_INITIATED = 0x3000;
    View markerRed, markerGreen;
    int minValue, maxValue;
    RelativeLayout markerContainer;
    CrystalRangeSeekbar crystalRangeSeekbar;
    int containerWidth = 0;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                }
            }
        });
        querySnapshotTask.addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                }
            }
        });*/
        crystalRangeSeekbar = findViewById(R.id.rangeSeekbar);
       /* docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.getData() != null) {
                    Log.i("Data updated:", documentSnapshot.getData().toString());
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();

                }
            }
        });*/
        markerGreen = findViewById(R.id.marker_green);
        markerRed = findViewById(R.id.marker_red);
       /* graph1 = findViewById(R.id.graph1);
        markerContainer = findViewById(R.id.layout_container_markers);
        graph1.getViewport().setScalable(false);
        graph1.getViewport().setScalableY(false);
        graph1.getViewport().setScrollable(false);
        graph1.getViewport().setScrollableY(false);
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setYAxisBoundsManual(true);
        graph1.getViewport().setMinX(0);
        graph1.getViewport().setMinY(0);
        graph1.getViewport().setMaxX(50);
        graph1.getViewport().setMaxY(10);
        DataPoint[] dataPoints = new DataPoint[]{
                new DataPoint(1, 10),
                new DataPoint(2, 6),
                new DataPoint(3, 5),
                new DataPoint(4, 9),
                new DataPoint(5, 3),
                new DataPoint(6, 4),
                new DataPoint(7, 8),
                new DataPoint(8, 7),
                new DataPoint(9, 3),
                new DataPoint(10, 6),
                new DataPoint(11, 6)
        };
        DataPoint[] wicketPoints = new DataPoint[]{
                new DataPoint(4, 9),
                new DataPoint(5, 3),
                new DataPoint(9, 3)
        };

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(wicketPoints);
        series.setAnimated(false);
        series.setThickness(1);
        series2.setSize(6);

        series2.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(MainActivity.this, "Over " + dataPoint.getX() + " :Run " + dataPoint.getY(), Toast.LENGTH_SHORT).show();
            }
        });
        series2.setShape(PointsGraphSeries.Shape.POINT);
        series.setColor(Color.parseColor("#2283f6"));
        graph1.addSeries(series);
        graph1.addSeries(series2);
        graph1.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph1.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph1.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);*/
        crystalRangeSeekbar.setMinStartValue(6);
        crystalRangeSeekbar.setMaxStartValue(11);
        crystalRangeSeekbar.setMinValue(1);
        crystalRangeSeekbar.setMaxValue(50);
        crystalRangeSeekbar.setGap(1);
        /*crystalRangeSeekbar.init();*/
        minValue = 6;
        maxValue = 11;
        crystalRangeSeekbar.apply();
        crystalRangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                Log.i("minVal; maxVal", minValue + "; " + maxValue);
                Log.i("curMinVal; curMaxVal", MainActivity.this.minValue + "; " + MainActivity.this.maxValue);

                /*if (maxValue.intValue() > 11) {
                    crystalRangeSeekbar.setMaxStartValue(11).setMinStartValue(MainActivity.this.minValue).apply();
                    crystalRangeSeekbar.apply();
                    return;
                }*/
                MainActivity.this.minValue = minValue.intValue();
                MainActivity.this.maxValue = maxValue.intValue();
                calcViewPositions();
            }
        });
        markerContainer.post(new Runnable() {
            @Override
            public void run() {
                containerWidth = markerContainer.getWidth();
                calcViewPositions();
            }
        });
    }

    private void calcViewPositions() {
        float displacementLeft = (float) minValue / (float) 50;
        float displacementRight = (float) maxValue / (float) 50;
        int posXMin = (int) (displacementLeft * containerWidth) - 10;
        int posXMax = (int) (displacementRight * containerWidth) - 10;
        markerRed.setX(posXMin);
        markerGreen.setX(posXMax);
    }
}
