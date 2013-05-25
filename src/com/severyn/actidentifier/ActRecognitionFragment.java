package com.severyn.actidentifier;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.severyn.actidentifier.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActRecognitionFragment extends Fragment {
	XYPlot xyzPlot;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.act_recognition, container,
				false);

		((MainActivity) getActivity()).setTabFragment(3, getTag());

		xyzPlot = (XYPlot) rootView.findViewById(R.id.xyzPlot);
		xyzPlot.setRangeBoundaries(-15, 15, BoundaryMode.FIXED);
		xyzPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 2);
		xyzPlot.setDomainBoundaries(0, 512, BoundaryMode.FIXED);
		xyzPlot.setTicksPerRangeLabel(1);
		xyzPlot.setDomainLabel("Time");
		xyzPlot.getDomainLabelWidget().pack();
		xyzPlot.setRangeLabel("Acceleration");
		xyzPlot.getRangeLabelWidget().pack();
		xyzPlot.setTitle("Recording");

		xyzPlot.redraw();

		return rootView;
	}

	public void updateStatusText(String text, boolean append) {
		TextView statusText = (TextView) this.getView().findViewById(
				R.id.statusText);
		if(append){
			statusText.setText(statusText.getText()+ " " + text);
		}
		statusText.setText(text);

	}
	
	public void updateStatusText2(String text, boolean append) {
		TextView statusText = (TextView) this.getView().findViewById(
				R.id.statusText2);
		if(append){
//			String existing = 
			statusText.setText(statusText.getText().toString() + " " + text);
		}else{
			statusText.setText(text);
		}
		

	}

}
