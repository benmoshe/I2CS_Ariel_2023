package exe.ex1.sol;

import java.util.Scanner;

/**
 * This class represents a simple solution to Ex1 (of Introduction to Computer Science, Ariel University 2023), see:
 * https://docs.google.com/document/d/1nxtiCHiREYgIJrIdmkxkTG6tMJDgS2qX/edit
 *
 * The main algorithm is as follows:
 * long mcd(a,b,c){
 *     long t = mcd(a,b);
 *     return mcd(t,c)
 * }
 * long mcd(a,b){
 *    long t = gcd(a,b);
 *    return a*(b/t);
 */
public class Ex1 {
    public static void main(String[] args) {
        if (args.length < 3) {
            args = new String[3];
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");
            args[0] = sc.next();
            System.out.print("Enter second number: ");
            args[1] = sc.next();
            System.out.print("Enter third number: ");
            args[2] = sc.next();
        }
        long n0 = Long.parseLong(args[0]);
        long n1 = Long.parseLong(args[1]);
        long n2 = Long.parseLong(args[2]);

        long mcd = mcd(n0,n1,n2);
        System.out.println("mcd("+n0+","+n1+","+n2+") = "+mcd);

    }
    public static long mcd2(long a, long b){
      if(a<b) {long t=a; a=b; b=t;}
      long ans=a;
      while(ans%b!=0) {
          ans += a;
      }
      return ans;
    }
    public static long mcd2(long a, long b, long c){
        long ans = mcd2(a,b);
        ans = mcd2(ans,c);
        return ans;
    }
    public static long mcd(long a, long b, long c){
        long ans = mcd(a,b);
        ans = mcd(ans,c);
        return ans;
    }
    public static long mcd(long a, long b){
        long gcd_ab = gcd4(a,b);
        long ans = (a/gcd_ab)*b;
        return ans;
    }
    public static long gcd3(long x, long y) {
        while (y != 0)  {long t = y; y=x%y; x=t;}
        return x;
    }
    public static long gcd4(long x, long y) {
        if (y == 0)  {return x;}
        return gcd4(y, x % y);
    }

}
