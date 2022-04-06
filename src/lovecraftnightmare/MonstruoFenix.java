package lovecraftnightmare;


public class MonstruoFenix extends Monstruo{

public MonstruoFenix (int nivel){
        this.fuerza= ((int)(7*Math.random())+4)*nivel;
        this.defensa= (int)((16*Math.random())+5)*nivel;
        this.vida= ((int)(Math.random()*20)+10)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Fenix";
        this.nivel=nivel;
    }
    
    
    
    
    
     @Override

             public void accionMonstruo(Heroe heroe) {
        int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Fenix escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza+10;   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza+10;
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=(10-(((int)Math.random()*3)+1))*5-(heroe.getDefensa()+bonoDefensa);
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            }
        }else{System.out.println("Fenix Escoge defensa");
          this.estadoDeDefensa=true;} 
    }
    
}
