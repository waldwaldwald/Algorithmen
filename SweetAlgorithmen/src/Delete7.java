import java.util.Scanner;
/**
 * �Ѵ���ʼ����100֮�ڵ����е������е���7�ı����ĺͺ���7�����������޳�������ӡ����������
 * @author Li,Yuanyuan
 *
 */

public class Delete7 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("������100���ڵ���ʼ��");
		int begin=s.nextInt();
		if(begin<0||begin>100){
			System.out.println("��������������������룡");
			return;
		}
		for(int i=begin;i<100;i++){
			if(i%7!=0&&i%10!=7&&i/10!=7){//���ܴ�7������;
				System.out.println(i+",");
				}
			}
	}

}
