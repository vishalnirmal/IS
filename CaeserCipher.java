import java.io.*;
class CaeserCipher
{
	char backward(char c,int key)
	{
		int location;
		if(c>=65 && c<=90)
		{
			location=(c-key);
			if(location<65)
				location=91-(65-location);
		}
		else
		{
			location=c-key;
			if(location<97)
				location=123-(97-location);
		}
		return (char)location;
	}
	void encrypt(String n,int key)
	{
		String ns = "";
		for(int i=0;i<n.length();i++)
		{
			char ch = n.charAt(i);
			if((int)ch>=65 && (int)ch<=90)
			{
				ns=ns+(char)(ch+(key%26));
			}
			else if(ch>=97 && ch<=122)
			{
				ns=ns+(char)(ch+(key%26));
			}
			else
				ns=ns+ch;
		}
		System.out.println(ns);
	}
	void decrypt(String n,int key)
	{
		String ns = "";
		for(int i=0;i<n.length();i++)
		{
			char ch = n.charAt(i);
			if(ch>=65 && ch<=90)
			{
				ns=ns+backward(ch,key%26);
			}
			else if(ch>=97 && ch<=122)
			{
				ns=ns+backward(ch,key%26);
			}
			else
				ns=ns+ch;
		}
		System.out.println(ns);
	}
	void hack(String n)
	{
		for(int i=0;i<26;i++)
			decrypt(n,i);
	}
	public static void main(String a[]) throws Exception
	{
		CaeserCipher obj = new CaeserCipher();
		String plainText;
		int c,key;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1.Encrypt\n2.Decrypt");
		c = Integer.parseInt(br.readLine());
		System.out.println("Enter the key: ");
		key = Integer.parseInt(br.readLine());
		System.out.print("Enter the message: ");
		plainText = br.readLine();
		if(c==1)
		obj.encrypt(plainText,key);
		else if(c==2)
		obj.decrypt(plainText,key);
		else
		System.out.println("Invalid input.");
		//obj.hack(plainText);
	}
}