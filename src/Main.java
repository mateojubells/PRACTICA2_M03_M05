import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * -- NOTES IMPORTANTS DE LA FUNCIONALITAT DEL PROGRAMA --
 * Hola, si estàs llegint això, quan es busca una pel·lícula s'ha de possar el seu
 * nom complet o el seu àlies, els àlies està format segons les parts més
 * representatives dels títols de les pel·lícules.
 *
 * @version 1.0
 * @author Francesc Barceló && Mateo Jubells
 */

public class Main
{
    /**
     * -- MAIN --
     * Codi on és declaren les matrius i vectors que és faràn servir en el programa,
     * També és crida al mètode omplirCartelleraHorari per inicalitzar els valors per defecte
     * de les matrius i vectors.
     * Després és crida al mètode recursiu del menú principal.
     *
     * @since 1.0
     * @author Francesc Barceló && Mateo Jubells
     */
    public static void main(String[] args)
    {
        String[] cartellera = new String[9];
        String[] aliasCartellera = new String[9];
        String[][] sales = new String [9][84];
        int[][] horari = new int[16][3];

        omplirCartelleraHorari(horari, sales, cartellera, aliasCartellera);

        menuPrincipal(horari, sales, cartellera, aliasCartellera);
    }

    /**
     * -- menuPrincipal --
     * En aquest mètode recursiu és on es mostra a l'usuari les funcions del menú
     * principal i se li demana que esculli una opcicó, en cas que l'opció sigui 3 el
     * programa s'apaga.
     * Si l'opció de l'usuari no és 3, s'executa un switch case en el que segons
     * l'opció que s'esculli s'executarà un mètode o un altre.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     *
     * @since 1.0
     * @author Francesc Barceló && Mateo Jubells
     */
    private static void menuPrincipal(int[][] horari, String[][] sales, String[] cartellera, String[] aliasCartellera)
    {

        final String TITOL = "||||||| CINES IMPERIAL |||||||"; // 30 Char
        final String MENU_PRINCIPAL = " 1. Veure cartellera \n" + " 2. Comprar entrades \n" + " 3. Sortir";

        System.out.println("\n" + TITOL + "\n" + MENU_PRINCIPAL);
        int opcioMenu = llegirInt("Escull una opció del menú: ", " ERROR: Opció de menú no vàlida", 1, 3);

        if (opcioMenu == 3)
        {
            System.out.println("\n" + "Apagant màquina...");
        }
        else
        {
            switch (opcioMenu) {
                case 1 -> menuVeureCartellera(horari, cartellera, aliasCartellera);
                case 2 -> comprarEntrades(horari, sales, cartellera, aliasCartellera);
            }

            menuPrincipal(horari, sales, cartellera, aliasCartellera);
        }
    }

    /**
     * -- menuVeureCartellera --
     * En aquest mètode se li mostren a l'usuari diferents opcions per mostrar la
     * cartellera, aquest mètode també és recursiu, ja que si l'usuari escull l'opció 5
     * torna al menú principal.
     * Si l'usuari escull una opció que no sigui la 5 es cridaran els mètodes segons
     * l'opció escollida.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     *
     * @since 1.0
     * @author Francesc Barceló && Mateo Jubells
     */
    private static void menuVeureCartellera(int[][] horari, String[] cartellera, String[] aliasCartellera)
    {
        final String TITOL = "|||||||| VEURE  PELIS ||||||||";
        final String MENU_CARTELLERA = " 1. Veure cartellera \n" + " 2. Veure noms pel·lícules \n" + " 3. Buscar pel·lícula \n" + " 4. Buscar per sessió \n" + " 5. Tornar al inici";

        System.out.println("\n" + TITOL + "\n" + MENU_CARTELLERA);

        int opcioMenu = llegirInt("Escull una opció del menú: ", " ERROR: Opció de menú no vàlida", 1, 5);

        if (opcioMenu == 5)
        {
            System.out.println("\n" + "Tornant al menú principal...");
        }
        else
        {
            switch (opcioMenu) {
                case 1 -> { /* -- VEURE CARTELLERA -- */
                    System.out.print("\n" + "||||||||| CARTELLERA |||||||||");
                    mostrarCartelleraHoraris(horari, cartellera);
                }
                case 2 -> { /* -- VEURE NOMS PEL·LÍCULES -- */
                    System.out.println("\n" + "||||| CARTELLERA (TÍTOL) |||||");
                    mostrarNomsPelicules(cartellera);
                }
                case 3 -> { /* -- BUSCAR PEL·LÍCULA -- */
                    System.out.println("\n" + "||||| BUSCAR  PEL·LÍCULA |||||");
                    mostrarNomsPelicules(cartellera);
                    buscarPelicula(horari, cartellera, aliasCartellera);
                }
                case 4 -> { /* -- BUSCAR PER SESSIÓ -- */
                    System.out.print("\n" + "||||| BUSCAR PER SESSIÓ ||||||");
                    buscarPerSesio(horari, cartellera);
                }
            }

            menuVeureCartellera(horari, cartellera, aliasCartellera);
        }
    }

