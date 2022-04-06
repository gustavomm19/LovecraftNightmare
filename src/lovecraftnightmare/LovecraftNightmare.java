package lovecraftnightmare;

import java.util.Scanner;
public class LovecraftNightmare {

   public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    
    //declarar variables
    
    String sig, nombre, respSexo, movimiento = null;
    
    
    boolean sexo;
    
    
    
    //Presentacion
        
        do{
        System.out.println("\t~~~Lovecraft Nightmare~~~");
        System.out.println("El eterno y oscuro dios Cthulhu ha despertado de su sueño para "
                + "\ntraer mil años de oscuridad. Sólo tú puedes enfrentarte a su ejército "
                + "\nde monstruos y salvar al mundo.");
        System.out.println("");
        
        
        //Creando el heroe
        System.out.println("Ingrese el sexo de su personaje: \tHombre(h) \tMujer(m)");
        respSexo=sc.next().toUpperCase();
        while(!(respSexo.equals("H")||respSexo.equals("M"))){
            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \tHombre(h) \tMujer(m)");
        respSexo=sc.next().toUpperCase();
        }
        if(respSexo.equals("H")){sexo=true;}else{sexo=false;}
        System.out.println("Ingrese el nombre de su personaje");
        nombre=sc.next();
    
        
        
        //mostrar las instrucciones
        System.out.println("\tINSTRUCCIONES");
        System.out.println("Para moverse utilice los sig. comandos:\nW:Arriba \nS:Abajo \nA:Izquierda \nD:Derecha");
        System.out.println("");
        System.out.println("Si intentas moverte fuera del mapa, tu personaje se quedará en la misma posicion.");
        System.out.println("Si te mueves hacia la posicion de un monstruo, iniciara el combata hasta que solo uno de los dos quede en pie.");
        System.out.println("Tu personaje será representado por los sig caracteres: (7_7)");
        System.out.println("Los monstruos serán representados por los sig caracteres: (~°o°)~");
        System.out.println("Solo podras ver los monstruos que esten a 3 o menos pasos de ti.");
        System.out.println("");
        System.out.println("Iniciaras con un bolso vacio con capacidad para 50 pociones, que podras obtener derrotando monstruos.");
        System.out.println("Iniciaras sin arma, escudo o armadura. Podras obtenerlos derrotando monstruos.");
        System.out.println("Derrotar un monstruo no garantiza que obtengas algun objeto.");
        System.out.println("Una vez que derrotes a todos los mostruos en un mapa, se generará un nuevo mapa de mayor tamaño, hasta alcanzar un maximo de 10x10");
        System.out.println("Presione 'ENTER' para comenzar a jugar");
        sig=sc.nextLine();
        sig=sc.nextLine();
        
        //Crear al personaje del usuario
        int kills=0;
        int x=0;
        int y=0;     
        int tamano=1;
        int nivel=1;
        int vida=100;
        int maxVida=100;
        int fuerza=1;
        int defensa=1;
        int contMapa=0;
        boolean estadoDeDefensa=false;
        
        int[] bolso= new int[50];
        for(int i=0;i<bolso.length;i++){
            bolso[i]=0;
        }
        
        Arma armaNula= new Arma(0, 0, "Ningún arma equipada");
        Armadura armaduraNula= new Armadura(0, 0, "Ningún armadura equipada");
        Escudo escudoNulo= new Escudo(0, 0, "Ningún escudo equipado");
        
        Heroe heroe=new Heroe(nivel, vida, fuerza, defensa,nombre, sexo, bolso, armaNula, armaduraNula, escudoNulo, estadoDeDefensa);
        //iniciar contador del tiempo
        long inicio=0;
        inicio=System.nanoTime();
        //inicio del juego
        //generar un nuevo mapa
        while(heroe.vivo()){
        if (tamano<10){tamano +=1;}
        x=0;
        y=0;
        //crear mapa
        int[][] mapa=new int[tamano][tamano];
        //ubicar heroe en el mapa
        mapa[x][y]=1;
        contMapa+=1;
        
        //ubicar monstruos en el mapa
        ubicarMonstruos(mapa);
        
        while(!hayMonstruos(mapa)){
            ubicarMonstruos(mapa);
        }
        
        
        //jugador puede moverse en el mapa
        
        while(heroe.vivo()&& hayMonstruos(mapa)){
            System.out.println("");
            System.out.println("Mapa: "+contMapa+"\tMonstruos derrotados: "+kills);
            System.out.println(heroe.getNombre()+"\tNivel: "+heroe.getNivel());
            System.out.println("Vida: "+heroe.getVida() +" de "+heroe.getMaxVida()+"\tFuerza: "+heroe.getFuerza()+"\tDefensa: "+heroe.getDefensa());
            System.out.println("Experiencia: "+heroe.getExperiencia()+" de "+((int) Math.pow((2*heroe.getNivel()),2)));
            imprimirMapa(mapa, x, y);
        System.out.println("Hacia donde se quiere mover");
        movimiento=sc.next().toUpperCase();
        switch(movimiento){
            case "D":
                //Validar que la posicion exista dentro del mapa
                if(y+1>=0 && y+1<mapa[x].length){
                    //verificar que no haya un monstruo en la posicion a la que te mueves
                    if (!(mapa[x][y + 1] == 2)) {
                        mapa[x][y] = 0;
                        y = y + 1;
                        mapa[x][y] = 1;
                    }
                    //caso en el que hay un monstruo en la posicion a la que te mueves
                    else if(mapa[x][y+1]==2){
                    combate(heroe, kills, bolso);
                        if (heroe.vivo()) {
                            mapa[x][y] = 0;
                            y = y + 1;
                            mapa[x][y] = 1;
                            kills += 1;
                        }
                    }
                }    
                break;
            case"A":
                if(y-1>=0 && y-1<=mapa[x].length){
                    if(!(mapa[x][y-1]==2)){
                    mapa[x][y]=0;
                    y=y-1;
                    mapa[x][y]=1;
                    }else if (mapa[x][y-1]==2){
                    combate(heroe, kills, bolso);
                        if (heroe.vivo()) {
                            mapa[x][y] = 0;
                            y = y - 1;
                            mapa[x][y] = 1;
                            kills += 1;
                        }
                    }
                }
                break;
            case "W":
                if(x-1>=0 && x-1<=mapa.length){
                    if(!(mapa[x-1][y]==2)){
                mapa[x][y]=0;
                x=x-1;
                mapa[x][y]=1;
                    }else if(mapa[x-1][y]==2){
                    combate(heroe, kills, bolso);
                        if (heroe.vivo()) {
                            mapa[x][y] = 0;
                            x = x - 1;
                            mapa[x][y] = 1;
                            kills += 1;
                        }
                    }
                }
                break; 
            case "S":
                if(x+1>=0 && x+1<mapa.length){
                    if (!(mapa[x + 1][y] == 2)) {
                        mapa[x][y] = 0;
                        x = x + 1;
                        mapa[x][y] = 1;
                    }
                    else if(mapa[x+1][y]==2){
                    combate(heroe, kills, bolso);
                        if (heroe.vivo()) {
                            mapa[x][y] = 0;
                            x = x + 1;
                            mapa[x][y] = 1;
                            kills += 1;
                        }
                    }
                }
                break;
            case "P":
                //Si el usuario no tiene pociones se le permite escoger de nuevo
                if(bolso[49]==0){
                System.out.println("No tiene pociones en el bolso");
                }else{
                usarPocion(heroe, bolso);    
                }
                break;
            case "I":
                infoEquipo(heroe);
                break;        
        }
        
        }
        }
        //finalizar contador del tiempo
        long fin=0;
        fin=System.nanoTime();
        int tiempo=(int)((fin - inicio)*1.0e-9);
            System.out.println("");
        System.out.println("\tHas muerto");
        System.out.println("");
        System.out.println("\tNo está muerto lo que puede yacer eternamente \n\tY con el paso de los extraños eónes \n\tIncluso la muerte puede morir. \n\tH.P. Lovecraft");
        mostrarInfo(heroe, contMapa, kills, tiempo);
        
        System.out.println("¿Desea volver a jugar? \t(Si) \t(No)");
        sig=sc.next().toUpperCase();
        
        //Validar respuesta del usuario
        while(!(sig.equals("SI")||sig.equals("S")||sig.equals("NO")||sig.equals("N"))){
            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
        sig=sc.next().toUpperCase();}
        
       
        
        }while((sig.equals("SI")||sig.equals("S")));
        
    
    }
    
