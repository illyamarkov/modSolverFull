/*
 Made by: Illya Markov
 I made this in grade 11 to help out students taking Cryptography in third year university.
 Feel free to use this when solving the tedious homework you may be assigned.
 
 I did actually make this entirely, you can tell by the odd code at some places...
 If I decide to, I will also comment what each part does, but I think it's pretty obvious.
 */

import java.util.Scanner;

public class modSolverFull {
	
	public static int GCD;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("nx ≡ y(mod z)");
		System.out.print("n:");
		int val = input.nextInt();
		boolean justStarted = true;
		System.out.print("y:");
		int res = input.nextInt();
		System.out.print("z:");
		int mod = input.nextInt();
		
		int resSave = res;
		int valSave = val;
		int modSave = mod;
		
		int l = 2;
		
		boolean toGCD = true;
		
		System.out.println(val + "x ≡ " + res + "(mod " + mod + ")");
		
		int i = 1;
		
		int j = 1;
		
		while (val != 1) {
			if (toGCD == true) {
				if (justStarted == true) {
					System.out.println("Invoked initial GCD");
					GCDstart(val, mod, res);
					val = val/GCD;
	            	res = res/GCD;
	            	mod = mod/GCD;
	            	justStarted = false;
				}
				else {
				GCDtest(val, mod);
				}
				for(int k = 2; k <= val && k <= res && k <= mod; k++)
		        {
		            if(val%k==0 && res%k==0 && mod%k==0 && GCD == 1) {
		            	System.out.println("GCD Iteration " + (l-1) + ": " +val + "x ≡ " + res + "(mod " + mod + ")");
		            	val = val/k;
		            	res = res/k;
		            	System.out.println("GCD: " + k);
		            	break;
		            }
		        }
				toGCD = false;
			}
			if (val > mod) {
				val = val%mod;
			}
			if (res > mod) {
				res = res%mod;
			}
			System.out.println("Simplification " + j + ": " +val + "x ≡ " + res + "(mod " + mod + ")");
			if (val == 1) {
				break;
			}
			res = res*((int)Math.ceil((float)mod/(float)val));
			val = val*((int)Math.ceil((float)mod/(float)val));
			System.out.println("Iteration " + j + ": " + val + "x ≡ " + res + "(mod " + mod + ")");
			if (j > 300) {
				System.out.println("\nPROBABLY IMPOSSIBLE, RUNNING MODULAR MULTIPLICATIVE INVERSE...  (MIGHT HAVE TO TWEAK J)");
				break;
			}
			if (res == 0 || val == 0) {
				System.out.println("Something was equal to 0, multiplying both by " + l);
				val = valSave * l;
				res = resSave * l;
				toGCD = true;
				l++;
			}
			j++;
		}
		
		
		boolean isPossible = true;
		int alreadySeen = (val*i)%mod;
		
		while ((val*i)%mod != res) {
			i++;
			if ((val*i)%mod == alreadySeen) {
				isPossible = false;
				modInverse(valSave, modSave, resSave);
				break;
			}
		}
		if (isPossible == true) {
			System.out.println("x≡" + i + "(mod " + mod + ")");
		}
	}
	public static void GCDtest (int a, int b) {
		boolean GCDcheck = false;
		for (int i = 1; i <= a; i++) {
			if (a%i == 0 && b%i == 0) {
				GCD = i;
				GCDcheck = true;
			}
		}
		if (GCD == 1) {
			System.out.println("Prime");
		}
		else {
			System.out.println("Composite: " + GCD);
		}
	}
	public static void GCDstart (int a, int b, int c) {
		boolean GCDcheck = false;
		for (int i = 1; i <= a; i++) {
			if (a%i == 0 && b%i == 0 && c%i == 0) {
				GCD = i;
				GCDcheck = true;
			}
		}
		if (GCD == 1) {
			System.out.println("Prime");
		}
		else {
			System.out.println("Composite: " + GCD);
		}
	}
	public static void modInverse (int a, int b, int c) {
		boolean verified = false;
		for (int i = 0; i < b; i++) {
			if ((a*i)%b == 1) {
				GCD = i;
				verified = true;
			}
		}
		if (verified == false) {
			System.out.print("YEP, IMPOSSIBLE (NO MULTIPLICATIVE INVERSE)");
		}
		else {
			System.out.println("POSSIBLE!");
			System.out.println(a + "*" + GCD + " = 1(mod " + b + ")");
			System.out.print(GCD + "*" + c + " = ");
			GCD*=c;
			System.out.print(GCD%b);
		}
	}
}