/**
 * ������ѧ���⣺�ټ�����ı��� ��Ŀ��������36���ˣ�36��ש��ÿ�˰���һ�Σ����ð��ꡣ������ÿ��ÿ�ΰ�4�飬Ůÿ��ÿ�ΰ�3�飬����ÿ�ΰ�һ�顣��
 * �С�Ů��С���������ˣ�
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