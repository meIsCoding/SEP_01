public class Histogramm {

	public static void main (String[] args ){
		String pixel 		= "X";
		String squarePixel  = "0";
		double[] data1 = {1.1, 1.9, 2.2, 3.0, 5.1, 5.2, 4.3, 0.1, 4.5, 5.1};
		double[] data2 = {8.0, 6.0, 4.0, 1.0, 2.0, 3.0, 4.0, 9.0};
		String[] histo1 = makeHistogramm(data1, pixel);
		printSquare(histo1, data1, squarePixel);
		String[] histo2 = makeHistogramm(data2, pixel);
		printSquare(histo2, data2, squarePixel);
	}

	private static String[] makeHistogramm(double[] array, String pixel) {
		int[] intArray = doubleToIntArray(array);
		int maxX       = array.length;
		int maxY       = maxValue(intArray);
		String[] lines = new String[maxY+2];
		lines[0] = "Wert "+ maxY +"|";
		for (int i = 1; i < maxY; i++){
			lines[i] = "      |";
			if (i%2 == 0){lines[i] = lines[i].substring(0,5) + (maxY-i) +"|";}
		}
		for (int j = 0; j < maxY; j++){
			for (int k = 0; k < maxX; k++){
				if(intArray[k] < maxY-j){lines[j] += " ";}
				else{lines[j] += pixel;}
			}
		}
		lines[maxY] = "      +";
		for (int m = 1; m <= maxX; m++){
			if (m%2 != 0){lines[maxY] += "-";}
			else{lines[maxY] += m%10;}
		}
		lines[maxY+1] = "";
		for (int n = 0; n < maxX; n++){
			lines[maxY+1] += " ";
		}
		lines[maxY+1] += "Punkt";
		printLines(lines);
		return lines;
	}

	private static void printSquare(String[] lines, double[] array, String squarePixel){
		int[] intArray   = doubleToIntArray(array);
		int squareWidth  = 0;
		int squareHeight = 0;
		int squarePos    = 0;
		int squareSize   = 0;
		for (int i = 0; i < intArray.length; i++){
			int widthL = 0;
			int widthR = 0;
			int height = intArray[i];
			if (i > 0){
				for (int j = 1; j <= i; j++){
					if (intArray[i]<= intArray[i-j] && widthL == j-1){widthL++;}
				}
			}
			for (int k=1; k < intArray.length-i; k++){
				if (intArray[i] <= intArray[i+k] && widthR == k-1){widthR++;}
			}
			int size = (widthL + 1 + widthR) * height;
			if (size >= squareSize){ // ">=" für rechtest-, ">" für linkestmögliches Quadrat
				squareSize   = size;
				squareWidth = size/height;
				squareHeight = height;
				if (widthR==0){squarePos = i;}
				else{squarePos = i + widthR;}
			}
		}
		for (int l = 0; l < (lines.length - 2); l++){
			if (l > (lines.length - (squareHeight + 3))){
				String line = lines[l].substring(0, 8 + (squarePos - squareWidth));
				for (int m = 0; m < squareWidth; m++){
					line += squarePixel;
				}
				line += lines[l].substring(8 + squarePos, lines[l].length());
				lines[l] = line;
			}
		}
		printLines(lines);
	}

	private static int[] doubleToIntArray(double[] DoubleArray){
		int[] IntArray = new int[DoubleArray.length];
		for (int i = 0; i < DoubleArray.length; i++){
			IntArray[i] = (int) Math.round(DoubleArray[i]);
		}
		return IntArray;
	}

	private static int maxValue(int[] array)
	{
		int max = 0;
		for (int i = 0; i < array.length; i++){
			if (array[i] > max){max = array[i];}
		}
		return max;
	}

	private static void printLines(String[] lines){
		System.out.println();
		for(String line : lines){
			System.out.println(line);
		}
	}
}