    /**
     * -- mostrarCartelleraHoraris --
     * En aquest mètode es mostren els noms de les pel·lícules i els seus horaris.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     *
     * @since 1.0
     * @author Mateo Jubells
     */
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

    /**
     * -- mostrarNomsPelicules --
     * Mètode per només mostrar els noms de les pel·lícules.
     *
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void mostrarNomsPelicules(String[] cartellera)
    {
        for (String nomPelicula : cartellera)
        {
            System.out.println(" " + nomPelicula);
        }
    }

    /**
     * -- buscarPelicula --
     * Mètode on demana a l'usuari que escrigui el nom de la pel·lícula (després de que
     * es mostrin), un cop demanat es comprova que la pel·lícula està en cartellera, és
     * mostra per pantalla juntament amb el seu horari.
     * Si l'usuari escriu C/c es torna al menú anterior.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     *
     * @since 1.0
     * @author Mateo Jubells
     */
    private static void buscarPelicula(int[][] horari, String[] cartellera, String[] aliasCartellera){
        Scanner llegir = new Scanner(System.in);

        String nomPelicula;

        do
        {
            System.out.print("Escull una pel·lícula a buscar (C per sortir): ");
            nomPelicula = llegir.nextLine();
        } while (!comprovarPeliculaExisteix(cartellera, aliasCartellera, nomPelicula) && !Objects.equals(nomPelicula.toLowerCase(), "c"));

        int idPelicula = buscarIdPelicula(cartellera, aliasCartellera, nomPelicula);

        if (!Objects.equals(nomPelicula.toLowerCase(), "c"))
        {
            boolean numHorarisPelicules = numHorarisPelicules(horari, idPelicula);

            System.out.print("\n" + cartellera[idPelicula] + " - ");
            mostrarHorariPelicula(horari, idPelicula, numHorarisPelicules);
            System.out.println();
        }
    }