//----Funciones   

//Pelea  
    
    public static void combate(Heroe heroe, int kills, int[] bolso) {
        Scanner sc = new Scanner(System.in);
        String resp, sig;
        //Nivel que recibe el monstruo
        int nivel = kills;
        if (nivel == 0) {
            nivel += 1;
        }
        int con = (int) (Math.random() * 6) + 1;
        Monstruo monstruo = null;
        switch (con) {
            case 1:
                //Dragon
                monstruo = new MonstruoDragon(nivel);
                break;
            case 2:
                //Fenix
                monstruo = new MonstruoFenix(nivel);
                break;
            case 3:

                //Grifo
                monstruo = new MonstruoGrifo(nivel);
                break;
            case 4:
                //Hidra
                monstruo = new MonstruoHidra(nivel);
                break;
            case 5:
                //Yeti
                monstruo = new MonstruoYeti(nivel);
                break;
            case 6:
                //Kraken
                monstruo = new MonstruoKraken(nivel);
                break;
        }
        //Mostrar monstruo
        System.out.println("¡Ha aparecido un monstruo!: " + monstruo.getNombre() + " Nivel: " + monstruo.getNivel());

        //Combate hasta que alguno de los dos pierda
        while (heroe.vivo() && monstruo.vivo()) {
            do{
            heroe.setContinuar(true);
            heroe.setEstadoDeDefensa(false);
            monstruo.setEstadoDeDefensa(false);
            System.out.println("");
            //Mostrar estadisticas
            System.out.println(heroe.getNombre() + ": " + heroe.getVida() + " puntos de vida");
            System.out.println(monstruo.getNombre() + ": " + monstruo.getVida() + " puntos de vida");
            System.out.println("¿Desea atacar, Defender o usar una poción? \tAtacar(A) \tDefender(D) \tPocion(P)");
            resp = sc.next().toUpperCase();

            //Validar la respuesta del usuario
            while (!(resp.equals("A") || resp.equals("D") || resp.equals("P"))) {
                System.out.println("Ingreso una opcion no válida");
                System.out.println("¿Desea atacar, Defender o usar una poción? \tAtacar(A) \tDefender(D) \tPocion(P)");
                resp = sc.next().toUpperCase();
            }

            //Usuario escoge pocion
            if (resp.equals("P")) {

                //Si el usuario no tiene pociones se le permite escoger de nuevo
                if (bolso[49] == 0) {
                    System.out.println("No tiene pociones en el bolso, escoja otra opcion");
                    System.out.println("¿Desea atacar o Defender? \tAtacar(A) \tDefender(D)");
                    resp = sc.next().toUpperCase();
                    while (!(resp.equals("A") || resp.equals("D"))) {
                        System.out.println("Ingreso una opcion no válida");
                        System.out.println("¿Desea atacar o Defender? \tAtacar(A) \tDefender(D)");
                        resp = sc.next().toUpperCase();
                    }
                } else {
                    usarPocion(heroe, bolso);
                }
            }
            //Usuario escoge defensa
            else if (resp.equals("D")) {
                heroe.setEstadoDeDefensa(true);
            }
            }while(!heroe.isContinuar());
            //Monstruo escoge
            monstruo.accionMonstruo(heroe);
            //Usuario escoge ataque
            if (heroe.vivo()) {
                if (resp.equals("A")) {
                    heroe.atacar(monstruo);
                }
            }
            if (heroe.isEstadoDeDefensa() == true && monstruo.isEstadoDeDefensa()) {
                System.out.println("Nadie atacó");
            }
        
        }

        if (heroe.vivo()) {
            System.out.println("");
            System.out.println("Has derrotado al monstruo");
            System.out.println("");
            if (heroe.getVida() <= (int) heroe.getMaxVida() * 0.9) {
                heroe.setVida((int) (heroe.getVida() + (heroe.getMaxVida() * 0.1)));
                System.out.println("Has recuperado un 10% de tu vida");
            } else if (heroe.getVida() > (int) heroe.getMaxVida() * 0.9 && heroe.getVida() < heroe.getMaxVida()) {
                heroe.setVida(heroe.getMaxVida());
                System.out.println("Has recuperado un 10 % de tu vida");
            }
            System.out.println("Tu nivel de vida es de: " + heroe.getVida());
            System.out.println("");
            heroe.recibirExperiencia(monstruo.darExperiencia());
            droppearItems(heroe, bolso);

        }

    }
    
    public static void ubicarMonstruos(int[][] mapa){
    for(int i=0; i<mapa.length;i++){
            for(int j=0;j<mapa[0].length;j++){
                if(!(mapa[i][j]==1)){
                int aleatorio= (int) (Math.random()*100) + 1;
                if(aleatorio<=30){
            mapa[i][j]=2;
                }
            }
            }
        }
    }
    
    //validar que hay monstruos en el mapa
    public static boolean hayMonstruos(int[][] mapa) {
        int cont = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == 2) {
                    cont += 1;
                }
            }
        }
        if (cont > 0) {
            return true;
        } else {
            System.out.println("");
            System.out.println("Has vencido a todos los monstruos en el mapa, se generará uno nuevo...");
            return false;
        }
    }
    
    
    //generar items despues del combate
    public static void droppearItems(Heroe heroe, int[] bolso) {
        Scanner sc = new Scanner(System.in);
        int cantidad = (int) ((Math.random() * 4));
        String respuesta, sig;

        int[] cantObjetos = new int[cantidad];
        for (int i = 0; i < cantObjetos.length; i++) {
            int tipo = (int) ((Math.random() * 100) + 1);
            if (tipo > 30 && tipo <= 100) {

                if (tipo >= 30 && tipo <= 50) {
                    cantObjetos[i] = 1;
                    //Pociones de vida    
                } else if (tipo >= 60 && tipo <= 80) {
                    cantObjetos[i] = 2;
                    //pociones de fuerza
                } else if (tipo >= 90 && tipo <= 100) {
                    //pociones multiusos
                    cantObjetos[i] = 3;
                }
            } //Items tipo equipable
            else if (tipo >= 1 && tipo <= 4) {
                cantObjetos[i] = 4;
                //BateDemoledor
            } else if (tipo >= 5 && tipo <= 8) {
                cantObjetos[i] = 5;
                //ArmaduraDragon
            } else if (tipo >= 9 && tipo <= 12) {
                cantObjetos[i] = 6;
                //Escudo De GNB
            } else if (tipo >= 13 && tipo <= 16) {
                cantObjetos[i] = 7;
                //Espada de almas
            } else if (tipo >= 17 && tipo <= 20) {
                cantObjetos[i] = 8;
                //Armadura Oscura
            } else if (tipo >= 21 && tipo <= 24) {
                cantObjetos[i] = 9;
                //Escudo De huesos
            } else if (tipo >= 25 && tipo <= 26) {
                cantObjetos[i] = 10;
                //AK-47
            } else if (tipo >= 27 && tipo <= 28) {
                cantObjetos[i] = 11;
                //Armadura Juggernaut
            } else if (tipo >= 29 && tipo <= 30) {
                cantObjetos[i] = 12;
                //Armadura de obsidiana
            }

        }

//asignar objetos al usuario
        for (int i = 0; i < cantObjetos.length; i++) {
            switch (cantObjetos[i]) {
                case 1:
                    //pocion vida    
                    Pocion pocionVida = new Pocion(30, 0, "Pocion de Vida");
                    System.out.println("Has recibido una pocion de vida");
                    pocionVida.mostrarInfo();
                    int aux = 1;
                    for (int j = 0; j < bolso.length; j++) {
                        if (j < 50 && aux > 0 && bolso[j] == 0) {
                            bolso[j] = aux;
                            aux = 0;
                            System.out.println("Pocion guardada");
                        }
                    }
                    //En caso de que el bolso esté lleno
                    if (aux > 0) {
                        do {
                            heroe.setContinuar(true);
                            System.out.println("El bolso esta lleno ¿Desea usar una pocion del bolso para abrir espacio?  (Si)  (No)");
                            respuesta = sc.next().toUpperCase();
                            if (respuesta.equals("SI")) {
                                usarPocion(heroe, bolso);
                                if (bolso[0] == 0) {
                                    bolso[0] = aux;
                                    aux = 0;
                                    System.out.println("Pocion guardada");
                                }
                            }
                        } while (!heroe.isContinuar());

                    }
                    ordenarBolso(bolso);
                    System.out.println("Presione 'ENTER' para continuar");
                    sig = sc.nextLine();
                    break;
                case 2:
                    //pocion de fuerza
                    Pocion pocionFuerza = new Pocion(0, 10, "Pocion de Fuerza");
                    System.out.println("Has recibido una pocion de Fuerza");
                    pocionFuerza.mostrarInfo();
                    aux = 2;
                    for (int j = 0; j < bolso.length; j++) {
                        if (j < 50 && aux > 0 && bolso[j] == 0) {
                            bolso[j] = aux;
                            aux = 0;
                            System.out.println("Pocion guardada");
                        }
                    }
                    if (aux > 0) {
                        do {
                            heroe.setContinuar(true);
                            System.out.println("El bolso esta lleno ¿Desea usar una pocion del bolso para abrir espacio?  (Si)  (No)");
                            respuesta = sc.next().toUpperCase();
                            if (respuesta.equals("SI")) {
                                usarPocion(heroe, bolso);
                                if (bolso[0] == 0) {
                                    bolso[0] = aux;
                                    aux = 0;
                                    System.out.println("Pocion guardada");
                                }
                            }
                        } while (!heroe.isContinuar());
                    }
                    ordenarBolso(bolso);
                    System.out.println("Presione 'ENTER' para continuar");
                    sig = sc.nextLine();
                    break;

                case 3:
                    //pocion multiusos
                    Pocion pocionMultiuso = new Pocion(50, 15, "Pocion Multiuso");
                    System.out.println("Has recibido una pocion multiusos");
                    pocionMultiuso.mostrarInfo();
                    aux = 3;
                    for (int j = 0; j < bolso.length; j++) {
                        if (j < 50 && aux > 0 && bolso[j] == 0) {
                            bolso[j] = aux;
                            aux = 0;
                            System.out.println("Pocion guardada");
                        }
                    }
                    if (aux > 0) {
                        do {
                            heroe.setContinuar(true);
                            System.out.println("El bolso esta lleno ¿Desea usar una pocion del bolso para abrir espacio?  (Si)  (No)");
                            respuesta = sc.next().toUpperCase();
                            if (respuesta.equals("SI")) {
                                usarPocion(heroe, bolso);
                                if (bolso[0] == 0) {
                                    bolso[0] = aux;
                                    aux = 0;
                                    System.out.println("Pocion guardada");
                                }
                            }
                        } while (!heroe.isContinuar());
                    }
                    ordenarBolso(bolso);
                    System.out.println("Presione 'ENTER' continuar");
                    sig = sc.nextLine();
                    break;

                case 4:
                    Arma BateDemoledor = new Arma(10, 0, "Bate Demoledor");
                    if (heroe.getArma().getFuerza() == 0) {
                        heroe.setArma(BateDemoledor);
                        System.out.println("Se te ha equipado un arma nueva");
                        BateDemoledor.mostrarInfo();
                        System.out.println("Presione 'ENTER' continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArma().getNombre().equals(BateDemoledor.getNombre()))){
                        System.out.println("Usted ya tiene un arma equipada: ");
                        heroe.getArma().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Bate demoledor? \t(Si) \t(No)");
                        BateDemoledor.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        //Validar respuesta del usuario
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArma(BateDemoledor);
                        }
                    }
                    break;

                case 5:
                    Armadura ArmaduraDragon = new Armadura(2, 8, "Armadura De Dragon");
                    if (heroe.getArmadura().getFuerza() == 0) {
                        heroe.setArmadura(ArmaduraDragon);
                        System.out.println("Se te ha equipado un armadura nueva");
                        ArmaduraDragon.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArmadura().getNombre().equals(ArmaduraDragon.getNombre()))){
                        System.out.println("Usted ya tiene un armadura equipada: ");
                        heroe.getArmadura().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Armadura de Dragon? \t(Si) \t(No)");
                        ArmaduraDragon.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArmadura(ArmaduraDragon);
                        }
                    }
                    break;

                case 6:
                    Escudo EscudoGNB = new Escudo(1, 7, "Escudo De GNB");
                    if (heroe.getEscudo().getFuerza() == 0) {
                        heroe.setEscudo(EscudoGNB);
                        System.out.println("Se te ha equipado un escudo nueva");
                        EscudoGNB.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getEscudo().getNombre().equals(EscudoGNB.getNombre()))){
                        System.out.println("Usted ya tiene un escudo equipado: ");
                        heroe.getEscudo().mostrarInfo();
                        System.out.println("¿desea reemplazarlo por Escudo De GNB? \t(Si) \t(No)");
                        EscudoGNB.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setEscudo(EscudoGNB);
                        }
                    }
                    break;

                case 7:
                    Arma EspadaDeAlmas = new Arma(15, 0, "Espada De Almas");
                    if (heroe.getArma().getFuerza() == 0) {
                        heroe.setArma(EspadaDeAlmas);
                        System.out.println("Se te ha equipado un arma nueva");
                        EspadaDeAlmas.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArma().getNombre().equals(EspadaDeAlmas.getNombre()))){
                        System.out.println("Usted ya tiene un arma equipada: ");
                        heroe.getArma().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Espada de Almas? \t(Si) \t(No)");
                        EspadaDeAlmas.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArma(EspadaDeAlmas);
                        }
                    }
                    break;

                case 8:
                    Armadura ArmaduraOscura = new Armadura(4, 14, "Armadura Oscura");
                    if (heroe.getArmadura().getFuerza() == 0) {
                        heroe.setArmadura(ArmaduraOscura);
                        System.out.println("Se te ha equipado un armadura nueva");
                        ArmaduraOscura.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArmadura().getNombre().equals(ArmaduraOscura.getNombre()))){
                        System.out.println("Usted ya tiene un armadura equipada: ");
                        heroe.getArmadura().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Armadura Oscura? \t(Si) \t(No)");
                        ArmaduraOscura.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArmadura(ArmaduraOscura);
                        }
                    }
                    break;

                case 9:
                    Escudo EscudoHuesos = new Escudo(4, 10, "Escudo De Huesos");
                    if (heroe.getEscudo().getFuerza() == 0) {
                        heroe.setEscudo(EscudoHuesos);
                        System.out.println("Se te ha equipado un escudo nueva");
                        EscudoHuesos.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getEscudo().getNombre().equals(EscudoHuesos.getNombre()))){
                        System.out.println("Usted ya tiene un escudo equipado: ");
                        heroe.getEscudo().mostrarInfo();
                        System.out.println("¿desea reemplazarlo por Escudo De Huesos? \t(Si) \t(No)");
                        EscudoHuesos.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setEscudo(EscudoHuesos);
                        }
                    }
                    break;

                case 10:
                    Arma AK47 = new Arma(30, 15, "AK-47");
                    if (heroe.getArma().getFuerza() == 0) {
                        heroe.setArma(AK47);
                        System.out.println("Se te ha equipado un arma nueva");
                        AK47.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArma().getNombre().equals(AK47.getNombre()))){
                        System.out.println("Usted ya tiene un arma equipada: ");
                        heroe.getArma().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Ak-47? \t(Si) \t(No)");
                        AK47.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArma(AK47);
                        }
                    }
                    break;

                case 11:
                    Armadura ArmaduraJuggernaut = new Armadura(9, 20, "Armadura De Juggernaut");
                    if (heroe.getArmadura().getFuerza() == 0) {
                        heroe.setArmadura(ArmaduraJuggernaut);
                        System.out.println("Se te ha equipado un armadura nueva");
                        ArmaduraJuggernaut.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getArmadura().getNombre().equals(ArmaduraJuggernaut.getNombre()))){
                        System.out.println("Usted ya tiene un armadura equipada: ");
                        heroe.getArmadura().mostrarInfo();
                        System.out.println("¿desea reemplazarla por Armadura De Juggernaut? \t(Si) \t(No)");
                        ArmaduraJuggernaut.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setArmadura(ArmaduraJuggernaut);
                        }
                    }
                    break;

                case 12:
                    Escudo EscudoObsidiana = new Escudo(8, 15, "Escudo Obsidiana");
                    if (heroe.getEscudo().getFuerza() == 0) {
                        heroe.setEscudo(EscudoObsidiana);
                        System.out.println("Se te ha equipado un escudo nueva");
                        EscudoObsidiana.mostrarInfo();
                        System.out.println("Presione 'ENTER' para continuar");
                        sig = sc.nextLine();
                    } else if(!(heroe.getEscudo().getNombre().equals(EscudoObsidiana.getNombre()))){
                        System.out.println("Usted ya tiene un escudo equipado: ");
                        heroe.getEscudo().mostrarInfo();
                        System.out.println("¿desea reemplazarlo por Escudo De Obsidiana? \t(Si) \t(No)");
                        EscudoObsidiana.mostrarInfo();
                        respuesta = sc.next().toUpperCase();
                        while (!(respuesta.equals("SI") || respuesta.equals("S") || respuesta.equals("NO") || respuesta.equals("N"))) {
                            System.out.println("Ingreso una respuesta no válida, por favor ingresela de nuevo \t(Si) \t(No)");
                            respuesta = sc.next().toUpperCase();
                        }
                        if (respuesta.equals("SI") || respuesta.equals("S")) {
                            heroe.setEscudo(EscudoObsidiana);
                        }
                    }
                    break;
            }
        }
    }
    
    
    public static void usarPocion(Heroe heroe, int[] bolso){
    //Verificar cuantas pociones tiene el usuario
        Scanner sc=new Scanner(System.in);
        String resp; 
         int pocionVida=0;
         int pocionFuerza=0;
         int pocionMulti=0;
            for(int i=0;i<bolso.length;i++){
                if (bolso[i]==1){
                pocionVida+=1;
                }
                else if(bolso[i]==2){
                pocionFuerza+=1;
                }
                else if(bolso[i]==3){
                pocionMulti+=1;
                }
        }
            //Generar pociones
            Pocion pocionVid=new Pocion(30,0,"Pocion de Vida");
            Pocion pocionFuerz=new Pocion (0,10, "Pocion de Fuerza");
            Pocion pocionMultiuso=new Pocion (50,15, "Pocion Multiuso");
            
            System.out.println("Usted posee estas pociones ¿Cuál desea usar?");
            if(pocionVida>0){
                System.out.println("Pocion de Vida (V): "+pocionVida);    
            }
            if(pocionFuerza>0){
                System.out.println("Pocion de Fuerza (F): "+pocionFuerza);    
            }
            if(pocionMulti>0){
                System.out.println("Pocion Multiusos (M): "+pocionMulti);    
            }
            System.out.println("Cancelar: (C)");
        resp=sc.next().toUpperCase();
        
        //validar que la opcion ingresada si este disponible
        while((resp.equals("V")&&pocionVida==0)||(resp.equals("F")&&pocionFuerza==0)||(resp.equals("M")&&pocionMulti==0)){
            System.out.println("Ingresó una pocion no disponible, por favor igrese una nueva");
        if(pocionVida>0){
                System.out.println("Pocion de Vida (V): "+pocionVida);    
        }
        if(pocionFuerza>0){
            System.out.println("Pocion de Fuerza (F): "+pocionFuerza);    
        }
        if(pocionMulti>0){
            System.out.println("Pocion Multiusos (M): "+pocionMulti);    
        }
        System.out.println("Cancelar (C)");
        
            resp=sc.next().toUpperCase();
        //validar respuestas del usuario
            while (!(resp.equals("V")||resp.equals("F")||resp.equals("M")||resp.equals("C"))){
            System.out.println("Ingresó una opcion no válida");   
                if(pocionVida>0){
                System.out.println("Pocion de Vida (V): "+pocionVida);    
                }
                if(pocionFuerza>0){
                System.out.println("Pocion de Fuerza (F): "+pocionFuerza);    
                }
                if(pocionMulti>0){
                System.out.println("Pocion Multiusos (M): "+pocionMulti);    
                }
                System.out.println("Cancelar (C)");
            resp=sc.next().toUpperCase();
            }
        }
        switch (resp){
            case "V":
               pocionVid.beber(heroe);
               int aux=1;
               for(int i=0;i<bolso.length&&aux>0;i++){
               if(bolso[i]==1){
               bolso[i]=0;
               aux--;
               }
               }
               ordenarBolso(bolso);
               break;
            case "F":
               pocionFuerz.beber(heroe);
               aux=1;
               for(int i=0;i<bolso.length&&aux>0;i++){
               if(bolso[i]==2){
               bolso[i]=0;
               aux--;
               }
               }
               ordenarBolso(bolso);
               break;
            case "M":
               pocionMultiuso.beber(heroe);
               aux=1;
               for(int i=0;i<bolso.length&&aux>0;i++){
               if(bolso[i]==3){
               bolso[i]=0;
               aux--;
               }
               }
               ordenarBolso(bolso);
               break;
               
            case "C":
                heroe.setContinuar(false);
                break;
        
        }
    }   

