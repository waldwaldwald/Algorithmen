/**
 * 经典数学问题：百鸡问题的变形 题目描述：有36个人，36块砖，每人搬了一次，正好搬完。其中男每人每次搬4块，女每人每次搬3块，两人每次搬一块。问
 * 男、女、小孩各多少人？
 * 
 * @author Li,Yuanyuan
 * 
 */

public class MenWomenChildren {
	public static void main(String[] args) {
		/**
		 * Es gibt 36 Stuck, ein Man traegt jedesmal 4 Stueck, eine Frau
		 * jedesmal 3 Stueck, zwei Kinder zusammen jedesmal 1. Wie viele
		 * Maenner, Frauen und Kinder gibt es?
		 */

		int manNum = 0;
		int womanNum = 0;
		// 36 durch 4 = 9
		for (int i = 0; i <= 9; i++) {
			// 36 durch 3 = 12
			for (int j = 0; j < 12; j++) {
				// Anzahl von Kindern muss grade sein, sonst kann es ja dass ein
				// Kind nix getragen hat
				if (((i * 4 + j * 3 + (36 - i - j) / 2) == 36)
						&& ((36 - i - j) % 2 == 0)) {
					manNum = i;
					womanNum = j;
					System.out.println("Man: " + manNum);
					System.out.println("Woman: " + womanNum);
					System.out.println("Child: " + (36 - manNum - womanNum));
				}
			}
		}
	}
}