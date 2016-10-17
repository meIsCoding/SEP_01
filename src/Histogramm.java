
public class Histogramm {

	public static void main (String[] args ){
		double[] Data1 = {1.1, 1.9, 2.2, 3.0, 5.1, 5.2, 4.3, 0.1, 4.5, 5.1};
		double[] Data2 = {8.0, 6.0, 4.0, 1.0, 2.0, 3.0, 4.0, 9.0};
		String[] Histo1 = makeHistogramm(Data1);
		String[] Histo2 = makeHistogramm(Data2);
		printSquare(Histo1, Data1);
		printSquare(Histo2, Data2);
	}
	
	private static String[] makeHistogramm(double[] TestArray) {
		int MaxX = TestArray.length;
		int MaxY = 0;
		int[] IntArray = doubleToIntArray(TestArray);
		for (int i =0; i < MaxX; i++){
			if (IntArray[i] > MaxY)
				MaxY = IntArray[i];
		}
		String[] Lines = new String[MaxY+2];
		for (int l = 0; l < MaxY; l++){
			if (l==0){
				Lines[l] = "Wert "+MaxY+"|";
			}else if (l%2 == 0){
				Lines[l] = "     "+(MaxY-l)+"|";
			}else{
				Lines[l] = "      |";
			}
		}
		for (int j = 0; j < MaxY; j++){
			for (int k = 0; k < MaxX; k++){
				if(IntArray[k] < MaxY-j){
					Lines[j] = Lines[j] + " ";
				}else{
					Lines[j] = Lines[j] + "*";
				}
			}
		}
		Lines[MaxY] = "      +";
		for (int m = 1; m <= MaxX; m++){
			if (m%2 != 0){
				Lines[MaxY] = Lines[MaxY]+"-";
			}else{
				Lines[MaxY] = Lines[MaxY]+(m%10);
			}
		}
		Lines[MaxY+1] = "  Punkt";
		for (int n = 0; n < MaxX; n++){
			Lines[MaxY+1] = " "+Lines[MaxY+1];
		}
		for(String Line : Lines){
			System.out.println(Line);
		}
		System.out.println("");
		return Lines;
	}
	
	private static void printSquare(String[] Histogramm, double[] Array){
		int[] IntArray = doubleToIntArray(Array);
		int SquareWidth = 0;
		int SquareHeight = 0;
		int SquarePos = 0;
		int SquareSize = 0;
		for (int i = 0; i < IntArray.length; i++){
			int WidthL = 0;
			int WidthR =0;
			int Height = IntArray[i];
			if (i >0){
				for (int j = 1; j <= i; j++){
					if (IntArray[i]<= IntArray[i-j] && WidthL == j-1){
						WidthL++;
					}
				}
			}
			for (int k=1; k < IntArray.length-i; k++){
				if (IntArray[i] <= IntArray[i+k] && WidthR == k-1){
					WidthR++;
				}
			}
			int Width = 1 +WidthL+WidthR;
			if (Width * Height >= SquareSize){ // ">=" für rechtest-, ">" für linkestmögliches Quadrat
				SquareSize = Width * Height;
				SquareWidth = Width;
				SquareHeight = Height;
				if (WidthR==0){
					SquarePos = i;
				}else{
					SquarePos = i+WidthR;
				}

			}
		}
		for (int l = 0; l < Histogramm.length-2; l++){
			if (l >Histogramm.length-SquareHeight-3){
				String Line = Histogramm[l].substring(0, 8+(SquarePos-SquareWidth));
				for (int m = 0; m < SquareWidth; m++){
					Line = Line+"0";
				}
				Line = Line + Histogramm[l].substring(8+SquarePos, Histogramm[l].length());
				Histogramm[l] = Line;
			}
		}
		for(String Line : Histogramm){
			System.out.println(Line);
		}
		System.out.println("");
	}
	
	private static int[] doubleToIntArray(double[] DoubleArray){
		int[] IntArray = new int[DoubleArray.length];
		for (int i = 0; i < DoubleArray.length; i++){
			IntArray[i] = (int) Math.round(DoubleArray[i]);
		}
		return IntArray;
	}
}

