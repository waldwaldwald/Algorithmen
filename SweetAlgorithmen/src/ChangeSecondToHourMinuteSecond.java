import java.util.Scanner;
/**
 * 输入一个秒数，要求转换为XX小时XX分XX秒的格式输出出来;
 * @author Li,Yuanyuan
 *
 */
public class ChangeSecondToHourMinuteSecond {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入秒数：");
		int second =s.nextInt();
		int hour=second/3600;
		int minite=second%3600/60;
		int sec=second%60;
		System.out.println("转换后为："+hour+"小时："+minite+"分钟："+sec+"秒");
	}

}
