import java.util.Scanner;

/**
 * �Ź����������Ź������ÿһ�У�ÿһ�У�б�ŵ�һ�кͷ�б�ŵ�һ�е����ڵ�����֮�;���ȣ� �����������������=9����Ǻܳ����ľŹ��񣻻������ƹ㵽5*5=
 * 25����ֻҪ�к��еĸ�������Ȳ����������Ϳ��ԣ�
 */

public class Sudoku {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("������һ�����ڵ���3������");
		int length = s.nextInt();
		if (length < 3 || length % 2 == 0) {
			System.out.println("��������ֲ��Ϸ���");
			return;
		}
		int[][] nineTable = new int[length][length];
		int indexX = 0;
		int indexY = 0;
		indexY = (nineTable.length - 1) / 2;
		nineTable[indexX][indexY] = 1;
		for (int i = 1; i < nineTable.length * nineTable.length; i++) {
			indexX--;
			indexY++;
			if (indexY >= nineTable.length && indexX >= 0) {
				indexY = 0;
			} else if (indexX < 0 && indexY < nineTable.length) {
				indexX = nineTable.length - 1;
			} else if (indexY >= nineTable.length && indexX < 0) {
				indexY--;
				indexX = indexX + 2;
			} else if (nineTable[indexX][indexY] != 0) {
				indexY--;
				indexX = indexX + 2;
			}
			nineTable[indexX][indexY] = i + 1;

		}
		for (int i = 0; i < nineTable.length; i++) {
			for (int j = 0; j < nineTable[i].length; j++) {
				if (nineTable[i][j] < 10)
					System.out.print(" " + nineTable[i][j] + "   ");
				else {
					System.out.print(nineTable[i][j] + "   ");
				}
			}
			System.out.println();
			System.out.println();
		}

	}

}
