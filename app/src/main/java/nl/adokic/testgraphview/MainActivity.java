package nl.adokic.testgraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        double x, y[], y1[];
        x = 0;

        y = new double[] {2, 3, 2, 4, 3, 5};
        y1 = new double[] {1, 2, 2, 3, 3, 4};

        for (int i = 0; i < 6; i++){
            x = x + 1;
            series.appendData(new DataPoint(x, y[i]), true, 10);

            series2.appendData(new DataPoint(x, y1[i]), true, 10);
            series2.setColor(Color.GREEN);

        }

        graphView.addSeries(series);
        graphView.addSeries(series2);



        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        staticLabelsFormatter.setHorizontalLabels(new String[] {"P-6", "P-5", "P-4", "P-3", "P-2", "P-1", "P-0"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

// set manual X bounds
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMaxX(7);

// set manual Y bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0.0);
        graphView.getViewport().setMaxY(9);

        series.setTitle("foo");
        series2.setTitle("bar");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
