package com.severyn.actidentifier;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.severyn.actidentifier.R;


public class AccMonitorFragment extends Fragment {
	Context context;
	XYPlot xPlot;
	XYPlot yPlot;
	XYPlot zPlot;

	public void updatePlot(ArrayList<Float> dataHistory, SimpleXYSeries series, XYPlot plot, Float value){
		if (dataHistory.size() < 120) {
			dataHistory.add(value);
		} else {
			dataHistory.remove(0);// remove oldest
			dataHistory.add(value);
			series.setModel(dataHistory, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY);
			plot.redraw();	
		}
	}
	  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.acc_monitor,
				container, false);

		((MainActivity)getActivity()).setTabFragment(1, getTag());
		
		xPlot = (XYPlot) rootView.findViewById(R.id.xAccPlot);
		yPlot = (XYPlot) rootView.findViewById(R.id.yAccPlot);
		zPlot = (XYPlot) rootView.findViewById(R.id.zAccPlot);

		initialisePlot(xPlot, ((MainActivity) getActivity()).getPlotSeries(0));
		initialisePlot(yPlot, ((MainActivity) getActivity()).getPlotSeries(1));
		initialisePlot(zPlot, ((MainActivity) getActivity()).getPlotSeries(2));		
		
		return rootView;
        }
    
    
    void initialisePlot(XYPlot plot, SimpleXYSeries series){
		plot.setRangeBoundaries(-16, 16, BoundaryMode.FIXED);
		plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 2);
		plot.setDomainBoundaries(0, 120, BoundaryMode.FIXED);
		plot.addSeries(series, new LineAndPointFormatter(Color.RED, Color.TRANSPARENT, Color.TRANSPARENT));
		plot.setTicksPerRangeLabel(1);
		plot.setDomainLabel("Time");
		plot.getDomainLabelWidget().pack();
		plot.setRangeLabel("Acceleration");
		plot.getRangeLabelWidget().pack();
		plot.setTitle(series.getTitle());
    }
    
}