    /**
     * -- comprovarPeliculaExisteix --
     * Mètode per comprovar si la pel·lícula que ha demanat l'usuari està disponible o
     * no.
     *
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     * @param nomPelicula --> variable de tipus String amb el nom de la pel·lícula introduida per l'usuari
     *
     * @return si la variable nomPelicula existeix dintre de cartellera o de aliasCartellera (true)
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static boolean comprovarPeliculaExisteix(String[] cartellera, String[] aliasCartellera, String nomPelicula)
    {
        boolean comprovarPeliculaExisteix = false;

        for (int i = 0; i < cartellera.length; i++)
        {
            if (Objects.equals(cartellera[i].toLowerCase(), nomPelicula.toLowerCase()) || Objects.equals(aliasCartellera[i].toLowerCase(), nomPelicula.toLowerCase()))
            {
                comprovarPeliculaExisteix = true;

                break;
            }
        }

        return comprovarPeliculaExisteix;
    }

    /**
     * -- buscarPerSesio --
     * Mètode on es demana que l'usuari introdueixi una hora per mostrar les
     * pel·lícules que són posteriors l'horari introduit a l'usuari.
     * El mètode inclou un control d'errors per evitar que el programa peti en cas que
     * el format de l'hora no sigui correcte.
     * Un cop l'hora sigui correcte, es mostraran les pel·lícules disponibles amb
     * aquell horari.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     *
     * @since 1.0
     * @author Mateo Jubells
     */
    private static void buscarPerSesio(int[][] horari, String[] cartellera)
    {
        Scanner input = new Scanner(System.in);

        String horariComplet;
        boolean formatHorari = false;
        int hores = 0, minuts = 0;


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
                hores = convertirHores(horariComplet);
                minuts = convertirMinuts(horariComplet);

                if ((hores < 0 || hores > 24) || (minuts < 0 || minuts > 60 ))
                {
                    System.out.print(" ERROR: Format d'horari no vàlid");
                }
                else
                {
                    formatHorari = true;
                }
            }
        } while (!formatHorari);

        mostrarSessionsSegonsHora(horari, cartellera, hores, minuts);
    }

    /**
     * -- convertirHores --
     * Mètode per convertir el String horariComplet a hores.
     *
     * @param horariComplet --> variable de tipus String amb l'hora/minuts introduida per l'usuari
     * @return la hora de la variable horariComplet
     *
     * @since 1.0
     * @author Francesc
     */
    private static int convertirHores(String horariComplet)
    {
        int hora = 0;
        String aux = String.valueOf(horariComplet.charAt(0)) + String.valueOf(horariComplet.charAt(1));

        return hora = Integer.parseInt(aux);
    }

    /**
     * -- convertirMinuts --
     * Mètode per convertir el String horariComplet a minuts.
     *
     * @param horariComplet --> variable de tipus String amb l'hora/minuts introduida per l'usuari
     * @return els minuts de la variable horariComplet
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static int convertirMinuts(String horariComplet)
    {
        int minuts;

        String aux = String.valueOf(horariComplet.charAt(3)) + String.valueOf(horariComplet.charAt(4));

        return minuts = Integer.parseInt(aux);
    }

    /**
     * -- mostrarSessionsSegonsHora --
     * Mètode per mostrar els noms de les pel·lícules i les seves sessions que sigui de
     * la mateixa o posterior hora introduida per l'usuari.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param hores --> variable de tipus int amb la hora escollida per l'usuari
     * @param minuts --> variable de tipus int amb els minuts escollit per l'usuari
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void mostrarSessionsSegonsHora (int[][] horari, String[] cartellera, int hores, int minuts)
    {
        System.out.print("\n" + "|||| SESSIONS DISPONIBLES ||||");

        for (int a = 0; a < cartellera.length; a++)
        {
            for (int i = 0; i < horari.length; i++)
            {
                if (horari[i][0] == a)
                {
                    if (horari[i][1] == hores && horari[i][2] >= minuts)
                    {
                        if (i < (horari.length - 1))
                        {
                            if (horari[i][0] != horari[i + 1][0] && i > 1)
                            {
                                System.out.print("\n\n" + cartellera[a] + " - ");
                            }
                            else if (horari[i][0] != horari[i + 1][0])
                            {
                                System.out.print("\n" + cartellera[a] + " - ");
                            }
                        }

                        System.out.print(horari[i][1] + ":" + horari[i][2] + " ");
                    }
                    else if (horari[i][1] > hores )
                    {
                        if (i < (horari.length - 1))
                        {
                            if (horari[i][0] != horari[i + 1][0] && i > 1)
                            {
                                System.out.print("\n\n" + cartellera[a] + " - ");
                            }
                            else if (horari[i][0] != horari[i + 1][0])
                            {
                                System.out.print("\n" + cartellera[a] + " - ");
                            }
                        }

                        System.out.print(horari[i][1] + ":" + horari[i][2] + " ");
                    }
                }
            }
        }

        System.out.println();
    }

    /**
     * -- comprarEntrades --
     * Mètode on s'agrupen totes les funcions per poder comprar entrades.
     * Primer es demana a l'usuari que esculli una pel·lícula, després una butaca,
     * un cop estiguin totes les dades correctes (controls d'errors) és passarà al
     * pagament, pel pagament l'usuari pot escollit tres opcions, pagar amb targeat,
     * pagar amb metàl·lic o cancelar la compra.
     * En aquest mètode també es declaren les dues ArrayList (butaquesReservades i
     * filesResrvades).
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void comprarEntrades(int[][] horari, String[][] sales, String[] cartellera, String[] aliasCartellera)
    {
        ArrayList<Integer> butaquesResevades = new ArrayList<>();
        ArrayList<Integer> filesResevades = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        String nomPelicula, horariComplet = null;

        System.out.println("\n" + "|||||| COMPRAR ENTRADES ||||||");

        mostrarNomsPelicules(cartellera);

        do
        {
            System.out.print("Selecciona una pel·lícula (nom): ");
            nomPelicula = input.nextLine();

            if (!comprovarPeliculaExisteix(cartellera, aliasCartellera, nomPelicula))
            {
                System.out.println(" ERROR: Aquesta pel·lícula no existeix");
            }
        } while (!comprovarPeliculaExisteix(cartellera, aliasCartellera, nomPelicula));

        int idPelicula = buscarIdPelicula(cartellera, aliasCartellera, nomPelicula);
        boolean numHorarisPelicules = numHorarisPelicules(horari, idPelicula);

        // Horaris
        if (numHorarisPelicules)
        {
            System.out.print("\n" + "Horaris disponibles: ");
            mostrarHorariPelicula(horari, idPelicula, numHorarisPelicules);

            horariComplet = buscarHorari(horari, idPelicula, horariComplet);
        }
        else
        {
            System.out.print("\n" + "Únic horari disponible: ");
            mostrarHorariPelicula(horari, idPelicula, numHorarisPelicules);
            horariComplet = mostrarHorariUnic(horari, idPelicula, horariComplet);

            System.out.println();
        }

        // Escollir butaca
        int numButaquesLliures = 80 - contarButaquesLliures(sales, idPelicula);

        int numEntrades = llegirInt("Introdueix el número d'entrades (" + numButaquesLliures + " disponibles): ", " ERROR: Quantitat d'entrades no vàlid", 1, numButaquesLliures);

        escollirButaca(butaquesResevades, filesResevades, sales, numEntrades, idPelicula);

        // Pagar
        pagament(butaquesResevades, filesResevades, sales, cartellera, nomPelicula, horariComplet, idPelicula, numEntrades);
        posarButaquesOcupades(sales, idPelicula);
    }

    /**
     * -- buscarIdPelicula --
     * Mètode on es recorre el vector cartellera i aliasCartellera i en cas que la
     * variable nomPelicula es retornar la posició d'ella (i).
     *
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     * @param nomPelicula --> variable de tipus String amb el nom de le pel·lícula introduida per l'usuari
     * @return la id de la pel·lícula
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static int buscarIdPelicula(String[] cartellera, String[] aliasCartellera, String nomPelicula)
    {
        int idPelicula = 0;

        for (int i = 0; i < cartellera.length; i++)
        {
            if (Objects.equals(cartellera[i].toLowerCase(), nomPelicula.toLowerCase()) || Objects.equals(aliasCartellera[i].toLowerCase(), nomPelicula.toLowerCase()))
            {
                idPelicula = i;
                break;
            }
        }

        return idPelicula;
    }

    /**
     * -- numHorarisPelicules --
     * Mètode per calcular la quantitat d'horaris que té la pel·lícula, si en té més
     * d'un retornar true, sino, retorna false.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @return si la pel·lícula té més d'un horari (true) o no (false)
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static boolean numHorarisPelicules(int[][] horari, int idPelicula)
    {
        boolean numHorarisPelicules = false;
        int numHoraris = 0;

        for (int i = 0; i < horari.length; i++)
        {
            if (horari[i][0] == idPelicula)
            {
                numHoraris++;
            }
        }

        if (numHoraris > 1) { numHorarisPelicules = true; }

        return numHorarisPelicules;
    }

    /**
     * -- mostrarHorariPelicula --
     * Mètode per mostrar l'horari de la pel·lícula seleccionada per l'usuari,
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @param numHorarisPelicules --> variable de tipus boolean que si es true vol dir que té menys d'un horari
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void mostrarHorariPelicula(int[][] horari, int idPelicula, boolean numHorarisPelicules)
    {
        for (int i = 0; i < horari.length; i++)
        {
            for (int j = 0; j < horari[j].length; j++)
            {
                if (horari[i][0] == idPelicula)
                {
                    System.out.print(horari[i][1] + ":" + horari[i][2] + " ");

                    if (!numHorarisPelicules) { break; }

                    break;
                }
            }
        }
    }

    /**
     * -- buscarHorari --
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @param horariComplet --> variable de tipus String amb el horari introduit per l'usuari
     * @return l'horari que ha seleccionat l'usuari
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static String buscarHorari(int[][] horari, int idPelicula, String horariComplet)
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
                if(comprovarHorariExisteix(horari, idPelicula, horariComplet)) { formatHorari = true; }
                else { System.out.print(" ERROR: Horari no disponible per aquesta pel·lícula"); }
            }
        } while (!formatHorari);

        return horariComplet;
    }

    /**
     * -- comprovarHorariExisteix --
     * Mètode que retornar true o false segons si l'horari que ha escollit l'usuari
     * està disponible per la pel·lícula que ha seleccionat.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @param horariComplet --> variable de tipus String amb el horari introduit per l'usuari
     * @return si l'horari està disponible per la pel·lícula retorna true, sino, false
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static boolean comprovarHorariExisteix(int[][] horari, int idPelicula, String horariComplet)
    {
        boolean horariExisteix = false;

        int hora = convertirHores(horariComplet);
        int minuts = convertirMinuts(horariComplet);

        if (!(hora > 23 || hora < 0) || !(minuts > 59 || minuts < 0))
        {
            for (int i = 0; i < horari.length; i++)
            {
                if (horari[i][0] == idPelicula)
                {
                    for (int j = 0; j < horari[j].length; j++)
                    {
                        if (hora == horari[i][1] && minuts == horari[i][2])
                        {
                            horariExisteix = true;

                            break;
                        }
                    }
                }
            }
        }

        return horariExisteix;
    }

    /**
     * -- mostrarHorariUnic --
     * Mètode per mostrar els horaris de les pel·lícules que només tenen un horari
     * i agafar el seu valor.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @param horariComplet --> variable de tipus String amb l'horari introduit per l'usuari
     * @return l'unic horari disponible segons la pel·lícula seleccionada
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static String mostrarHorariUnic(int[][] horari, int idPelicula, String horariComplet)
    {
        for (int[] hora : horari)
        {
            if (hora[0] == idPelicula)
            {
                horariComplet = hora[1] + ":" + hora[2];

                break;
            }
        }

        return horariComplet;
    }

    /**
     * -- contarButaquesLliures --
     * Mètode per contar la quantitat de butaques que hi han disponibles en cada sala,
     * per aixi, poder limitar en el control d'errors el màxim d'entrades que un
     * usuari pot comprar.
     *
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @return el número de butaques lliures en cada sala.
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static int contarButaquesLliures(String[][] sales, int idPelicula)
    {
        int numButaques = 0;

        for (int i = 0; i < sales.length; i++)
        {
            if (i == idPelicula)
            {
                for (int j = 0; j < sales[i].length; j++)
                {
                    if (Objects.equals(sales[i][j], "--") || Objects.equals(sales[i][j], "XX"))
                    {
                        numButaques++;
                    }
                }

                break;
            }
        }

        return numButaques;
    }

    /**
     * -- escollirButaca --
     * Mètode per demanar a l'usauri que esculli quina butaca vol. Un cop escollida es
     * guarda en les ArrayList per després ser mostrades en l'entrada.
     *
     * @param butaquesResevades --> ArrayList amb les butaques comprades per l'usuari
     * @param filesResevades --> ArrayList amb les files de les butaques comprades per l'usuari
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param numEntrades --> variable de tipus int amb la quantitat d'entrades que l'usuari vol comprar
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void escollirButaca(ArrayList<Integer> butaquesResevades, ArrayList<Integer> filesResevades, String[][] sales, int numEntrades, int idPelicula)
    {
        int fila, butaca, auxButaca;

        do
        {
            mostrarSala(sales, idPelicula);

            System.out.println();

            do
            {
                fila = llegirInt("Escull un fila: ", " ERROR: Fila no vàlida", 1, 4);
                butaca = llegirInt("Escull una butaca: ", " ERROR: Butaca no vàlida", 1, 20);
                auxButaca = butaca;


                butaca = switch (fila) {
                    case 2 -> butaca + 21;
                    case 3 -> butaca + 42;
                    case 4 -> butaca + 63;
                    default -> butaca;
                };
            } while (!butacaLliure(sales, butaca, idPelicula));

            sales[idPelicula][butaca - 1] = "XX";

            butaquesResevades.add(auxButaca);
            filesResevades.add(fila);

            numEntrades--;
        } while (numEntrades > 0);
    }

    /**
     * -- mostrarSala --
     * Mètode per mostrar la sala de la pel·lícula seleccionada.
     *
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     */
    private static void mostrarSala(String[][] sales, int idPelicula)
    {
        final String RED = "\033[31m";
        final String GREEN = "\033[32m";
        final String BLUE = "\033[34m";
        final String YELLOW = "\033[33m";
        final String RESET = "\u001B[0m";

        int finalFila = 20, columna1 = 3, columna2 = 15;

        System.out.println("\n" + "||||||||||||||||||||||||||||||||||||||||||||||||||||| PANTALLA |||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|\t\t\t\t\t\t\t\t\t\t\t\t   BUTAQUES   \t\t\t\t\t\t\t\t\t\t\t\t" + "| FILA |");

        for (int i = 0; i < sales.length; i++)
        {
            if (i == idPelicula)
            {
                for (int j = 0; j < sales[i].length; j++)
                {
                    if (j == 0)
                    {
                        System.out.print("|");
                    }

                    if (j != finalFila)
                    {
                        if (Objects.equals(sales[i][j], "--"))
                        {
                            System.out.print(" " + RED + sales[i][j] + RESET + " |");
                        }
                        else if (Objects.equals(sales[i][j], "XX"))
                        {
                            System.out.print(" " + YELLOW + sales[i][j] + RESET + " |");
                        }
                        else
                        {
                            System.out.print(" " + GREEN + sales[i][j] + RESET + " |");
                        }
                    }
                    else
                    {
                        System.out.print("- " + BLUE + sales[i][j] + RESET + " -|");
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

        System.out.println();
    }

    /**
     * -- butacaLliure --
     * Mètode per comprovar si la butaca que l'usuari vols escollir està ocupada o no.
     *
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param butaca --> variable de tipus int amb la butaca seleccionada per l'usuari
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @return si la butaca està ocupada (true) o si no ho està (false)
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static boolean butacaLliure(String[][] sales, int butaca, int idPelicula)
    {
        boolean butacaLliure = true;

        if (Objects.equals(sales[idPelicula][butaca - 1], "--") || Objects.equals(sales[idPelicula][butaca - 1], "XX"))
        {
            butacaLliure = false;

            System.out.println(" ERROR: Aquesta butaca està ocupada");
        }

        return butacaLliure;
    }

    /**
     * -- pagament --
     * Mètode on s'engloba el codi per efectuar el pagament de les entrades
     * seleccionades, aquí podem trobar tres opcions, pagar amb targeta, pagar amb
     * metàl·lic o cancelar la compra.
     *
     * @param butaquesResevades --> ArrayList amb les butaques comprades per l'usuari
     * @param filesResevades --> ArrayList amb les files de les butaques comprades per l'usuari
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param nomPelicula --> variable de tipus String amb el nom de le pel·lícula introduida per l'usuari
     * @param horariComplet --> variable de tipus String amb l'horari introduit per l'usuari
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     * @param numEntrades --> variable de tipus int amb la quantitat d'entrades que l'usuari vol comprar
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void pagament(ArrayList<Integer> butaquesResevades, ArrayList<Integer> filesResevades, String[][] sales, String[] cartellera, String nomPelicula, String horariComplet, int idPelicula, int numEntrades)
    {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dia = DateTimeFormatter.ofPattern("E");
        String diaSetmana = myDateObj.format(dia);

        final String MENU_PAGAR = """
                1. Targeta\s
                2. Metàl·lic (màx. 50€)\s
                3. Cancelar compra""".indent(1);

        final float PREU_ENTRADA;

        if (Objects.equals(diaSetmana, "vie") || Objects.equals(diaSetmana, "sáb") || Objects.equals(diaSetmana, "dom")) { PREU_ENTRADA = 8.50f; }
        else { PREU_ENTRADA = 4.50f; }

        float preuCompra = PREU_ENTRADA * numEntrades;

        nomPelicula = cartellera[idPelicula];

        int numCaractersPelicula = (34 - nomPelicula.length()) / 2;

        System.out.println("\n" + "|||||||||| PAGAMENT ||||||||||" + "\n" + MENU_PAGAR);
        int opcioMenuPagar = llegirInt("Escull una opció: ", "ERROR: Opció de menú no vàlida", 1, 3);

        switch (opcioMenuPagar) {
            case 1 -> {
                pagamentTargeta(preuCompra);
                mostrarEntrades(butaquesResevades, filesResevades, PREU_ENTRADA, nomPelicula, horariComplet, numEntrades, numCaractersPelicula, idPelicula);
            }
            case 2 -> {
                pagamentMetalic(preuCompra);
                mostrarEntrades(butaquesResevades, filesResevades, PREU_ENTRADA, nomPelicula, horariComplet, numEntrades, numCaractersPelicula, idPelicula);
            }
            case 3 -> cancelarCompra(butaquesResevades, sales, idPelicula);
        }
    }

    /**
     * -- pagamentTargeta --
     * Mètode per pagar amb targeta, aquí es demana a l'usuari que apropi la targeta
     * (presionar ENTER) per efectuar la compra, també s'ha inclos un easteregg, que
     * en cas que un número aleatori sigui igual a 1, mostrarà un error de lectura de
     * la targeta-
     *
     * @param preuCompra --> variable de tipus float amb el preu de la compra de l'usauri
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void pagamentTargeta(float preuCompra)
    {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int errorPagament = 0;

        System.out.println("\n" + "|||| PAGAMENT AMB TARGETA ||||");
        System.out.printf("Preu total de la compra: %.2f€ \n", (preuCompra));
        System.out.println("Apropi la targeta al datàfon...");
        input.nextLine();

        do
        {
            errorPagament = random.nextInt((40 + 1) - 1) + 1;

            if (errorPagament == 1)
            {
                System.out.println(" ERROR: Lectura de targeta no vàlida...");
                System.out.println("Apropi la targeta al datàfon...");
                input.nextLine();
            }
            else
            {
                System.out.println("Pagament correcte, aquí té les entrades");
            }
        } while (errorPagament == 1);
    }

    /**
     * -- pagamentMetalic --
     * Mètode on s'engloba el codi perquè l'usuari pugui pagar amb metàl·lic,
     * aquí es demanarà a l'usuari que introdueixi diners fins que aquests,
     * superin el preu de la compra.
     *
     * @param preuCompra --> variable de tipus float amb el preu de la compra de l'usauri
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void pagamentMetalic(float preuCompra)
    {
        float importAcumulat = 0.00f;

        System.out.println("\n" + "||| PAGAMENT AMB METÀl·LIC |||");
        System.out.printf("Preu total de la compra: %.2f€ \n", preuCompra);

        do
        {
            float importClient = 0.00f;

            if (importAcumulat > 0.00f)
            {
                System.out.printf("Import actual, %.2f€, encara falta a pagar %.2f€ \n", importAcumulat, (preuCompra - importAcumulat));
            }

            do
            {
                importClient = llegirFloat("Introdueix el import (màx. 50€): ", " ERROR: Import no vàlid", 0, 50);
            } while (!comprovarImport(importClient));

            importAcumulat += importClient;
        } while (preuCompra > importAcumulat);

        if ((importAcumulat - preuCompra) > 0.00f)
        {
            System.out.printf("\nPagament correcte, no oblidi el canvi de %.2f€ \n", (importAcumulat - preuCompra));
        }
        else
        {
            System.out.println("Pagament correcte...");
        }
    }

    /**
     * -- comprovarImport --
     * Mètode control d'errors per comprovar que l'import que l'usuari a afegit sigui
     * una moneda/bitllet existent.
     *
     * @param importClient --> variable de tipus float amb l'import del usari
     * @return si la moneda/bitllet existeixin (true)
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static boolean comprovarImport(float importClient)
    {
        final float[] DINERS = {0.01f, 0.02f, 0.05f, 0.10f, 0.20f, 0.50f, 1.00f, 2.00f, 5.00f, 10.00f, 20.00f, 50.00f};

        boolean comprovarImport = false;

        for (float moneda : DINERS)
        {
            if (moneda == importClient)
            {
                comprovarImport = true;

                break;
            }
        }

        if (!comprovarImport)
        {
            System.out.println(" ERROR: Moneda/Bitllet no vàlid");
        }

        return comprovarImport;
    }

    /**
     * -- mostrarEntrades --
     * Mètode per mostrar les entrades del cinema comprades un cop finalitzada la
     * compra.
     *
     * @param butaquesResevades --> ArrayList amb les butaques comprades per l'usuari
     * @param filesResevades --> ArrayList amb les files de les butaques comprades per l'usuari
     * @param PREU_ENTRADA --> variable de tipus float amb el preu de l'entrada segons el dia de la setmana
     * @param nomPelicula --> variable de tipus String amb el nom de le pel·lícula introduida per l'usuari
     * @param horariComplet --> variable de tipus String amb l'horari introduit per l'usuari
     * @param numEntrades --> variable de tipus int amb la quantitat d'entrades que l'usuari vol comprar
     * @param numCaractersPelicula --> variable de tipus int amb el total de caràcters que té el nom de la pel·lícula
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void mostrarEntrades(ArrayList<Integer> butaquesResevades, ArrayList<Integer> filesResevades, float PREU_ENTRADA, String nomPelicula, String horariComplet, int numEntrades, int numCaractersPelicula, int idPelicula)
    {
        LocalDateTime myDateObj = LocalDateTime.now();
        Random random = new Random();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM. yyyy");
        DateTimeFormatter dia = DateTimeFormatter.ofPattern("E");

        String diaSetmana = myDateObj.format(dia);
        String dataCompra = myDateObj.format(myFormatObj);

        nomPelicula += " ".repeat(Math.max(0, numCaractersPelicula));

        do
        {
            int codiCompra = random.nextInt((999999999 + 1) - 100000000) + 100000000;

            System.out.println();

            // Mostrar capçalera
            System.out.println("|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|");
            System.out.println("||||||||||| CINES IMPERIAL |||||||||||");
            // System.out.println("|____________________________________|");
            System.out.println("|                                    |");

            // Mostrar nom pel·lícula
            System.out.print("| ");
            for (int i = 0; i < numCaractersPelicula; i++) { System.out.print(" "); }
            System.out.println(nomPelicula.toUpperCase() + " |");

            System.out.println("|                                    |");

            // Mostrar sessió i sala
            System.out.println("| SESSIÓ: " + horariComplet + "              SALA: " + (idPelicula + 1) + " |");

            // Mostrar fila i butaca
            if (butaquesResevades.get(numEntrades - 1) < 10)
            {
                System.out.println("| FILA: 0" + filesResevades.get(numEntrades - 1) + "                BUTACA: 0" + butaquesResevades.get(numEntrades - 1) + " |");
            }
            else
            {
                System.out.println("| FILA: 0" + filesResevades.get(numEntrades - 1) + "                BUTACA: " + butaquesResevades.get(numEntrades - 1) + " |");
            }

            System.out.println("|                                    |");

            // Mostrar dades extres
            System.out.printf("|     %.2f€   I.V.A.: 10%% inclòs     | \n", PREU_ENTRADA);

            if (Objects.equals(diaSetmana, "vie") || Objects.equals(diaSetmana, "sáb") || Objects.equals(diaSetmana, "dom"))
            { System.out.println("|       CAP DE SETMANA/FESTIUS       |"); }
            else
            { System.out.println("|              LABORABLES            |"); }

            System.out.println("| " + dataCompra + "     R.E.F.: " +  codiCompra + " |");
            System.out.println("|____________________________________|");

            numEntrades--;
        } while (numEntrades > 0);
    }

    /**
     * -- cancelarCompra --
     * Mètode per cancelar la compra que ha fet el client, serveix per desbloquejar
     * les cadires que l'usuari habia seleccionat.
     *
     * @param butaquesResevades --> ArrayList amb les butaques comprades per l'usuari
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void cancelarCompra(ArrayList<Integer> butaquesResevades, String[][] sales, int idPelicula)
    {
        int posicioEntrada = 0;

        for (int i = 0; i < sales.length; i++)
        {
            if (i == idPelicula)
            {
                for (int j = 0; j < sales[i].length; j++)
                {
                    if (Objects.equals(sales[i][j], "XX"))
                    {
                        sales[i][j] = "0" + butaquesResevades.get(posicioEntrada++);
                    }
                }

                break;
            }
        }

        System.out.println("\n" + "Compra cancelada...");
    }

    /**
     * -- posarButaquesOcupades --
     * Mètode perquè un cop acabada la compra, les butaques que l'usuari a reservat
     * passin d'estar de lliures (0X o XX) a estar ocupades (--).
     *
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param idPelicula --> variable de tipus int amb la id de la pel·lícula
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void posarButaquesOcupades(String[][] sales, int idPelicula)
    {
        for (int i = 0; i < sales.length; i++)
        {
            if (i == idPelicula)
            {
                for (int j = 0; j < sales[i].length; j++)
                {
                    if (Objects.equals(sales[i][j], "XX"))
                    {
                        sales[i][j] = "--";
                    }
                }

                break;
            }
        }
    }

    /* private static void buscarPerSesio(int[][] horari, String[] cartellera)
    {
        Scanner llegir = new Scanner(System.in);
        boolean horaExisteix=false;
        boolean trobat = false;
        String hora="";
        int hores=0;
        int minuts=0;
        int posicio=0;
        int peli=0;
        int imprimirPosicio=0;
        System.out.println("Introdueix la hora que vols anar");
        hora = llegir.nextLine();
        if (hora.substring(0, 1).matches("[0-9]")&&hora.substring(3, 4).matches("[0-9]")){
        hores = convertirHores(hora);
        minuts = convertirMinuts(hora);
        if (!(hores > 23 || hores < 0) || !(minuts > 59 || minuts < 0))
        {
            for (int a = 0; a < cartellera.length; a++)
            {
                for (int i = 0; i < horari.length; i++)
                {
                    for (int j = 0; j < horari[j].length; j++)
                    {
                        if (horari[i][0] == a)
                        {
                            if (hores == horari[i][1] && minuts == horari[i][2])
                            {
                                horaExisteix = true;
                                posicio = i;
                            }
                        }
                    }
                }
            }
        }
        if (horaExisteix == true){
            for (int i = 0; i <16; i++) {
                trobat = false;
                for (int j = 0; j < 3; j++) {
                    if (horari[i][1]>hores){
                        peli = horari[i][0];
                        trobat= true;
                        imprimirPosicio=i;
                    } else if (horari[i][1]==hores&&horari[i][2]>=minuts) {
                        peli = horari[i][0];
                        trobat= true;
                        imprimirPosicio=i;
                    }
                }
                if (trobat == true){
                    System.out.println(cartellera[peli]);
                    System.out.println(horari[imprimirPosicio][1]+":"+ horari[imprimirPosicio][2]);
                    System.out.println("");
                }
            }
            System.out.println();
        }
    }else{
            System.out.println("Valor no valid");
            hora = "";
            buscarPerSesio(horari, cartellera);
        }
    }
     */

    /**
     * -- omplirCartelleraHorari --
     * Mètode per donar valor al vector i matrius del programa.
     *
     * @param horari --> matriu de tipus int amb l'horari de les pel·lícules
     * @param sales --> matriu de tipus int amb la distribució de les sales
     * @param cartellera --> vector de tipus String amb els noms de les pel·lícules
     * @param aliasCartellera --> vector de tipus String amb els àlies de les pel·lícules
     *
     * @since 1.0
     * @author Francesc Barceló
     */
    private static void omplirCartelleraHorari(int[][] horari, String[][] sales, String[] cartellera, String[] aliasCartellera)
    {
        cartellera[0] = "Till, el crimen que lo cambió todo";
        aliasCartellera[0] = "Till";

        horari[0][0] = 0;
        horari[0][1] = 19;
        horari[0][2] = 30;

        cartellera[1] = "El piloto (DolbyAtmos)";
        aliasCartellera[1] = "El piloto";

        horari[1][0] = 1;
        horari[1][1] = 18;
        horari[1][2] = 30;
        horari[2][0] = 1;
        horari[2][1] = 20;
        horari[2][2] = 30;
        horari[3][0] = 1;
        horari[3][1] = 22;
        horari[3][2] = 15;

        cartellera[2] = "Llaman a la puerta";
        aliasCartellera[2] = "Llaman a la puerta";

        horari[4][0] = 2;
        horari[4][1] = 21;
        horari[4][2] = 15;

        cartellera[3] = "Momias";
        aliasCartellera[3] = "Momias";

        horari[5][0] = 3;
        horari[5][1] = 16;
        horari[5][2] = 30;

        cartellera[4] = "Avatar: El sentido del agua (3D)";
        aliasCartellera[4] = "Avatar";

        horari[6][0] = 4;
        horari[6][1] = 17;
        horari[6][2] = 15;
        horari[7][0] = 4;
        horari[7][1] = 19;
        horari[7][2] = 45;

        cartellera[5] = "Los Fabelman";
        aliasCartellera[5] = "Los Fabelman";

        horari[8][0] = 5;
        horari[8][1] = 19;
        horari[8][2] = 15;
        horari[9][0] = 5;
        horari[9][1] = 22;
        horari[9][2] = 15;

        cartellera[6] = "Titanic (25 aniversario)";
        aliasCartellera[6] = "Titanic";

        horari[10][0] = 6;
        horari[10][1] = 18;
        horari[10][2] = 15;
        horari[11][0] = 6;
        horari[11][1] = 20;
        horari[11][2] = 45;

        cartellera[7] = "Almas en pena de Inisherin";
        aliasCartellera[7] = "Almas en pena";

        horari[12][0] = 7;
        horari[12][1] = 18;
        horari[12][2] = 15;

        cartellera[8] = "La ballena (The Whale)";
        aliasCartellera[8] = "La ballena";

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
        for (int i = 0; i < sales.length; i++)
        {
            int numFila = 0;

            for (int j = 0; j < sales[i].length; j++)
            {
                if (j == 20 || j == 41 || j == 62 || j == 83)
                {
                    sales[i][j] = "0" + (++numFila);
                }
            }
        }
    }

    /**
     * -- llegirInt --
     * Mètode estandaritzat per poder llegir un int i aplicar-li un control d'errors
     * per evitar que peti al introduir un valor que no sigui int, i, que tampoc
     * tingui un valor fora del llindar.
     *
     * @param missatge --> variable de tipus String amb el missatge que es vol mostrar per pantalla
     * @param error --> varibale de tipus String amb el missatge d'error que es vol mostrar per pantalla
     * @param min --> variable de tipus int amb el valor minim del llindar de valors
     * @param max --> variable de tipus int amb el valor màxim del llindar de valors
     * @return un valor int dintre del llindar de valor dessitjat
     *
     * @since 1.0
     * @author Francesc Barceló && Mateo Jubells
     */
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

    /**
     * -- llegirFloat --
     * Mètode estandaritzat per poder llegir un float i aplicar-li un control d'errors
     * per evitar que peti al introduir un valor que no sigui float, i, que tampoc
     * tingui un valor fora del llindar.
     *
     * @param missatge --> variable de tipus String amb el missatge que es vol mostrar per pantalla
     * @param error --> varibale de tipus String amb el missatge d'error que es vol mostrar per pantalla
     * @param min --> variable de tipus int amb el valor minim del llindar de valors
     * @param max --> variable de tipus int amb el valor màxim del llindar de valors
     * @return un valor float dintre del llindar de valor dessitjat
     *
     * @since 1.0
     * @author Francesc Barceló && Mateo Jubells
     */
    private static float llegirFloat(String missatge, String error, int min, int max)
    {
        Scanner input = new Scanner(System.in);

        boolean controlErrors = false;
        float valor = 0.00f;

        do
        {
            System.out.print(missatge);
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