package com.severyn.actidentifier;
public class AccFeat {
	int id;

	Double[] mean = new Double[3];
	Double[] sd = new Double[3];
	Double[] avPeakDistance = new Double[3];
	int[][] histogram = new int[3][10];
	int[][] fftHistogram = new int[3][10];
	int[] crossingCount = new int[3];
	double resultantAcc;
	int type;

	public int getId() {
		return id;
	}

	public double getFeature(int i){
		switch(i){
			case 0: return mean[0];
			case 1: return mean[1];
			case 2: return mean[2];
			case 3: return sd[0];
			case 4: return sd[1];
			case 5: return sd[2];
			case 6: return avPeakDistance[0];
			case 7: return avPeakDistance[1];
			case 8: return avPeakDistance[2];
			case 9: return crossingCount[0];
			case 10: return crossingCount[1];
			case 11: return crossingCount[2];
			case 12: return resultantAcc;	
		}
		if(i>12&&i<=histogram[0].length*3+12){
			//System.out.println("axis: " + (int) Math.floor((i-13)/histogram[0].length) + " index: " + (i-13) % histogram[0].length);
			return histogram[(int) Math.floor((i-13)/histogram[0].length)][(i-13) % histogram[0].length];
		}
		if(i>histogram[0].length*3+12){
			return fftHistogram[(int) Math.floor((i-43)/fftHistogram[0].length)][(i-43) % fftHistogram[0].length];
		}
		return 0;
	}

	public void setCrossingCount(int i, int cc) {
		this.crossingCount[i] = cc;
	}

	public Double getAvPeakDistance(int i) {
		return this.avPeakDistance[i];
	}

	public Double getCrossingCount(int i) {
		return (double) this.crossingCount[i];
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getResultantAcc() {
		return this.resultantAcc;
	}

//	public double getgResultantAcc() {
//		return this.gresultantAcc;
//	}

	public Double getMean(int i) {
		return mean[i];
	}

	public void setMean(int i, Double mean) {
		this.mean[i] = mean;
	}

	public Double getSd(int i) {
		return sd[i];
	}

	public void setSd(int i, Double sd) {
		this.sd[i] = sd;
	}


	public void setResultantAcc(double resultantAcc) {
		this.resultantAcc = resultantAcc;
	}


	public void setAvPeakDistance(int i, double averageDistanceBetweenPeaks) {
		this.avPeakDistance[i] = averageDistanceBetweenPeaks;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public int getFftHistogram(int axis, int index) {
		return fftHistogram[axis][index];
	}

	public void setFftHistogram(int i, int[] fftHistogram) {
		this.fftHistogram[i] = fftHistogram;
	}

	public int getHistogram(int axis, int index) {
//		System.out.println("axis: " + axis + " index: " + index);
		return fftHistogram[axis][index];
	}

	public void setHistogram(int i, int[] histogram) {
		this.histogram[i] = histogram;
	}

}
