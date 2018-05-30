import java.math.BigInteger;
import java.util.Random;
import java.io.*;
public class rsa {
	private BigInteger p,q,N,phi,e,d;
	private int bitlength = 1024;
	private int blocksize = 256;
	private Random r;
	public rsa() {
		r = new Random();
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitlength/2, r);
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {
			e.add(BigInteger.ONE);
		}
		d = e.modInverse(phi);
	}
	public rsa(BigInteger e, BigInteger d, BigInteger N) {
		this.e = e;
		this.d = d;
		this.N = N;
	}
	public static void main (String[] args) throws IOException {
		rsa rsa = new rsa();
		DataInputStream in=new DataInputStream(System.in);
		String teststring ;
		System.out.println("Enter the plain text:");
		teststring=in.readLine();
		byte[] encrypted = rsa.encrypt(teststring.getBytes());
		System.out.println("Encrypted String: "+ new String(encrypted));
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Decrypted String: " + new String(decrypted));
	}
	public byte[] encrypt(byte[] message) {
		return (new BigInteger(message)).modPow(e, N).toByteArray();
	}
	public byte[] decrypt(byte[] message) {
		return (new BigInteger(message)).modPow(d, N).toByteArray();
	}
}