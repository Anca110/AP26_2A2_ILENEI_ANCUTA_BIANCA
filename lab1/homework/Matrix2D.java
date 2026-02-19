public class Matrix2D{

    //transforma matricea intr un string si o afisez folosind metoda
    static String matrixToString(int[][] image)
    {
        StringBuilder s=new StringBuilder();
        for(int i=0; i<image.length; i++)
        {
            for(int j=0; j<image[i].length; j++)
            {
                if(image[i][j]>150)
                    s.append("\u2591\u2591");
                else
                    s.append("\u2593\u2593");
            }
            s.append("\n");
        }
        return s.toString();
    }



    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insuficiente argumente");
            return;
        }

        int n = Integer.parseInt(args[0]);
        String type = args[1];

        System.out.println("n= " + n);
        System.out.println("se deseneaza: " + type);


        long initial_time=System.nanoTime();

        int[][] image = new int[n][n];

        if (type.equals("rectangle"))
        {
            //fundalul alb
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    image[i][j] = 255;
                }

            //dreptunghi negru
            int start = n / 4;
            int end = 3 * n / 4;
            for (int i = start; i < end; i++)
                for (int j = start; j < end; j++) {
                    image[i][j] = 30;
                }

        }
        else if(type.equals("circle"))
        {
            //fundal negru
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    image[i][j] = 30;
                }
            //cerc alb
            int cx = n / 2;
            int cy = n / 2;
            int r = n / 4;

            int r2 = r * r;

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    int dx = i - cx;
                    int dy = j - cy;
                    if (dx*dx + dy*dy <= r2)
                    {
                        image[i][j] = 255; // cerc alb
                    }
                }
            }
        }

        long final_time=System.nanoTime();

        long timp=final_time-initial_time;

        if(n<20)
        {
            //afisez imaginea
            System.out.print(matrixToString(image));
        }
        else {
            System.out.println("Timp de creare matrice: ");
            System.out.println(timp);
        }

    }
}

