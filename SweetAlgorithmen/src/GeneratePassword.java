import java.util.Random;
/**
 * 密码的自动生成器：密码由大写字母/小写字母/数字组成，生成六位随机密码;
 *
 */

public class GeneratePassword {
	public static void main(String[] args) {
		char[] pardStore=new char[62];//密码库的建立
		//把所有的大写字母放进去
		for(int i=0;i<26;i++){
			pardStore[i]=(char) ('A'+i);
		}
		//把所有的小写字母放进去
		for(int i=26;i<52;i++){
			pardStore[i]=(char) ('a'+(i-26));
		}
		//把0-9放进去
		for(int i=52;i<62;i++){
			pardStore[i]=(char) ('0'+(i-52));
		}
		//生成6位随机密码
		Random r=new Random();
		for(int i=0;i<16;i++){
			int n=r.nextInt(62);
			System.out.print(pardStore[n]);
		}
		
	}

}
