import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        cartellera();
    }

    private static void cartellera()
    {
       String[] cartellera = new String[2];

        cartellera[0] = "El Gato con Botas: El último deseo";
        cartellera[1] = "Astérix y Obélix: El Reino Medio";
        /* cartellera[2] = "Llaman a la puerta ";
        cartellera[3] = "Todo a la vez en todas partes";
        cartellera[4] = "Avatar: El sentido del agua";
        cartellera[5] = "Los Fabelman";
        cartellera[6] = "Titanic (25 aniversario) ";
        cartellera[7] = "El piloto";
        cartellera[8] = "La ballena (The Whale)";
        cartellera[9] = "Babylon";
        cartellera[10] = "El asombroso Mauricio";
        cartellera[11] = "Operación Fortune: El gran engaño";
        cartellera[12] = "M3GAN"; */

        int[][] horari = new int[12][3];

        horari[0][0] = 0;
        horari[0][1] = 14;
        horari[0][2] = 30;
        horari[1][0] = 0;
        horari[1][1] = 15;
        horari[1][2] = 30;

        horari[2][0] = 1;
        horari[2][1] = 20;
        horari[2][2] = 30;

        for (int a = 0; a < cartellera.length; a++)
        {
            System.out.println(cartellera[a]);

            for (int i = 0; i < horari.length; i++)
            {
                if (horari[i][0] == a)
                {
                    for (int j = 0; j < horari[j].length; j++)
                    {
                        if (horari[i][2] != 0)
                        {
                            System.out.println(horari[i][1] + ":" + horari[i][2]);
                            break;
                        }
                    }
                }
            }
        }
    }
}
