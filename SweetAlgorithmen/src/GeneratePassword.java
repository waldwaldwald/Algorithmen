import java.util.Random;
/**
 * ������Զ��������������ɴ�д��ĸ/Сд��ĸ/������ɣ�������λ�������;
 *
 */

public class GeneratePassword {
	public static void main(String[] args) {
		char[] pardStore=new char[62];//�����Ľ���
		//�����еĴ�д��ĸ�Ž�ȥ
		for(int i=0;i<26;i++){
			pardStore[i]=(char) ('A'+i);
		}
		//�����е�Сд��ĸ�Ž�ȥ
		for(int i=26;i<52;i++){
			pardStore[i]=(char) ('a'+(i-26));
		}
		//��0-9�Ž�ȥ
		for(int i=52;i<62;i++){
			pardStore[i]=(char) ('0'+(i-52));
		}
		//����6λ�������
		Random r=new Random();
		for(int i=0;i<16;i++){
			int n=r.nextInt(62);
			System.out.print(pardStore[n]);
		}
		
	}

}
