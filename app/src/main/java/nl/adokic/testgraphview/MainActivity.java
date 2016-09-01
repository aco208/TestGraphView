package nl.adokic.testgraphview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series, series2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graphView = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        series.setDrawDataPoints(true);
        series.setThickness(15);
        series.setDataPointsRadius(10);

        series2 = new LineGraphSeries<DataPoint>();
        series2.setDrawDataPoints(true);
        series2.setThickness(15);
        series2.setDataPointsRadius(10);

        textView = (TextView) findViewById(R.id.textView);


        double x, y[], y1[];
        x = 0;

        y = new double[] {5, 3, 4, 2, 3, 2};
        y1 = new double[] {4, 3, 3, 2, 2, 1};

        for (int i = 0; i < 6; i++){
            x = x + 1;
            series.appendData(new DataPoint(x, y[i]), true, 10);

            series2.appendData(new DataPoint(x, y1[i]), true, 10);
            series2.setColor(Color.GREEN);

        }

        graphView.addSeries(series2);
        graphView.addSeries(series);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                textView.setText("Clicked: "+dataPoint);
            }
        });

        graphView.getViewport().setScrollable(true);



        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        staticLabelsFormatter.setHorizontalLabels(new String[] {"P-5", "P-4", "P-3", "P-3", "P-2", "P-0"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

// set manual X bounds
        graphView.getViewport().setXAxisBoundsManual(true);
//        graphView.getViewport().setMaxX(7);
//        graphView.getViewport().setMinX(0);

// set manual Y bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(9);

        series2.setTitle("Required");
        series.setTitle("Scored");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.getLegendRenderer().setTextColor(Color.WHITE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}
