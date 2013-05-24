package com.severyn.actidentifier;

import java.util.ArrayList;
import java.util.Arrays;

public final class IdentificationEngine {
	ArrayList<ArrayList<Double>> entropyMean = new ArrayList<ArrayList<Double>>();
	ArrayList<ArrayList<Double>> entropyVar = new ArrayList<ArrayList<Double>>();
	
	public void setEntropyMean(ArrayList<Double> list){
		entropyMean.add(list);
	}
	
	public void setEntropyVar(ArrayList<Double> list){
		entropyVar.add(list);
	}
	
	private double p(double v, double m, double var) {
		if (var == 0)
			return 1;
		double p = ((1 / (Math.sqrt(2 * Math.PI * var)) * Math.exp(-(Math.pow(v
				- m, 2))
				/ (2 * var))));

		return p;
	}
	
	public void classify2(AccFeat q) {
		System.out.println("\n• Starting NBC Classification");
		double[] results = new double[9];
		double result;
		ArrayList<Double> qf = new ArrayList<Double>();

		for (int j = 0; j < 73; j++) {
			qf.add(q.getFeature(j));
		}

		for (int i = 0; i < 9; i++) {
			if (i != 1 && i != 4 && i != 5 && i != 6) { // debug
				result = 1;
				for (int j = 0; j < entropyMean.get(i).size(); j++) {
					result = result * p(qf.get(j), entropyMean.get(i).get(j),entropyVar.get(i).get(j));

				}
				results[i] = result;
			}
		}

		int maxindex = 0;
		double maxvalue = results[0];
		for (int i = 0; i < 9; i++) {
			if (results[i] > results[maxindex]) {
				maxvalue = results[i];
				maxindex = i;
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (i != maxindex && i != 1 && i != 4 && i != 5 && i != 6) {
				System.out.println("    Type #"
						+ i
						+ " : "
						+ String.format("%.2f",
								Math.log(results[i]) / Math.log(maxvalue))
						// (results[i])/(maxvalue))
						+ " times less likely.");
			}
		}
		System.out.println("");

		System.out.println("• This is an activity of type #" + maxindex);

	}
	
	
	
}
