import java.util.Random;

/**
 * 写一个彩票的生成代码： 1-33随机选7个不重复的数字；
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
