import java.io.*;
class otp
{
	static int getIndex(char ch)
	{
		if(ch>=65 && ch<=90)
			return (int)(ch-65);
		if(ch>=97 && ch<=122)
			return (int)(ch-97);
		return 0;
	}
	static String encrypt(String pt, String key)
	{
		String ct="";
		for (int i=0;i<pt.length();i++) 
			ct = ct+(char)((getIndex(pt.charAt(i))+getIndex(key.charAt(i)))%26+65);
		return ct;
	}
	static String decrypt(String ct, String key)
	{
		String pt="";
		for (int i=0;i<ct.length();i++) 
			pt = pt+(char)((getIndex(ct.charAt(i))-getIndex(key.charAt(i))+26)%26+65);
		return pt;
	}
	static String generate(int l)
	{
		String nk="";
		for(int i=0;i<l;i++)
		{
			nk+=(char)((int)(Math.random()*26)+65);
		}
		return nk;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plain text: ");
		String pt = br.readLine();
		String key = generate(pt.length());
		String ct = encrypt(pt,key);
		System.out.println("Cipher text: "+ct);
		System.out.println("Plain text: "+decrypt(ct,key));
	}
}