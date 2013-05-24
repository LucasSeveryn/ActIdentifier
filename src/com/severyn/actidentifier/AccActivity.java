package com.severyn.actidentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class AccActivity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5739053446639539286L;
	AccData Data;
	AccData fData;
	AccData lpfData;
	AccData hpfData;
	AccData bpfData;

	int[] crossings;
	float[] sd;
	int type = 9;
	private float[] minMax;
	float spread = 0.30f;
	int rate = 8;
	float alpha = 0.10f;
	float avResAcc;
	float[] peakDistances;
	int[] peakNumber;
	float[] binX;
	float[] binY;
	float[] binZ;
	ArrayList<Integer> peakIndicesX;
	ArrayList<Integer> peakIndicesY;
	ArrayList<Integer> peakIndicesZ;
	private AccData GData;

	public ArrayList<Integer> getPeakIndicesX() {
		return peakIndicesX;
	}

	public ArrayList<Integer> getPeakIndicesY() {
		return peakIndicesY;
	}

	public ArrayList<Integer> getPeakIndicesZ() {
		return peakIndicesZ;
	}

//	private void calculatePeakIndices() {
//		peakIndicesX = FeatureExtractors.peakIndices(lpfData.getxData(),
//				Data.getNoise()[0]);
//		peakIndicesY = FeatureExtractors.peakIndices(lpfData.getyData(),
//				Data.getNoise()[1]);
//		peakIndicesZ = FeatureExtractors.peakIndices(lpfData.getzData(),
//				Data.getNoise()[2]);
//	}

	

	public float getAvResAcceleration() {
		return avResAcc;
	}

	



	public AccActivity(AccData recordedData, AccData recordedGData) {
		Data = recordedData;
		GData = recordedGData;
//		fData = new AccData(FeatureExtractors.iterativeFFT(
//				Data.getDenoisedxData(), 1), FeatureExtractors.iterativeFFT(
//				Data.getDenoisedyData(), 1), FeatureExtractors.iterativeFFT(
//				Data.getDenoisedzData(), 1));
		
//		calculateAvPeakDistances();
//		calculateBinnedDistribution();
	}

	public float[] getSD() {
		return sd;
	}



	


	public int[] getCrossings() {
		return crossings;
	}

	public int getxCrossings() {
		return crossings[0];
	}

	public void setxCrossings(int xCrossings) {
		crossings[0] = xCrossings;
	}

	public int getzCrossings() {
		return crossings[2];
	}

	public void setzCrossings(int zCrossings) {
		crossings[2] = zCrossings;
	}

	public int getyCrossings() {
		return crossings[1];
	}

	public void setyCrossings(int yCrossings) {
		crossings[1] = yCrossings;
	}

	// <string-array name="actTypeArray">
	// <item>Walking (0)</item>
	// <item>Running (1)</item>
	// <item>Walking up the stairs (2)</item>
	// <item>Walking down the stairs (3)</item>
	// <item>Sitting (4)</item>
	// <item>Standing up (5)</item>
	// <item>Jumping (6)</item>
	// <item>Test: Wave Sideways (7)</item>
	// <item>Test: Wave Forward (8)</item>
	public String getType() {
		switch (type) {
		case 0:
			return "Walking (0)";
		case 1:
			return "Running (1)";
		case 2:
			return "Walking up the stairs (2)";
		case 3:
			return "Walking down the stairs (3)";
		case 4:
			return "Sitting (4)";
		case 5:
			return "Standing up (5)";
		case 6:
			return "Jumping (6)";
		case 7:
			return "Test: Wave Sideways (7)";
		case 8:
			return "Test: Wave Forward (8)";
		case 9:
			return "Test: Unidentified (9)";
		default:
			return "Unspecified";

		}

	}

	public void setType(int type) {
		this.type = type;
	}

	public void setMinMax(float[] maximumDisplacements) {
		this.minMax = maximumDisplacements;
	}

	public float[] getMinMax() {
		return minMax;
	}

	public AccData getData() {
		return Data;
	}

	public AccData getfData() {
		return fData;
	}

	public float getSpread() {
		return spread;
	}

	public void setSpread(float spread) {
		this.spread = spread;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public float[] getAvPeakDistance() {
		return peakDistances;
	}

	public int[] getPeakNumber() {
		return peakNumber;
	}

	public AccData getlpfData() {
		return lpfData;
	}

	public AccData getbpfData() {
		return bpfData;
	}

	public AccData gethpfData() {
		return hpfData;
	}

	public String printBinX() {
		return "[" + String.format("%.2f", binX[0]) + ","
				+ String.format("%.2f", binX[1]) + ","
				+ String.format("%.2f", binX[2]) + ","
				+ String.format("%.2f", binX[3]) + ","
				+ String.format("%.2f", binX[4]) + ","
				+ String.format("%.2f", binX[5]) + ","
				+ String.format("%.2f", binX[6]) + ","
				+ String.format("%.2f", binX[7]) + ","
				+ String.format("%.2f", binX[8]) + ","
				+ String.format("%.2f", binX[9]) + "]";
	}

	public AccData getGyroData(){
		return GData;
	}
	
	public String printBinY() {
		return "[" + String.format("%.2f", binY[0]) + ","
				+ String.format("%.2f", binY[1]) + ","
				+ String.format("%.2f", binY[2]) + ","
				+ String.format("%.2f", binY[3]) + ","
				+ String.format("%.2f", binY[4]) + ","
				+ String.format("%.2f", binY[5]) + ","
				+ String.format("%.2f", binY[6]) + ","
				+ String.format("%.2f", binY[7]) + ","
				+ String.format("%.2f", binY[8]) + ","
				+ String.format("%.2f", binY[9]) + "]";
	}

	public String printBinZ() {
		return "[" + String.format("%.2f", binZ[0]) + ","
				+ String.format("%.2f", binZ[1]) + ","
				+ String.format("%.2f", binZ[2]) + ","
				+ String.format("%.2f", binZ[3]) + ","
				+ String.format("%.2f", binZ[4]) + ","
				+ String.format("%.2f", binZ[5]) + ","
				+ String.format("%.2f", binZ[6]) + ","
				+ String.format("%.2f", binZ[7]) + ","
				+ String.format("%.2f", binZ[8]) + ","
				+ String.format("%.2f", binZ[9]) + "]";
	}

}
