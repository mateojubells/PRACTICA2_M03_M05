import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        menuPrincipal();
    }
    private static void menuPrincipal()
    {
        final String MENU_PRINCIPAL = " 1. Veure cartellera \n 2. Comprar entrades \n 3. Sortir";

        System.out.println(MENU_PRINCIPAL);
        int opcioMenuPrincipal = llegirInt("Escull una opció del menú: ", "ERROR: Opció de menú no vàlida", 1, 3);

        if (opcioMenuPrincipal == 3)
        {
            System.out.println("\n" + "Apagant màquina...");
        }
        else
        {
            switch (opcioMenuPrincipal)
            {
                case 1:
                    System.out.println("Hola");
                    break;
            }

            menuPrincipal();
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
}