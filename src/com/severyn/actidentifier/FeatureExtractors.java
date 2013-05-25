package com.severyn.actidentifier;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import edu.emory.mathcs.jtransforms.fft.*;

public final class FeatureExtractors {
	public final static FeatureExtractors INSTANCE = new FeatureExtractors();
	static float alpha = 0.10f;

	private FeatureExtractors() {
		// Exists only to defeat instantiation.
	}

	public static String getType(int type) {
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
	
	public static double[] fftest(ArrayList<Double> v) {
		// ArrayList<Double> result = new ArrayList<>();
		DoubleFFT_1D fftDo = new DoubleFFT_1D(v.size());

		double[] fft = new double[v.size() * 2];

		for (int i = 0; i < v.size(); i++) {
			fft[i] = v.get(i);
		}
		fftDo.realForwardFull(fft);

		double[] result = new double[v.size()];

		double max = 0;

		for (int i = 1; i < v.size(); i++) {
			// System.out.println(Math.sqrt(fft[i]*fft[i]+fft[i+v.size()-1]*fft[i+v.size()-1]));
			result[i - 1] = Math.sqrt(fft[i] * fft[i] + fft[i + v.size() - 1]
					* fft[i + v.size() - 1]);
			if (result[i - 1] > max)
				max = result[i - 1];
		}
		// System.out.println(max);
		return result;
		// for (double d : fft) {
		// result.add(d);
		// }
		// return result;
	}

	public static int[] calcHistogram(double[] data, double min, double max,
			int numBins) {
		final int[] result = new int[numBins];
		final double binSize = (max - min) / numBins;

		for (double d : data) {
			int bin = (int) ((d - min) / binSize); // changed this from numBins
			if (bin < 0) { /* this data is smaller than min */
			} else if (bin >= numBins) { /* this data point is bigger than max */
			} else {
				result[bin] += 1;
			}
		}
		return result;
	}

	public static int[] calcHistogram(ArrayList<Double> data, double min,
			double max, int numBins) {
		final int[] result = new int[numBins];
		final double binSize = (max - min) / numBins;

		for (double d : data) {
			int bin = (int) ((d - min) / binSize); // changed this from numBins
			if (bin < 0) { /* this data is smaller than min */
			} else if (bin >= numBins) { /* this data point is bigger than max */
			} else {
				result[bin] += 1;
			}
		}
		return result;
	}

	public static int zeroCrossingCount(ArrayList<Double> data2) {
		float spread = 0.30f;
		int rate = 8;

		int count = 0;

		double max = Collections.max(data2);
		double min = Collections.min(data2);

		ArrayList<Double> data = (ArrayList<Double>) data2.clone();

		double x;
		double previous = data.get(0);
		for (int i = rate; i + rate <= data.size(); i = i + rate) {
			x = data.get(i);
			if (previous < 0 && x > 0 || previous > 0 && x < 0) {
				count++;
			}
			previous = x;

		}

		return count;
	}

	public static double averageResultantAcceleration(ArrayList<Double> xv,
			ArrayList<Double> yv, ArrayList<Double> zv) {
		float result = 0;
		for (int i = 0; i < xv.size(); i++) {
			double atomicResult = 0;
			double x = xv.get(i);
			double y = yv.get(i);
			double z = zv.get(i);

			x = x * x;
			y = y * y;
			z = z * z;

			atomicResult = x + y + z;
			atomicResult = Math.sqrt(atomicResult);
			result += atomicResult;
		}

		return result / xv.size();
	}

	public static ArrayList<Double> highPassFilter(ArrayList<Double> v) {
		alpha = 1 - alpha;
		ArrayList<Double> output = new ArrayList<Double>();
		output.add(v.get(0));
		for (int i = 1; i < v.size(); i++) {

			output.add(alpha * output.get(i - 1) + alpha
					* (v.get(i) - v.get(i - 1)));
		}

		return output;
	}

	public static ArrayList<Double> lowPassFilter(ArrayList<Double> v) {
		ArrayList<Double> output = new ArrayList<Double>();

		output.add(v.get(0));
		for (int i = 1; i < v.size(); i++) {
			output.add(alpha * v.get(i) + (1 - alpha) * output.get(i - 1));
		}

		return output;
	}

	public static double standardDeviation(ArrayList<Double> arrayList) {
		DescriptiveStatistics stats = new DescriptiveStatistics();

		// Add the data from the array
		for (int i = 0; i < arrayList.size(); i++) {
			stats.addValue(arrayList.get(i));
		}
		return stats.getStandardDeviation();

	}

	public static double calculateMean(ArrayList<Double> arrayList) {
		Double sum = 0.0;
		for (Double mark : arrayList) {
			sum += mark;
		}
		return sum.doubleValue() / arrayList.size();
	}

	public static double calculateMeanInt(ArrayList<Integer> arrayList) {
		Double sum = 0.0;
		for (Integer mark : arrayList) {
			sum += mark;
		}
		return (sum.doubleValue() / arrayList.size());
	}

	public static double averageDistanceBetweenPeaks(ArrayList<Double> v) {
		ArrayList<Integer> distances = new ArrayList<Integer>();

		// /peak detection methodology
		ArrayList<Integer> maxtab = peakdet(v);

		for (int i = 1; i < maxtab.size(); i++) {
			distances.add(maxtab.get(i) - maxtab.get(i - 1));
		}

		return calculateMeanInt(distances);

	}

	public static double roundThreeDecimals(double d) {
		DecimalFormat threeDForm = new DecimalFormat("#.###");
		return Double.valueOf(threeDForm.format(d));
	}

	private static ArrayList<Integer> removeSimilar(ArrayList<Integer> v, int k) {
		Collections.sort(v);
		int previous = v.get(0);
		int current;
		int i = 1;

		while (i < v.size()) {
			current = v.get(i);
			if ((current - previous) < k) {
				v.remove(i);
			} else {
				i++;
			}
			previous = current;
		}
		return v;
	}

	public static ArrayList<Integer> peakdet(ArrayList<Double> v) {
		ArrayList<Integer> peakIndices = new ArrayList<Integer>();
		double max = Collections.max(v);
		double cutoff = max * 0.85;
		int iterations = 0;
		while (peakIndices.size() < 3 && iterations < 40) {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > cutoff) {
					peakIndices.add(Integer.valueOf(i));
				}
			}

			if (peakIndices.size() > 1) {
				peakIndices = removeSimilar(peakIndices, 8);
			}
			cutoff -= 0.05f;
			iterations++;
		}
		if (peakIndices.size() > 1) {
			peakIndices = removeSimilar(peakIndices, 8);
		} else {
			peakIndices.add(0);
			peakIndices.add(0);
			peakIndices.add(0);
		}
		return peakIndices;

	}

	public static ArrayList<Integer> peakdet2(ArrayList<Double> v) {
		double delta = (Collections.max(v) - Collections.min(v)) * 0.5;
		ArrayList<Integer> maxtab = new ArrayList<Integer>();
		ArrayList<Integer> mintab = new ArrayList<Integer>();

		double mn = 10000;
		double mx = -10000;

		int mnpos = 0;
		int mxpos = 0;

		boolean lookformax = true;

		for (int i = 0; i < v.size(); i++) {
			double d = v.get(i);
			if (d > mx) {
				mx = d;
				mxpos = i;
			}
			if (d < mn) {
				mn = d;
				mnpos = i;
			}

			if (lookformax) {
				if (d < mx - delta) {
					maxtab.add(mxpos);
					mn = d;
					mnpos = i;
					lookformax = false;
				}
			} else {
				if (d > mn + delta) {
					mintab.add(i);
					mx = d;
					mxpos = i;
					lookformax = true;
				}
			}
		}
		return maxtab;
	}

	public static ArrayList<Integer> peakdetN(ArrayList<Double> v) {
		ArrayList<Integer> maxtab = new ArrayList<Integer>();
		double avg = calculateMean(v);
		double sd = standardDeviation(v);

		for (int i = 0; i < v.size(); i++) {
			double val = v.get(i);
			if ((val - avg) >= sd) {
				maxtab.add(i);
			}
		}
		return maxtab;
	}

	public static AccFeat calculateFeatures(AccData a) {
		AccFeat temp = new AccFeat();
		ArrayList<Double> xData = a.getxData();
		ArrayList<Double> yData = a.getyData();
		ArrayList<Double> zData = a.getzData();

		ArrayList<Double> lpfxData = FeatureExtractors.lowPassFilter(xData);
		ArrayList<Double> lpfyData = FeatureExtractors.lowPassFilter(yData);
		ArrayList<Double> lpfzData = FeatureExtractors.lowPassFilter(zData);

		temp.setMean(0, FeatureExtractors.calculateMean(xData));
		temp.setMean(1, FeatureExtractors.calculateMean(yData));
		temp.setMean(2, FeatureExtractors.calculateMean(zData));

		temp.setSd(0, FeatureExtractors.standardDeviation(xData));
		temp.setSd(1, FeatureExtractors.standardDeviation(yData));
		temp.setSd(2, FeatureExtractors.standardDeviation(zData));

		//

		temp.setAvPeakDistance(0,
				FeatureExtractors.averageDistanceBetweenPeaks(lpfxData));
		temp.setAvPeakDistance(1,
				FeatureExtractors.averageDistanceBetweenPeaks(lpfyData));
		temp.setAvPeakDistance(2,
				FeatureExtractors.averageDistanceBetweenPeaks(lpfzData));

		temp.setResultantAcc(FeatureExtractors.averageResultantAcceleration(
				a.getxData(), a.getyData(), a.getzData()));

		temp.setFftHistogram(0, FeatureExtractors.calcHistogram(
				FeatureExtractors.fftest(xData), 0, 100, 10));
		temp.setFftHistogram(1, FeatureExtractors.calcHistogram(
				FeatureExtractors.fftest(yData), 0, 100, 10));
		temp.setFftHistogram(2, FeatureExtractors.calcHistogram(
				FeatureExtractors.fftest(zData), 0, 100, 10));

		temp.setHistogram(0,
				FeatureExtractors.calcHistogram(xData, -15, 15, 10));
		temp.setHistogram(1,
				FeatureExtractors.calcHistogram(yData, -15, 15, 10));
		temp.setHistogram(2,
				FeatureExtractors.calcHistogram(zData, -15, 15, 10));

		temp.setCrossingCount(0, FeatureExtractors
				.zeroCrossingCount(FeatureExtractors.highPassFilter(lpfxData)));
		temp.setCrossingCount(1, FeatureExtractors
				.zeroCrossingCount(FeatureExtractors.highPassFilter(lpfyData)));
		temp.setCrossingCount(2, FeatureExtractors
				.zeroCrossingCount(FeatureExtractors.highPassFilter(lpfzData)));

		return temp;
	}

}
