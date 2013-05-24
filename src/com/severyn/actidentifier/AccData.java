package com.severyn.actidentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AccData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4027578149606001414L;
	private ArrayList<Float> xData;
	private ArrayList<Float> yData;
	private ArrayList<Float> zData;
//	private float[] averageNoise;

	public AccData() {
		xData = new ArrayList<Float>();
		yData = new ArrayList<Float>();
		zData = new ArrayList<Float>();
	}
	
	public float getXMiddleValue(){
		return (Collections.max(xData) + Collections.min(xData))/2;
	}
	
	public float getYMiddleValue(){
		return (Collections.max(yData) + Collections.min(yData))/2;
	}
	
	public float getZMiddleValue(){
		return (Collections.max(zData) + Collections.min(zData))/2;
	}
	
	public int size(){
		return xData.size();
	}
	
//	public float[] getNoise() {
//	//	return averageNoise;
//
//	}

	public AccData(ArrayList<Float> xData, ArrayList<Float> yData,
			ArrayList<Float> zData) {
		this.xData = xData;
		this.yData = yData;
		this.zData = zData;
	}

	public ArrayList<Float> getDenoisedxData() {
		ArrayList<Float> clonexData = (ArrayList<Float>) xData.clone();
		for (int i = 0; i < clonexData.size(); i++) {
	//		clonexData.set(i, clonexData.get(i) - averageNoise[0]);
		}
		return clonexData;
	}

	public ArrayList<Float> getDenoisedyData() {
		ArrayList<Float> clonexData = (ArrayList<Float>) yData.clone();
		for (int i = 0; i < clonexData.size(); i++) {
	//		clonexData.set(i, clonexData.get(i) - averageNoise[1]);
		}
		return clonexData;
	}
	
	public ArrayList<Float> getDenoisedzData() {
		ArrayList<Float> clonexData = (ArrayList<Float>) zData.clone();
		for (int i = 0; i < clonexData.size(); i++) {
	//		clonexData.set(i, clonexData.get(i) - averageNoise[2]);
		}
		return clonexData;
	}
	
	public ArrayList<Float> getxData() {
		return xData;
	}

	public void setxData(ArrayList<Float> xData) {
		this.xData = xData;
	}

	public ArrayList<Float> getyData() {
		return yData;
	}

	public void setyData(ArrayList<Float> yData) {
		this.yData = yData;
	}

	public ArrayList<Float> getzData() {
		return zData;
	}

	public void setzData(ArrayList<Float> zData) {
		this.zData = zData;
	}

	public void clear() {
		xData.clear();
		yData.clear();
		zData.clear();
	}

	public void addX(float x) {
		xData.add(x);
	}

	public void addZ(float z) {
		zData.add(z);
	}

	public void addY(float y) {
		yData.add(y);
	}

//	public void setNoise(float[] averageNoise) {
//		this.averageNoise = averageNoise;
//	}
}
