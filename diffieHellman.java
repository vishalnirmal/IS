import java.io.*;
class diffeHellman
{
	static int power(int x, int y,int b)
    {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return (power(x, y / 2,b) * power(x, y / 2,b))%b;
        else
            return (x * power(x, y / 2,b) * power(x, y / 2,b))%b;
    }
    public static void main(String[] args) throws IOException{
    	int P,g,pa,pb,sa,sb;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter the public no.: ");
    	P = Integer.parseInt(br.readLine());
    	System.out.println("Enter the base: ");
    	g = Integer.parseInt(br.readLine());
    	pa = (int)(Math.random()*(P-1)*(g-1));
		pb = (int)(Math.random()*(P-1)*(g-1));
		System.out.println("Private no. of A: "+pa);
		System.out.println("Private no. of B: "+pb);
		sa = power(g,pa,P);
		sb = power(g,pb,P);
		System.out.println("A sends : "+sa);
		System.out.println("B sends : "+sb);
		System.out.println("Secret key for A: "+power(sa,pb,P));
		System.out.println("Secret key for B: "+power(sb,pa,P));
    }
}