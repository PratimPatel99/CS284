package hw2;

public class Complexity {
//I pledge my honor that I have abided by the Stevens Honor System -ppate78
	

	//O(n^2)
	public void method1(int n) {
		int counter = 0;
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=n;j++) {
				System.out.println("The value of the counter is: " +counter);
				counter++;
			}
		}
	}
	//O(n^3)
	public void method4 (int n) {
		int counter =0;
		for(int i =0; i<=n;i++) {
			for(int j=0;j<=n;j++) {
				for(int k=0;k<=n;k++) {
					System.out.println("The value of the counter is:" +counter);
					counter++;
				}
			}
		}
		
	}
	//O(logn)
	public void method2(int n) {
		int counter = 0;
		for(int i=0;i<=n;i*=2) {
			System.out.println("The value of the counter is:" +counter);
			counter++;
			
		}
	}
	//O(nlogn)
	public void method3(int n) {
		int counter = 0;
		for(int i =0;i<=n;i++) {
			for(int j=0;j<=n;i*=2) {
				System.out.println("The value of the counter is:" + counter);
				counter++;
			}
		}
	}
	//O(log(log(n)))
	public void method5(int n) {
		int counter = 0;
		for(double i = n; i > 2; i = Math.sqrt(i)) {
			System.out.println("The value of the counter is:" + counter);
			counter++;
		}
	}
	public static void main(String [] args) {
		
	}
}
