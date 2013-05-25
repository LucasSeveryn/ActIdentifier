package com.severyn.actidentifier;
import java.util.ArrayList;

import android.util.Pair;


public class NaiveGaussianClassifier {
	ArrayList<ArrayList<Double>> entropyMean;
	ArrayList<ArrayList<Double>> entropyVar;

	

	public NaiveGaussianClassifier(ArrayList<ArrayList<Double>> entropyMean, ArrayList<ArrayList<Double>> entropyVar) {
		this.entropyMean = entropyMean;
		this.entropyVar = entropyVar;
        
	}

	public Pair<Integer,String> classify(AccFeat q) {
		String txt="";
		txt+=("\n- Starting NBC Classification");
		double[] results = new double[9];
		double result;
		ArrayList<Double> qf = new ArrayList<Double>();

		for (int j = 0; j < 73; j++) {
			qf.add(q.getFeature(j));
		}

		for (int i = 0; i < 9; i++) {
			if (i != 1 && i != 4 && i != 5 && i != 6) { // debug
				result = 0;
				for (int j = 0; j < entropyMean.get(i).size(); j++) {
//					result = result * p(qf.get(j), entropyMean.get(i).get(j),entropyVar.get(i).get(j));
					result += Math.log1p(p(qf.get(j), entropyMean.get(i).get(j),entropyVar.get(i).get(j)));

				}
				results[i] = result;
			}
		}

		int maxindex = 0;
		double maxvalue = results[0];
		
		for(int i=0;i<9;i++){
			if(!Double.isNaN(results[i])){
				maxvalue=results[i];
				maxindex=i;
				break;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			
			
			
			if (!Double.isNaN(results[i])){
				if(results[i]>0){
					txt+="\n    results[" + i+"] = " + results[i];
				}
				if(results[i] > results[maxindex]) {
					maxvalue = results[i];
					maxindex = i;
				}
			}
				
			}
		
		
		txt+="\n-Results: ";
		for (int i = 0; i < results.length; i++) {
			if (i!=maxindex && i != 1 && i != 4 && i != 5 && i != 6) {
				txt+="\n" + "    Type #"
						+ i
						+ " : "
//						+ String.format("%.2f",
//								Math.log(results[i]) / Math.log(maxvalue))
//						 (results[i]))+ " % "
						+ String.format("%.2f",
//								Math.log(results[i]) / Math.log(maxvalue))
						 (results[i])/(maxvalue))
						+ " times less likely.";
			}
		}
		txt+="\n- This is an activity of type #" + maxindex;
		
		Pair pair = new Pair<Integer, String>(maxindex, txt);
		return pair;

	}
	
	private double p(double v, double m, double var) {
		if (var == 0)
			return 1;
		double p = ((1 / (Math.sqrt(2 * Math.PI * var)) * Math.exp(-(Math.pow(v
				- m, 2))
				/ (2 * var))));

		return p;
	}
	

	public ArrayList<ArrayList<Double>> getEntropyMean(){
		return entropyMean;
	}
	
	public ArrayList<ArrayList<Double>> getEntropyVar(){
		return entropyVar;
	}
}
