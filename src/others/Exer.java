package others;

public class Exer {

	public static void main(String[] args) {
		
		int a = 77;
		int b = test(a);
		System.out.println(b);

	}
	
	public static int test(int t) {
		
		int c;
		if (t > 90) {
			c = 90;
		}
		if (t <= 90 && t > 80) {
			c = 80;
		}
		if (t <= 80 && t > 70) {
			c = 70;
		}
		else {
			c = 60;
		}
		return c;
	}

}
