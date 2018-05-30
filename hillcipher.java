import java.io.*;
class hillcipher
{
	static int[][] mul(int[][] a,int[][] b)
	{
		int c1 = a[0].length;
		int c2 = b[0].length;
		int r1 = a.length;
		int r2 = b.length;
		int[][] res = new int[r1][c2];
		for(int i=0;i<r1;i++)
			for(int j=0;j<c2;j++)
			{
				int sum = 0;
				for(int k = 0;k<r2;k++)
					sum += a[i][k]*b[k][j];
				res[i][j] = sum;
			}
		return res;
	}
	static int getIndex(char ch)
	{
		if(ch>=65 && ch<=90)
			return (int)(ch-65);
		if(ch>=97 && ch<=122)
			return (int)(ch-97);
		return 0;
	}
	static int[][] generateMatrix(String key)
	{
		int[][] m = new int[2][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				m[i][j] = getIndex(key.charAt(i*2+j));
		return m;
	}
	static int[][] generatePtMatrix(String pt)
	{
		if(pt.length()%2!=0)
			pt+='X';
		int[][] n = new int[pt.length()/2][2];
		for(int i=0,r=0;i<pt.length();i+=2)
		{
			n[r][0] = getIndex(pt.charAt(i));
			n[r++][1] = getIndex(pt.charAt(i+1));
		}
		return n;
	}
	static int[][] inverseMatrix(String key)
	{
		int[][] kt = generateMatrix(key);
		int[][] nt = new int[2][2];
		nt[0][0] = kt[1][1];
		nt[0][1] = -kt[0][1];
		nt[1][0] = -kt[1][0];
		nt[1][1] = kt[0][0];
		return nt;
	}
	static String encrypt(String pt, String key)
	{
		int[][] kt = generateMatrix(key);
		int[][] pkm = generatePtMatrix(pt);
		int res[][] = mul(pkm,kt);
		String ct="";
		for(int i=0;i<res.length;i++)
			for(int j=0;j<res[0].length;j++)
				ct = ct + (char)(res[i][j]%26 + 65);
		return ct;
	}
	static String decrypt(String ct, String key)
	{
		int[][] kt = inverseMatrix(key);
		int kin = kt[0][0]*kt[1][1] - kt[0][1]*kt[1][0];
		for (int i=0;i<2;i++)
			for (int j=0;j<2;j++) 
				kt[i][j]/=kin;
		int[][] ckm = generatePtMatrix(ct);
		int[][] res = mul(ckm,kt);
		String pt="";
		for(int i=0;i<res.length;i++)
			for(int j=0;j<res[0].length;j++)
				pt = pt + (char)((res[i][j]+2600)%26 + 65);
		return pt;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plain text: ");
		String pt = br.readLine();
		System.out.println("Enter the key: ");
		String key = br.readLine();
		String ct = encrypt(pt,key);
		System.out.println("Cipher text: "+ct);
		System.out.println("Plain text: "+decrypt(ct,key));
	}
}