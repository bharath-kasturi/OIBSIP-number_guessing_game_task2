import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class guessTheNumber {
    static int lvl=1;
    static HashMap<Integer,Integer> h=new HashMap<>();
    static int highest=0;
    public static void displayStats(){
        if(h.isEmpty()){
            System.out.println("\n\t\t\tYou haven't Cleared any level Yet!");
        }
        else{
        System.out.println("LEVEL\t\t\tSCORE");
        Set<Integer> s=h.keySet();
        
        for(int i:s){
            System.out.println(i+"\t\t\t"+h.get(i));
        }
        System.out.println("\nCurrent Best: LEVEL "+lvl+"\nHighest level Reached is : LEVEL "+highest);
        }
    }
    public static void SetHighest(int n){
        try (
        FileReader r = new FileReader("highscore.txt")) {
            int c=0;
            String num="";
            while((c=r.read())!=-1){
                char ch=(char)c;
                num=num+ch;
            }
             c=Integer.parseInt(num);
             highest=c;
            if(n>c){
                try (
        FileWriter f = new FileWriter("highscore.txt")) {
            highest=n;
            
            f.write(Integer.toString(n));
            
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    public static void Operator(){
        System.out.println("\n\t\t\tLEVEL "+lvl);
        Scanner sc=new Scanner(System.in);
        int n=(int)(Math.random()*100);
        int ch=10;
        int temp;
        int att=0;
        while(ch-->0){
            System.out.print("Enter the number :- ");
            temp=sc.nextInt();
            if(temp<0||temp>100){
                att++;
                System.out.println("Out of Range enter the number between 1 to 100 ["+ch+" attempts left]");
            }
            else{
                if(temp==n){
                h.put(lvl,100-(att*10));
                att=0;
                lvl++;
                System.out.println("\n\t\t\t\t\tCorrect Answer!");
                int k;
                System.out.println("Enter one option among the following");
                System.out.println("1.Continue with the next level\n2.Exit");
                System.out.print("Your Option :- ");
                k=sc.nextInt();
                if(k==1){
                    Operator();
                    return;
                }
                else{
                    lvl--;
                    SetHighest(lvl);
                    displayStats();
                    return;
                }
                
            }
            else if(temp>n){
                System.out.println("Your number is HIGHER than the number ["+ch+" attempts left]");
                
                att++;
            }
            else{
                System.out.println("Your number is LOWER than the number ["+ch+" attempts left]");
                
                att++;
            }
            }
            
        }
        System.out.println("\n\t\t\tOops! you are out of attempts the number is "+n);
        System.out.println("Enter one option among the following");
        System.out.println("1.Try again\n2.Exit");
        int k;
        System.out.print("Your Option :- ");
        k=sc.nextInt();
        if(k==1){
            Operator();
            return;
        }
        else{
            displayStats();
            return;
        }
    }
    public static void main(String[] args) {
        System.out.println("\t\t\t\tGUESS THE NUMBER BETWEEN 1 AND 100");
        Operator();
    }
}
