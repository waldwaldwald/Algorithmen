import java.util.Scanner;
/**
 * 把从起始数到100之内的所有的整数中的是7的倍数的和含有7的数字数都剔除掉，打印其它的数；
 * @author Li,Yuanyuan
 *
 */

public class Delete7 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入100以内的起始数");
		int begin=s.nextInt();
		if(begin<0||begin>100){
			System.out.println("输入的数有误，请重新输入！");
			return;
		}
		for(int i=begin;i<100;i++){
			if(i%7!=0&&i%10!=7&&i/10!=7){//不能带7的条件;
				System.out.println(i+",");
				}
			}
	}

}
