package com.severyn.actidentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AccData implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 4027578149606001414L;
        private ArrayList<Double> xData;
        private ArrayList<Double> yData;
        private ArrayList<Double> zData;
//      private float[] averageNoise;

        public AccData() {
                xData = new ArrayList<Double>();
                yData = new ArrayList<Double>();
                zData = new ArrayList<Double>();
        }
        
        public Double getXMiddleValue(){
                return (Collections.max(xData) + Collections.min(xData))/2;
        }
        
        public Double getYMiddleValue(){
                return (Collections.max(yData) + Collections.min(yData))/2;
        }
        
        public Double getZMiddleValue(){
                return (Collections.max(zData) + Collections.min(zData))/2;
        }
        
        public int size(){
                return xData.size();
        }
        
//      public float[] getNoise() {
//      //      return averageNoise;
//
//      }

        public AccData(ArrayList<Double> xData, ArrayList<Double> yData,
                        ArrayList<Double> zData) {
                this.xData = xData;
                this.yData = yData;
                this.zData = zData;
        }

        public ArrayList<Double> getDenoisedxData() {
                ArrayList<Double> clonexData = (ArrayList<Double>) xData.clone();
                for (int i = 0; i < clonexData.size(); i++) {
        //              clonexData.set(i, clonexData.get(i) - averageNoise[0]);
                }
                return clonexData;
        }

        public ArrayList<Double> getDenoisedyData() {
                ArrayList<Double> clonexData = (ArrayList<Double>) yData.clone();
                for (int i = 0; i < clonexData.size(); i++) {
        //              clonexData.set(i, clonexData.get(i) - averageNoise[1]);
                }
                return clonexData;
        }
        
        public ArrayList<Double> getDenoisedzData() {
                ArrayList<Double> clonexData = (ArrayList<Double>) zData.clone();
                for (int i = 0; i < clonexData.size(); i++) {
        //              clonexData.set(i, clonexData.get(i) - averageNoise[2]);
                }
                return clonexData;
        }
        
        public ArrayList<Double> getxData() {
                return xData;
        }

        public void setxData(ArrayList<Double> xData) {
                this.xData = xData;
        }

        public ArrayList<Double> getyData() {
                return yData;
        }

        public void setyData(ArrayList<Double> yData) {
                this.yData = yData;
        }

        public ArrayList<Double> getzData() {
                return zData;
        }

        public void setzData(ArrayList<Double> zData) {
                this.zData = zData;
        }

        public void clear() {
                xData.clear();
                yData.clear();
                zData.clear();
        }

        public void addX(Double x) {
                xData.add(x);
        }

        public void addZ(Double z) {
                zData.add(z);
        }

        public void addY(Double y) {
                yData.add(y);
        }

//      public void setNoise(float[] averageNoise) {
//              this.averageNoise = averageNoise;
//      }
}
