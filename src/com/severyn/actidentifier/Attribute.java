package com.severyn.actidentifier;
public class Attribute {
		double mean;
		double variance;

		Attribute(double mean, double variance) {
			this.mean = mean;
			this.variance = variance;
		}

		double getVar() {
			return variance;
		}

		double getM() {
			return mean;
		}

		void setMean(double mean) {
			this.mean = mean;
		}

		void setVariance(double variance) {
			this.variance = variance;
		}
	}
