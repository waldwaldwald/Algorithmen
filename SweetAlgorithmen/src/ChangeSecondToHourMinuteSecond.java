import java.util.Scanner;
/**
 * ����һ��������Ҫ��ת��ΪXXСʱXX��XX��ĸ�ʽ�������;
 * @author Li,Yuanyuan
 *
 */
public class ChangeSecondToHourMinuteSecond {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("������������");
		int second =s.nextInt();
		int hour=second/3600;
		int minite=second%3600/60;
		int sec=second%60;
		System.out.println("ת����Ϊ��"+hour+"Сʱ��"+minite+"���ӣ�"+sec+"��");
	}

}
