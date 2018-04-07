package hr.fer.zemris.avsp.lab1;

public class Main {

	public static void main(String[] args) {
		SimHash sh = new SimHash("fakultet elektrotehnike i racunarstva", false);
		System.out.println(sh.calculateSimHash());
	}

}
