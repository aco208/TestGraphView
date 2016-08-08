package nl.adokic.testgraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
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
        series.setDataPointsRadius(5);

        series2 = new LineGraphSeries<DataPoint>();
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(5);

        double x, y[], y1[];
        x = 0;

        y = new double[] {2, 3, 2, 4, 3};
        y1 = new double[] {0, 2, 2, 3, 3};

        for (int i = 0; i < 5; i++){
            x = x + 1;
            series.appendData(new DataPoint(x, y[i]), true, 8);

            series2.appendData(new DataPoint(x, y1[i]), true, 8);
            series2.setColor(Color.GREEN);
        }

        graphView.addSeries(series);
        graphView.addSeries(series2);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return "Period "+super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
