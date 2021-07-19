import java.util.*;
public class Puzzle
{
    static int LIMIT=40;
    static int minimum=LIMIT;
    static HashMap<Integer,Integer> seen=new HashMap<Integer, Integer>();
    static Stack<String> operators=new Stack<String>();
    static int bruh;
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        minimum=LIMIT;
        bruh=n;
        printExpression(n,k);
    }
    static void printExpression(int n,int d)
    {
        minLevel(d,n,d,1);
        if(generate(d,n,d,1))
        {
            String e="";
           if(operators.size()>0)
           e=Integer.toString(d)+operators.pop();
            while(operators.size()>0)
           {
                String popped=operators.pop();
                if(popped.equals("÷") || popped.equals("x"))
                e="("+e+Integer.toString(d)+")"+popped;
                else
                e=e+Integer.toString(d)+popped;
           }
           e=e+Integer.toString(d)+"="+Integer.toString(bruh);
           System.out.println("Expression :- "+e);
        }
        else
        System.out.println("Expression not found.");
    }
    static boolean generate(int total,int n,int d,int level)
    {
        if(total==n)
        return true;
        if(level==minimum)
        return false;
        if(!seen.containsKey(total) || (seen.containsKey(total) && seen.get(total)>=level))
        {
            seen.put(total, level);
            int divide=-1;
            if(total%d==0)
            {
                divide=total/d;
                if(!seen.containsKey(divide))
                seen.put(divide, level+1);
           }
           int addition=total+d;
           if(!seen.containsKey(addition))
           seen.put(addition, level+1);
           int subtraction=-1;
           if(total-d>0)
           {
               subtraction=total-d;
               if(!seen.containsKey(subtraction))
               seen.put(subtraction, level+1);
           }
           int multiplication=total*d;
           if(!seen.containsKey(multiplication))
           seen.put(multiplication, level+1);
           int power=(int)(Math.pow(total,d));
           if(!seen.containsKey(power))
           seen.put(power, level+1);
           if(divide!=-1 && generate(divide,n,d,level+1))
           {
               operators.add("÷");
               return true;
           }
           if(generate(addition,n,d,level+1))
           {
               operators.add("+");
               return true;
           }
           if(subtraction!=-1 && generate(subtraction,n,d,level+1))
           {
               operators.add("-");
               return true;
           }
           if(generate(multiplication,n,d,level+1))
           {
               operators.add("x");
               return true;
           }
           if(generate(power,n,d,level+1))
           {
               operators.add("^");
               return true;
           }
     }
     return false;
 }
 static void minLevel(int total,int n,int d,int level)
 {
     if(total==n)
     {
        minimum=Math.min(minimum,level);
        return;
     }
     if(level==minimum)
     return;
     if(total%d==0)
     minLevel(total/d,n,d,level+1);
     minLevel(total+d,n,d,level+1);
     if(total-d>0)
     minLevel(total-d,n,d,level+1);
     minLevel(total*d,n,d,level+1);
     minLevel(((int)Math.pow(total,d)),n,d,level+1);
 }
}
