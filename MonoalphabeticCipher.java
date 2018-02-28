import java.io.BufferedReader;
import java.io.InputStreamReader;
public class MonoalphabeticCipher
{
	private String key="MLNKOZPJQIRHSGTFUEVDWCXBYA";
	String encrypt(String pt,String key)
	{
		String ns="";
		for(int i=0;i<pt.length();i++)
		{
			char ch=pt.charAt(i);
			if(ch>=65 && ch<=90)
				ns += key.charAt(ch-65);
			else if(ch>=97 && ch<=122)
				ns += (char)(key.charAt(ch-97)+32);
			else
				ns += ch;
		}
		return ns;	
	}
	String decrypt(String ct,String key)
	{
		String ns="";
		for(int i=0;i<ct.length();i++)
		{
			char ch = ct.charAt(i);
			if(ch>=65 && ch<=90)
				ns += (char)(key.indexOf(ch)+65);
			else if(ch>=97 && ch<=122)
				ns += (char)(key.indexOf(ch-32)+97);
			else
				ns += ch;
		}
		return ns;
	}
	public static void main(String[] args) throws Exception{
		MonoalphabeticCipher n = new MonoalphabeticCipher();
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1.Encrypt\n2.Decrypt");
		int c = Integer.parseInt(sc.readLine());
		if(c==1)
		{
			System.out.println("Enter the plain text: ");
			String pt = sc.readLine();
			/*System.out.println("Enter the key: ");
			n.key = sc.nextLine();*/
			System.out.println(n.encrypt(pt,n.key));
		}
		else if (c==2) 
		{
			System.out.println("Enter the code: ");
			String ct = sc.readLine();
			/*System.out.println("Enter the key: ");
			n.key = sc.nextLine();*/
			System.out.println(n.decrypt(ct,n.key));				
		}
		else
			System.out.println("Invalid input.");
	}
}