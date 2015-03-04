/**
*定义一个字符串变量String str="床前明月光，疑是地上霜。举头望明月，低头思故乡。"。打印成如下格式的：
*	低  举  疑  床
*	头  头  是  前
*	思  望  地  明
*	故  明  上  月
*	乡  月  霜  光
*	。  ，  。  ，
*/

public class ChangeHorizonToVerdical {
	public static void main(String[] args) {
		String str="床前明月光，疑是地上霜。举头望明月，低头思故乡。";
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
