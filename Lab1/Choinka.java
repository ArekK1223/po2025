public class choinka {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Podaj wysokosc choinki, np.: java Choinka 5");
			return;
		}
		
		int h = Integer.parseInt(args[0]);
		for (int i = 1; i <= h; i++) {
			System.out.println(" ".repeat(h-i) + "*".repeat(2*i -1));
		}
	}
}