//Ordenar el bolso   
public static void ordenarBolso(int[] bolso){
    int pos, j;
        for (int i = 1; i < bolso.length; i++) {
            pos = bolso[i];
            for (j = i; j > 0 && bolso[j - 1] > pos; j--) {
                bolso[j] = bolso[j - 1];
            }
            bolso[j] = pos;
        }
    
}

public static void infoEquipo(Heroe heroe){
    Scanner sc=new Scanner(System.in);
    String continuar;
    System.out.println("");
    System.out.println("Arma:");
    if (heroe.getArma().getNombre().equals("Ningún arma equipada")){
        System.out.println(heroe.getArma().getNombre());
    }else{
        heroe.getArma().mostrarInfo();
    }
    System.out.println("");
    System.out.println("Escudo:");
    if (heroe.getEscudo().getNombre().equals("Ningún escudo equipado")){
        System.out.println(heroe.getEscudo().getNombre());
    }else{
        heroe.getEscudo().mostrarInfo();
    }
    System.out.println("");
    System.out.println("Armadura:");
    if (heroe.getArmadura().getNombre().equals("Ningún armadura equipada")){
        System.out.println(heroe.getArmadura().getNombre());
    }else{
        heroe.getArmadura().mostrarInfo();
    }
    System.out.println("");
    System.out.println("Presione 'ENTER' para continuar");
    continuar=sc.nextLine();
}

