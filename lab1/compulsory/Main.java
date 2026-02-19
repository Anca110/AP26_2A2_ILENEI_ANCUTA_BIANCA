//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");

        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n=(int) (Math.random() * 1_000_000);
        int m=n*3;
        m=m+0b10101;
        m=m+0xFF;
        m=m*6;

        while(m>10)
        {
            int sum=0;
            while(m>0)
            {
                sum=sum+ m%10;
                m=m/10;
            }
            m=sum;
        }
        System.out.println(m);

        System.out.println("Willy-nilly, this semester I will learn " + languages[m]);

    }//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
}