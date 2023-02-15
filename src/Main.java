import javax.management.MalformedObjectNameException;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        menuPrincipal();
    }
    private static void menuPrincipal()
    {
        // mateo
        String[] cartellera = new String[12];
        int[][] horari = new int[12][3];

        final String TITOL = "||||||| CINES IMPERIAL |||||||"; // 30 Char
        final String MENU_PRINCIPAL = " 1. Veure cartellera \n" + " 2. Comprar entrades \n" + " 3. Sortir";

        // Mètode per delcarar les dades del la cartellera i els horaris //
        cartelleraHorari(horari, cartellera);
        // mostrarCartelleraHoraris(cartellera, horari);

        System.out.println("\n" + TITOL + "\n" + MENU_PRINCIPAL);
        int opcioMenu = llegirInt("Escull una opció del menú: ", "ERROR: Opció de menú no vàlida", 1, 3);

        if (opcioMenu == 3)
        {
            System.out.println("\n" + "Apagant màquina...");
        }
        else
        {
            switch (opcioMenu)
            {
                case 1:
                    menuVeureCartellera(cartellera, horari);
                    break;
                case 2:
                    break;
            }

            menuPrincipal();
        }
    }

    private static void menuVeureCartellera(String[] cartellera, int[][] horari)
    {
        final String TITOL = "|||||||| VEURE  PELIS ||||||||";
        final String MENU_CARTELLERA = " 1. Veure cartellera \n" + " 2. Veure noms pel·lícules \n" + " 3. Buscar pel·lícula \n" + " 4. Buscar segons hora \n" + " 5. Tornar al inici";

        System.out.println("\n" + TITOL + "\n" + MENU_CARTELLERA);
        int opcioMenu = llegirInt("Escull una opció del menú: ", "ERROR: Opció de menú no vàlida", 1, 4);

        if (opcioMenu == 5)
        {
            System.out.println("\n" + "Tornant al menú principal...");
        }
        else
        {
            switch (opcioMenu)
            {
                case 1:
                    System.out.print("\n" + "||||||||| CARTELLERA |||||||||");
                    mostrarCartelleraHoraris(horari, cartellera);
                    break;
            }

            menuVeureCartellera(cartellera, horari);
        }
    }
    private static void cartelleraHorari(int[][] horari, String[] cartellera)
    {
        cartellera[0] = "El Gato con Botas: El último deseo";
        cartellera[1] = "Astérix y Obélix: El Reino Medio";
        cartellera[2] = "Llaman a la puerta ";
        cartellera[3] = "Todo a la vez en todas partes";
        cartellera[4] = "Avatar: El sentido del agua";
        cartellera[5] = "Los Fabelman";
        cartellera[6] = "Titanic (25 aniversario) ";
        cartellera[7] = "El piloto";
        cartellera[8] = "La ballena (The Whale)";
        cartellera[9] = "Babylon";
        cartellera[10] = "El asombroso Mauricio";
        cartellera[11] = "Operación Fortune: El gran engaño";


        horari[0][0] = 0;
        horari[0][1] = 14;
        horari[0][2] = 30;

        horari[1][0] = 1;
        horari[1][1] = 15;
        horari[1][2] = 30;
        horari[2][0] = 1;
        horari[2][1] = 20;
        horari[2][2] = 30;
        horari[3][0] = 1;
        horari[3][1] = 22;
        horari[3][2] = 15;
        horari[4][0] = 2;
        horari[4][1] = 15;
        horari[4][2] = 15;
        horari[5][0] = 3;
        horari[5][1] = 15;
        horari[5][2] = 15;
        horari[6][0] = 4;
    }
    private static void mostrarCartelleraHoraris(int[][] horari, String[] cartellera)
    {
        for (int a = 0; a < cartellera.length; a++)
        {
            System.out.print("\n" + cartellera[a] + " - ");

            for (int i = 0; i < horari.length; i++)
            {
                if (horari[i][0] == a)
                {
                    for (int j = 0; j < horari[j].length; j++)
                    {
                        if (horari[i][2] != 0)
                        {
                            System.out.print(horari[i][1] + ":" + horari[i][2] + " ");
                            break;
                        }
                    }
                }
            }

            System.out.println();
        }
    }
    private static int llegirInt(String missatge, String error, int min, int max)
    {
        Scanner input = new Scanner(System.in);

        boolean controlErrors = false;
        int valor = 0;

        do
        {
            System.out.print(missatge);
            controlErrors = input.hasNextInt();

            if (!controlErrors)
            {
                System.out.println(error);
            }
            else
            {
                valor = input.nextInt();

                if (valor < min || valor > max)
                {
                    System.out.println(error);
                    controlErrors = false;
                }
            }

            input.nextLine();
        } while (!controlErrors);

        return valor;
    }
    private static float llegirFloat(String missatge, String error, int min, int max)
    {
        Scanner input = new Scanner(System.in);

        boolean controlErrors = false;
        float valor = 0.00f;

        do
        {
            System.out.println(missatge);
            controlErrors = input.hasNextFloat();

            if (!controlErrors)
            {
                System.out.println(error);
            }
            else
            {
                valor = input.nextFloat();

                if (valor < min || valor > max)
                {
                    System.out.println(error);
                    controlErrors = false;
                }
            }

            input.nextLine();
        } while (!controlErrors);

        return valor;
    }
}
