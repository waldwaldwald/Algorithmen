import java.util.Random;

/**
 * дһ����Ʊ�����ɴ��룺 1-33���ѡ7�����ظ������֣�
 *
 */
public class GenerateDistinctNumber {
	public static void main(String[] args){
		int[] lucyTickets=new int[7];
		Random r=new Random();
		for(int i=0;i<lucyTickets.length;i++){
			lucyTickets[i]=r.nextInt(33)+1;
			for(int j=0;j<i;j++){
				if(lucyTickets[i]==lucyTickets[j]){
					i--;
					break;
				}	
			}
			
		}
		for(int i=0;i<lucyTickets.length;i++){
			System.out.print(lucyTickets[i]+" ");
			
		}
	}

}
