// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
class Rule implements Comparable<Rule>{
    public String name;
    public short choice;
    public short blank;
    public char sort_flag;
    public char unique_flag;
    public long odd;

    public Rule(String name,short choice,short blank,char sort_flag,char unique_flag){
        this.name=name;
        this.choice=choice;
        this.blank=blank;
        this.sort_flag=sort_flag;
        this.unique_flag=unique_flag;
    }
    public int compareTo(Rule other){
        if(odd!=other.odd){
            Long temp_odd=odd;
            return temp_odd.compareTo(other.odd);
        }
        else
            return name.compareTo(other.name);

    }

}



public class Lottery {
    private ArrayList<Long> factorialSet=new ArrayList<Long>();

    public String[] sortByOdds(String[] rules) {
        factorialSet.add(0L);//the 0 index value is 0; count from 1.
        ArrayList<Rule> result_set=new ArrayList<Rule>();
        for(String rule_string:rules){
            Rule current_rule=ReadRuleFromInput(rule_string);
            current_rule.odd=CalculateOdds(current_rule);
            result_set.add(current_rule);
        }
        Collections.sort(result_set);
        String[] res=new String[result_set.size()];
        for(int i=0;i<result_set.size();i++)
            res[i]=result_set.get(i).name;       
        return res;
    }

    private Rule ReadRuleFromInput(String input){
        String[] half_tokens=input.split(":");
        if(half_tokens.length==2){
            String name=half_tokens[0];
            String[] single_tokens=half_tokens[1].split(" ");
            if(single_tokens.length==4){
                short choice=Short.valueOf(single_tokens[0]);
                short blank=Short.valueOf(single_tokens[1]);
                char sort_flag=single_tokens[2].charAt(0);
                char unique_flag=single_tokens[3].charAt(0);
                return new Rule(name,choice,blank,sort_flag,unique_flag);

            }
            else{
                return null;}
        }
        else{
            return null;}
    }

    private long CalculateOdds(Rule rule){
        if(rule.sort_flag=='F'&&rule.unique_flag=='F'){
            return (long)Math.pow(rule.choice,rule.blank);
        } 
        else if(rule.sort_flag=='F'&&rule.unique_flag=='T'){
            return Calculatefactorial(rule.choice)/Calculatefactorial(rule.choice-rule.blank);
        } 
        else if(rule.sort_flag=='T'&&rule.unique_flag=='F'){
            return Calculatefactorial(rule.choice+rule.blank-1)/Calculatefactorial(rule.blank)/Calculatefactorial(rule.choice-1);
        } 
        else if(rule.sort_flag=='T'&&rule.unique_flag=='T'){
            return Calculatefactorial(rule.choice)/Calculatefactorial(rule.blank)/Calculatefactorial(rule.choice-rule.blank);
        }
        else
            return 0L; 
    }

    private long  Calculatefactorial(int n){
        int curr_size=factorialSet.size();
        if(n<curr_size){
            return factorialSet.get(n);
        }
        else if(n==1){
            long result=1;
            factorialSet.add(result);
            return result;
        }
        else if(n>=curr_size){
            long result=Calculatefactorial(n-1)*n;
            factorialSet.add(result);
            return result; 
        }
        else {
            return 0L;
        }
    }





    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new Lottery()).sortByOdds(new String[] {"PICK ANY TWO: 10 2 F F"
                ,"PICK TWO IN ORDER: 10 2 T F"
                ,"PICK TWO DIFFERENT: 10 2 F T"
                ,"PICK TWO LIMITED: 10 2 T T"}),new String[] { "PICK TWO LIMITED",  "PICK TWO IN ORDER",  "PICK TWO DIFFERENT",  "PICK ANY TWO" });
            eq(1,(new Lottery()).sortByOdds(new String[] {"INDIGO: 93 8 T F",
                "ORANGE: 29 8 F T",
                "VIOLET: 76 6 F F",
                "BLUE: 100 8 T T",
                "RED: 99 8 T T",
                "GREEN: 78 6 F T",
                "YELLOW: 75 6 F F"}
                ),new String[] { "RED",  "ORANGE",  "YELLOW",  "GREEN",  "BLUE",  "INDIGO",  "VIOLET" });
            eq(2,(new Lottery()).sortByOdds(new String[] {}),new String[] { });
        } catch( Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }
    private static void eq( int n, int a, int b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, char a, char b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected '"+b+"', received '"+a+"'.");
    }
    private static void eq( int n, long a, long b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"L, received "+a+"L.");
    }
    private static void eq( int n, boolean a, boolean b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, String a, String b ) {
        if ( a != null && a.equals(b) )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"\", received \""+a+"\".");
    }
    private static void eq( int n, int[] a, int[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++)
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, long[] a, long[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, String[] a, String[] b ) {
        if ( a.length != b.length) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if( !a[i].equals( b[i])) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void print( int a ) {
        System.err.print(a+" ");
    }
    private static void print( long a ) {
        System.err.print(a+"L ");
    }
    private static void print( String s ) {
        System.err.print("\""+s+"\" ");
    }
    private static void print( int[] rs ) {
        if ( rs == null) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( long[] rs) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( String[] rs ) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print( "\""+rs[i]+"\"" );
            if( i != rs.length-1)
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void nl() {
        System.err.println();
    }
    // END CUT HERE
}
