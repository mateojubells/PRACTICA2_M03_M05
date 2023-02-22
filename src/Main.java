import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        menuPrincipal();
    }
    private static void menuPrincipal()
    {
        final String TITOL = "||||||| CINES IMPERIAL |||||||"; // 30 Char
        final String MENU_PRINCIPAL = " 1. Veure cartellera \n" + " 2. Comprar entrades \n" + " 3. Sortir";

        String[] cartellera = new String[9];
        String[][] sales = new String [9][84];
        int[][] horari = new int[16][3];
        int[][] entrades = new int[10][2];

        // Mètode per omplir la matriu i el vector
        omplirCartelleraHorari(horari, sales, cartellera);

        System.out.println("\n" + TITOL + "\n" + MENU_PRINCIPAL);
        int opcioMenu = llegirInt("Escull una opció del menú: ", " ERROR: Opció de menú no vàlida", 1, 3);

        if (opcioMenu == 3)
        {
            System.out.println("\n" + "Apagant màquina...");
        }
        else
        {
            switch (opcioMenu)
            {
                case 1:
                    menuVeureCartellera(horari, cartellera);
                    break;
                case 2:
                    comprarEntrades(horari, sales, entrades, cartellera);
                    break;
            }

            menuPrincipal();
        }
    }
    private static void menuVeureCartellera(int[][] horari, String[] cartellera)
    {
        final String TITOL = "|||||||| VEURE  PELIS ||||||||";
        final String MENU_CARTELLERA = " 1. Veure cartellera \n" + " 2. Veure noms pel·lícules \n" + " 3. Buscar pel·lícula \n" + " 4. Buscar segons hora \n" + " 5. Tornar al inici";

        System.out.println("\n" + TITOL + "\n" + MENU_CARTELLERA);

        int opcioMenu = llegirInt("Escull una opció del menú: ", " ERROR: Opció de menú no vàlida", 1, 5);

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
                case 2:
                    break;
                case 3:
                    System.out.println("\n"+ "||||||||| Pelicula |||||||||");
                    String nomPelicula = buscarPelicula(cartellera, horari,"Escull una pelicula a buscar "+"\n (Per sortir escrigui exit): ");
                    for (int a = 0; a < cartellera.length; a++) {
                        if (Objects.equals(nomPelicula,cartellera[a]))
                        {

                        }
                    }
                    break;
            }

            menuVeureCartellera(horari, cartellera);
        }
    }
    private static String buscarPelicula(String[] cartellera, int[][] horari, String missatge){
        Scanner llegir = new Scanner(System.in);
        boolean trobat= false;
        int posicio=0;

        System.out.print(missatge);
        String nomPelicula = llegir.nextLine();
        if (Objects.equals(nomPelicula.toLowerCase(),"exit")){
            System.out.println("Sortint...");
        }else {
            for (int i =0; i<cartellera.length; i++){
                if (Objects.equals(cartellera[i].toLowerCase(), nomPelicula.toLowerCase())){
                    trobat =true;
                    posicio = i;
                }}
            if (trobat==true){
                System.out.print(cartellera[posicio]);
                System.out.print(": esta disponible!!");
                mostrarHorariPelicula(cartellera, horari, nomPelicula);

            }else {
                System.out.println("Pelicula no trobada, torna a intentar");
                buscarPelicula(cartellera, horari, missatge);
            }
        }


        return nomPelicula;

    }
    private static void comprarEntrades(int[][] horari, String[][] sales, int[][] entrades, String[] cartellera)
    {
        Scanner input = new Scanner(System.in);

        String nomPelicula, horariComplet = null;

        System.out.println("\n" + "|||||| COMPRAR ENTRADES ||||||");

        mostrarNomsPelicules(cartellera);

        do
        {
            System.out.print("Selecciona una pel·lícula (nom): ");
            nomPelicula = input.nextLine();

            if (!comprovarPeliculaExisteix(cartellera, nomPelicula))
            {
                System.out.println(" ERROR: Aquesta pel·lícula no existeix");
            }
        } while (!comprovarPeliculaExisteix(cartellera, nomPelicula));

        mostrarHorariPelicula(cartellera, horari, nomPelicula);

        buscarHorari(horari, cartellera, nomPelicula, horariComplet);

        int numButaquesLliures = 80 - contarButaquesLliures(sales, cartellera, nomPelicula);

        int numEntrades = llegirInt("Introdueix el número d'entrades (" + numButaquesLliures + " disponibles): ", " ERROR: Quantitat d'entrades no vàlid", 1, numButaquesLliures);

        escollirButaca(sales, entrades, cartellera, nomPelicula, numEntrades);
    }
    private static void buscarHorari(int[][] horari, String[] cartellera, String nomPelicula, String horariComplet)
    {
        Scanner input = new Scanner(System.in);

        boolean formatHorari = false;

        do
        {
            System.out.print("\n" + "Escull una sessió (HH:MM): ");
            horariComplet = input.nextLine();

            if (horariComplet.length() != 5) // Comprovar que l'hora no tingui menys/més de 5 caràcters
            {
                System.out.print(" ERROR: Format d'horari no vàlid");
            }
            else if (!Objects.equals(horariComplet.charAt(2), ':')) // Per comparar amb el char.At() s'ha de fer amb ''
            {
                System.out.print(" ERROR: Format d'horari no vàlid");
            }
            else
            {
                if(comprovarHorariExisteix(horari, cartellera, horariComplet, nomPelicula)) { formatHorari = true; }
                else { System.out.print(" ERROR: Horari no disponible per aquesta pel·lícula"); }
            }
        } while (!formatHorari);
    }
    private static int contarButaquesLliures(String[][] sales, String[] cartellera, String nomPelicula)
    {
        int numButaques = 0;

        for (int a = 0; a < cartellera.length; a++)
        {
            if (Objects.equals(cartellera[a].toLowerCase(), nomPelicula.toLowerCase()))
            {
                for (int i = 0; i < sales.length; i++)
                {
                    for (int j = 0; j < sales[i].length; j++)
                    {
                        if (Objects.equals(sales[i][j], "--"))
                        {
                            numButaques++;
                        }
                    }

                    break;
                }

                break;
            }
        }

        return numButaques;
    }
    private static void escollirButaca(String[][] sales, int[][] entrades, String[] cartellera, String nomPelicula, int numEntrades)
    {
        // TODO: Fer el tema de comprovar que la butaca no estigui buida

        boolean butacaLliure = false;
        int butaca = 0;

        do
        {
            mostrarSala(sales, cartellera, nomPelicula);
            // numEntrades--;
            butacaLliure = true;
        } while (!butacaLliure /* || numEntrades > 0 */);
    }
    private static void mostrarSala(String[][] sales, String[] cartellera, String nomPelicula)
    {
        int finalFila = 20, columna1 = 3, columna2 = 15;

        System.out.println("\n" + "||||||||||||||||||||||||||||||||||||||||||||||||||||| PANTALLA |||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "| FILA |");

        for (int a = 0; a < cartellera.length; a++)
        {
            if (Objects.equals(cartellera[a].toLowerCase(), nomPelicula.toLowerCase()))
            {
                for (int i = 0; i < sales.length; i++)
                {
                    for (int j = 0; j < sales[i].length; j++)
                    {
                        if (j == 0)
                        {
                            System.out.print("|");
                        }

                        if (j != finalFila)
                        {
                            System.out.print(" " + sales[i][j] + " |");
                        }
                        else
                        {
                            System.out.print("- " + sales[i][j] + " -|");
                        }

                        if (j == finalFila && j != (sales[i].length - 1))
                        {
                            System.out.print("\n" + "|");
                            finalFila += 21;
                        }
                        else if (j == columna1)
                        {
                            System.out.print("   |");
                            columna1 += 21;
                        }
                        else if (j == columna2)
                        {
                            System.out.print("   |");
                            columna2 += 21;
                        }
                    }

                    break;
                }
            }
        }

        System.out.println();
    }
    private static boolean comprovarHorariExisteix(int[][] horari, String[] cartellera, String horariComplet, String nomPelicula)
    {
        boolean horariExisteix = false;

        int hora = convertirHores(horariComplet);
        int minuts = convertirMinuts(horariComplet);

        if (!(hora > 23 || hora < 0) || !(minuts > 59 || minuts < 0))
        {
            for (int a = 0; a < cartellera.length; a++)
            {
                if (Objects.equals(cartellera[a].toLowerCase(), nomPelicula.toLowerCase()))
                {
                    for (int i = 0; i < horari.length; i++)
                    {
                        for (int j = 0; j < horari[j].length; j++)
                        {
                            if (horari[i][0] == a)
                            {
                                if (hora == horari[i][1] && minuts == horari[i][2])
                                {
                                    horariExisteix = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return horariExisteix;
    }
    private static int convertirMinuts(String horariComplet)
    {
        int minuts;

        String aux = String.valueOf(horariComplet.charAt(3)) + String.valueOf(horariComplet.charAt(4));

        return minuts = Integer.parseInt(aux);
    }
    private static int convertirHores(String horariComplet)
    {
        int hora = 0;
        String aux = String.valueOf(horariComplet.charAt(0)) + String.valueOf(horariComplet.charAt(1));

        return hora = Integer.parseInt(aux);
    }
    private static void mostrarHorariPelicula(String[] cartellera, int[][] horari, String nomPelicula)
    {
        for (int a = 0; a < cartellera.length; a++)
        {
            if (Objects.equals(cartellera[a].toLowerCase(), nomPelicula.toLowerCase()))
            {
                System.out.print("\n" + "Horaris disponibles: ");

                for (int i = 0; i < horari.length; i++)
                {
                    for (int j = 0; j < horari[j].length; j++)
                    {
                        if (horari[i][0] == a)
                        {
                            System.out.print(horari[i][1] + ":" + horari[i][2] + " ");

                            break;
                        }
                    }
                }

                break;
            }
        }
    }
    private static boolean comprovarPeliculaExisteix(String[] cartellera, String nomPelicula)
    {
        boolean comprovarPeliculaExisteix = false;

        for (int i = 0; i < cartellera.length; i++)
        {
            if (Objects.equals(cartellera[i].toLowerCase(), nomPelicula.toLowerCase()))
            {
                comprovarPeliculaExisteix = true;

                break;
            }
        }

        return comprovarPeliculaExisteix;
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
    private static void mostrarNomsPelicules(String[] cartellera)
    {
        for (String nomPelicula : cartellera)
        {
            System.out.println(" " + nomPelicula);
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
    private static void omplirCartelleraHorari(int[][] horari, String[][] sales, String[] cartellera)
    {
        cartellera[0] = "El Gato con Botas: El último deseo";

        horari[0][0] = 0;
        horari[0][1] = 14;
        horari[0][2] = 30;

        cartellera[1] = "Astérix y Obélix: El Reino Medio";

        horari[1][0] = 1;
        horari[1][1] = 15;
        horari[1][2] = 30;
        horari[2][0] = 1;
        horari[2][1] = 20;
        horari[2][2] = 30;
        horari[3][0] = 1;
        horari[3][1] = 22;
        horari[3][2] = 15;

        cartellera[2] = "Llaman a la puerta";

        horari[4][0] = 2;
        horari[4][1] = 15;
        horari[4][2] = 15;

        cartellera[3] = "Todo a la vez en todas partes";

        horari[5][0] = 3;
        horari[5][1] = 15;
        horari[5][2] = 15;

        cartellera[4] = "Avatar: El sentido del agua";

        horari[6][0] = 4;
        horari[6][1] = 17;
        horari[6][2] = 30;
        horari[7][0] = 4;
        horari[7][1] = 19;
        horari[7][2] = 45;

        cartellera[5] = "Los Fabelman";

        horari[8][0] = 5;
        horari[8][1] = 15;
        horari[8][2] = 15;
        horari[9][0] = 5;
        horari[9][1] = 22;
        horari[9][2] = 15;

        cartellera[6] = "Titanic (25 aniversario) ";

        horari[10][0] = 6;
        horari[10][1] = 10;
        horari[10][2] = 15;
        horari[11][0] = 6;
        horari[11][1] = 12;
        horari[11][2] = 45;

        cartellera[7] = "El piloto";

        horari[12][0] = 7;
        horari[12][1] = 18;
        horari[12][2] = 15;

        cartellera[8] = "La ballena (The Whale)";

        horari[13][0] = 8;
        horari[13][1] = 17;
        horari[13][2] = 30;
        horari[14][0] = 8;
        horari[14][1] = 19;
        horari[14][2] = 45;
        horari[15][0] = 8;
        horari[15][1] = 22;
        horari[15][2] = 15;

        // Omplir sales amb número de butaques
        int butaca = 0;

        for (int i = 0; i < sales.length; i++)
        {
            for (int j = 0; j < sales[i].length; j++) // S'ha de posar la i :(
            {
                if (butaca < 9)
                {
                    sales[i][j] = "0" + (++butaca);
                }
                else
                {
                    sales[i][j] = "" + (++butaca);
                }

                if (butaca == 21)
                {
                    butaca = 0;
                }
            }
        }

        // Possar algunes butaques plenes
        Random rand = new Random();

        for (int i = 0; i < sales.length; i++)
        {
            int contador = 0;

            for (int j = 0; j < sales[i].length; j++)
            {
                if (contador != 25)
                {
                    int posicioRandom = rand.nextInt((49) + 1);
                    contador++;
                    sales[i][posicioRandom] = "--";
                }
            }
        }

        // Possar num a la fila
        int numFila = 0, fila = 20;

        for (int i = 0; i < sales.length; i++)
        {
            for (int j = 0; j < sales[i].length; j++)
            {
                if (j == fila)
                {
                    sales[i][j] = "0" + (++numFila);

                    fila += 21;
                }
            }
        }
    }
}
