
public class Matrix2D{

    static int[] findBox(int[][] image, int background)
    {
        int rand_sus=image.length;
        int rand_jos=-1;
        int col_stanga=image[0].length;
        int col_dreapta=-1;

        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[i].length; j++){
                if(image[i][j]!=background){
                    if(i<rand_sus) rand_sus=i;
                    if(i>rand_jos) rand_jos=i;
                    if(j<col_stanga) col_stanga=j;
                    if(j>col_dreapta) col_dreapta=j;
                }
            }
        }
        if(rand_jos==-1) return null;

        return new int[]{rand_sus, rand_jos, col_stanga, col_dreapta};
    }


    //transforma matricea intr un string si o afisez folosind metoda
    static String matrixToString(int[][] image)
    {
        StringBuilder s=new StringBuilder();
        for(int i=0; i<image.length; i++)
        {
            for(int j=0; j<image[i].length; j++)
            {
                if(image[i][j]>150)
                    s.append("\u2591");
                else
                    s.append("\u2593");
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
            //fac fundalul alb
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    image[i][j] = 255;
                }

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


        int background;
        if(type.equals("rectangle")){
            background=255;
        }else if(type.equals("circle")) {
            background=30;
        }
        else{
            System.out.println("forma necunoscuta");
            return;
        }
        int[] box=findBox(image, background);

        if(box==null){
            System.out.println("Nu exista forma");
        }else{
            System.out.println("Cutia de incadrare: ");
            System.out.println("Sus: "+ box[0]);
            System.out.println("Jos: "+ box[1]);
            System.out.println("Stanga: "+ box[2]);
            System.out.println("Dreapta: "+ box[3]);

        }

    }



}
