/**
*����һ���ַ�������String str="��ǰ���¹⣬���ǵ���˪����ͷ�����£���ͷ˼���硣"����ӡ�����¸�ʽ�ģ�
*	��  ��  ��  ��
*	ͷ  ͷ  ��  ǰ
*	˼  ��  ��  ��
*	��  ��  ��  ��
*	��  ��  ˪  ��
*	��  ��  ��  ��
*/

public class ChangeHorizonToVerdical {
	public static void main(String[] args) {
		String str="��ǰ���¹⣬���ǵ���˪����ͷ�����£���ͷ˼���硣";
		char[] poet=str.toCharArray();
		int l=18;
		boolean flag=true;
		int i=0;
		while(flag){
			for(int j=l;j>=(0+i);){
				System.out.print(poet[j]+" ");
				j=j-6;
			}
			System.out.println();
			l++;
			i++;
			if(l==24){ flag=false;}
		}
			
	}

}