//Mostrar datos del jugador al perder
public static void mostrarInfo(Heroe heroe, int contMapa, int kills, int tiempo) {
        String sexo;
        int horas = 00;
        int minutos = 00;
        if (heroe.isSexo() == true) {
            sexo = "Hombre";
        } else {
            sexo = "Mujer";
        }
        if (tiempo > 60) {
            minutos = (int) (tiempo / 60);
        }
        for (tiempo = tiempo; tiempo > 60;) {
            tiempo = tiempo - 60;
        }
        if (minutos > 60) {
            horas = (int) (minutos / 60);
        }
        for (minutos = minutos; minutos > 60;) {
            minutos = minutos - 60;
        }

        System.out.println("");
        System.out.println("Nombre: " + heroe.getNombre() + " Sexo: " + sexo);
        System.out.println("");
        System.out.println("Nivel: " + heroe.getNivel() + " Mapas jugados: " + contMapa + " Monstruos derrotados: " + kills);
        System.out.println("");
        System.out.println("Vida: " + heroe.maxVida + " Fuerza: " + heroe.getFuerza() + " Defensa: " + heroe.getDefensa());
        System.out.println("");
        System.out.println("Tiempo jugado: " + horas + ":" + minutos + ":" + tiempo);
        System.out.println("");
    }

    
    public static void imprimirMapa(int [][] mapa, int x, int y){
    for (int i=0; i<mapa.length; i++){
        System.out.print("|");
        for(int j=0;j<mapa[i].length; j++){
            System.out.print("\t");
            switch(mapa[i][j]){
                //mostrar al heroe
                case 1:
                    System.out.print("(7_7)");
                break;
                //mostrar al monstruo
                case 2:
                    //El monstruo esta a 3 o menos pasos del jugador
                    if(((j==y)||(i==x))&&(j-y==1||j-y==2||j-y==3)){System.out.print("(~°o°)~");}
                    else if(((j==y)||(i==x))&&(j-y==-1||j-y==-2||j-y==-3)){System.out.print("(~°o°)~");}
                    else if(((j==y)||(i==x))&&(i-x==1||i-x==2||i-x==3)){System.out.print("(~°o°)~");}
                    else if(((j==y)||(i==x))&&(i-x==-1||i-x==-2||i-x==-3)){System.out.print("(~°o°)~");}
                    else if((i==x-1&&j==y+1)||(i==x-2&&j==y+1)||(i==x-1&&j==y+2)){System.out.print("(~°o°)~");}
                    else if((i==x+1&&j==y+1)||(i==x+2&&j==y+1)||(i==x+1&&j==y+2)){System.out.print("(~°o°)~");}
                    else if((i==x-1&&j==y-1)||(i==x-2&&j==y-1)||(i==x-1&&j==y-2)){System.out.print("(~°o°)~");}
                    else if((i==x+1&&j==y-1)||(i==x+2&&j==y-1)||(i==x+1&&j==y-2)){System.out.print("(~°o°)~");}
                    //El monstruo esta mas lejos
                    else{System.out.print("+");}
                    break;
                default:
                    //pocicion vacia
                    System.out.print("+");
                    break;
            }
        }
    System.out.println("\t|");
    }
    
    }
    
}