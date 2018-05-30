import java.io.*;
class railfence
{
	static char[][] generate(String s,int l,int key)
	{
		boolean f = true;
		if(s.equals("*"))	f = false;
		char[][] et = new char[key][l];
		int i=0,inc=1,j=0,count=0;
		while(count<l)
		{
			if(i+inc>key-1 || i+inc<0)
				inc*=-1;
			et[i][j] = (f)?s.charAt(count):'*';
			i+=inc;
			j++;
			count++;
		}
		return et;
	}
	static String encrypt(String pt, int key)
	{	
		int i=0,inc=1,j=0,count=0;
		String ct="";
		char[][] et = generate(pt,pt.length(),key);
		for(i=0;i<key;i++)
			for(j=0;j<pt.length();j++)
				if(et[i][j]!='\0')
					ct+=et[i][j];
		return ct;
	}
	static String decrypt(String ct, int key)
	{
		int i=0,inc=1,j=0,count=0;
		String pt="";
		char[][] et = generate("*",ct.length(),key);
		for(i=0;i<key;i++)
			for(j=0;j<ct.length();j++)
				if(et[i][j]=='*')
					et[i][j]=ct.charAt(count++);	
		count=0;
		inc=1;
		i=0;
		j=0;
		while(count<ct.length())
		{
			if(i+inc>key-1 || i+inc<0)
				inc*=-1;
			pt+=et[i][j];
			i+=inc;
			j++;
			count++;
		}
		return pt;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plain text: ");
		String pt = br.readLine();
		System.out.println("Enter the key: ");
		int key = Integer.parseInt(br.readLine());
		String ct = encrypt(pt,key);
		System.out.println("Cipher text: "+ct);
		System.out.println("Plain text: "+decrypt(ct,key));
	}
}