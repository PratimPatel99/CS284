package hw1;
public class BinaryNumber {
	// I pledge my honor that I have abided by the Stevens Honor System -ppate78
// Data Fields
	private int[] data;
	
	private boolean overflow;
	
// constructors
	//Creates a binary number of length length
	public BinaryNumber(int length) {
		int[] bin = new int[length];
		data = bin;
	}
	//takes in a string and returns a binary number
	public BinaryNumber(String str) {
		int[] bin = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			bin[i] = java.lang.Character.getNumericValue(str.charAt(i));
		}
		data = bin;
	}
// Operations
	//gets the length of the binary number
	public int getLength() {
		return data.length;
	}
	//gets the binary number digit at the given index
	public int getDigit(int index) {
		if (index > data.length || index <0) {
			System.out.println("Error: The index exceeds the length, or is less than zero.");
			return -1; 
		} else {
			return data[index];
		}
	}
	//converts a binary number to its decimal form
	public int toDecimal() {
		int decimal = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == 1) {
				decimal += Math.pow(2, i);
			}
		}
		return decimal;
	}
	//shifts the binary numbers a certain amount to the right
	public void shiftR(int amount) {
		BinaryNumber Shift = new BinaryNumber(data.length + amount);
		for (int i = amount; i < Shift.getLength(); i++) {
			Shift.data[i] = data[i-amount];			
		}
		data = Shift.data;
	}
	//adds two binary numbers if they are the same length
	public void add(BinaryNumber aBinaryNumber) {
		BinaryNumber sum = new BinaryNumber(this.getLength() + 1);
		if(data.length != aBinaryNumber.getLength()) {
			System.out.println("Error: Not the same length.");
			throw new ArrayIndexOutOfBoundsException();
		} else {
		if(this.data.length == aBinaryNumber.data.length) {
			for(int i = 0; i < data.length; i++) {
				if(this.data[i] == 1 && aBinaryNumber.data[i] == 1 && sum.overflow == true) {
					sum.data[i] = 1;
					sum.overflow = true;
				}
				else if(this.data[i] == 1 && aBinaryNumber.data[i] == 1 && sum.overflow == false) {
					sum.data[i] = 0;
					sum.overflow = true;
				}
				else if(this.data[i] != aBinaryNumber.data[i] && sum.overflow == true) {
					sum.data[i] = 0;
					sum.overflow = true;
				}
				else if(this.data[i] == 0 && aBinaryNumber.data[i] == 0 && sum.overflow == false) {
					sum.data[i] = 0;
				}
				else if(this.data[i] != aBinaryNumber.data[i] && sum.overflow == false) {
					sum.data[i] = 1;
				}
				
				else {
					sum.data[i] = 1;
					sum.overflow = false;
				}
			}
			if(sum.overflow == true) {
				sum.data[this.getLength()] = 1;
				this.overflow = true;
			}
			else {
			}
			this.data = sum.data;
		}
	  }
	}


//sets overflow to false
	public void clearOverflow() {
		overflow = false;
	}
//toString function to print	
	public String toString() {
		if (overflow == true) {
			return "Overflow";
		} 
		else {
			String binaryString = "";
			for (int i = 0; i < data.length; i++) {
				binaryString += data[i];
			}
			return binaryString;
		}
	}
	public static void main(String[] args) {
		
		
}
}