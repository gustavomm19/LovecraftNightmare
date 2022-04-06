package lovecraftnightmare;

import java.util.Scanner;

public class Heroe extends Personaje{
    Scanner sc=new Scanner(System.in);
   private boolean sexo, continuar;
    private int [] bolso;
    private Arma arma;
    private Armadura armadura;
    private Escudo escudo;
    private int experiencia;
    //getters and setters

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int[] getBolso() {
        return bolso;
    }

    public void setBolso(int[] bolso) {
        this.bolso = bolso;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public Escudo getEscudo() {
        return escudo;
    }

    public void setEscudo(Escudo escudo) {
        this.escudo = escudo;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }
    
    
//constructor    
 public Heroe(int nivel, int vida,int fuerza, int defensa, String nombre, boolean sexo, int[] bolso, Arma arma, Armadura armadura, Escudo escudo, boolean estadoDeDefensa){
 this.experiencia=0;
 this.nivel=nivel;
 this.vida=vida;
 this.maxVida=vida;
 this.fuerza=fuerza;
 this.defensa=defensa;
 this.nombre=nombre;
 this.sexo=sexo;
 this.bolso=bolso;
 this.arma=arma;
 this.armadura=armadura;
 this.escudo=escudo;
 this.estadoDeDefensa=estadoDeDefensa;
 this.continuar=true;
 }   
 

//metdodos del jugador
 
 //metodo de ataque
    
  public void atacar(Personaje defensor){
    //Solo ataca si no esta defendiendo
    if (this.estadoDeDefensa==false){
      //Calcula su ataque con lo que tiene equipado
      int bonoAtaque=escudo.getFuerza() + arma.getFuerza() + escudo.getFuerza();
      //Si el monstruo no defiende
      if(defensor.isEstadoDeDefensa()==false){
      int dano= ((int)(Math.random()*10)+1) *(fuerza+bonoAtaque);
    defensor.sufrirDano(dano);}
      //Si el monstuo defiende
      else{
      int ataque= ((int)(Math.random()*10)+1) *(fuerza+bonoAtaque);   
      int porDano=(int) ((((Math.random()*4)+7)*5) - defensor.getDefensa());
      int dano=(int) (ataque*(porDano*0.01));
      defensor.sufrirDano(dano);
      //Si el monstruo sigue vivo
      if(defensor.getVida()>0){    
      //Si el monstruo sufrio daño
      if((dano>0)){
              //Si el monstruo tiene menos del 90% de su vida maxima
            if(defensor.getVida()<(int)defensor.maxVida*0.90){
              defensor.setVida((int) (defensor.getVida()+ (defensor.getMaxVida()-defensor.getVida())*(0.1)));
              System.out.println(defensor.getNombre()+" ha recuperado 10% de su vida");
              }//Si el monstruo tiene 90% o mas de su vida maxima
              else if(defensor.getVida()>=(int)defensor.maxVida*0.90){defensor.setVida(defensor.getMaxVida());
              System.out.println(defensor.getNombre()+" ha recuperado 10% de su vida");
              }
          }
          System.out.println("Le quedan "+defensor.getVida()+" puntos de vida");
      }}
      }
  }
  
//metodo de defenza
   
  public void defender(int dano){
      this.estadoDeDefensa=true;
  }
  //recibir la experiencia
  public void recibirExperiencia(int exp){
  String sig;
    int limite=(int) Math.pow((2*nivel),2);
      if(experiencia+exp<limite){
      experiencia+=exp;
      System.out.println("Presione 'ENTER' para continuar");
      sig=sc.nextLine();
      }else if(experiencia+exp==limite){
      experiencia=0;
      subioDeNivel();
      System.out.println("Presione 'ENTER' para continuar");
      sig=sc.nextLine();
      }else if(experiencia+exp>limite){
      int sobrante=(experiencia+exp)-limite;
      subioDeNivel();
      experiencia=0;
      recibirExperiencia(sobrante);
      
      }
  }
  
  
  //cambios al subir de nivel
  public void subioDeNivel(){
  nivel +=1;
  maxVida +=(int)(Math.random()*50)+1;
  fuerza +=(int)(Math.random()*10)+1;
  defensa +=(int)(Math.random()*10)+1;
  this.vida=maxVida;
      System.out.println("¡Has subido de nivel! Eres nivel: "+nivel 
        +"\nVida: "+vida
        +"\nFuerza: "+fuerza
        +"\nDefensa: "+defensa);
  }
 
}

