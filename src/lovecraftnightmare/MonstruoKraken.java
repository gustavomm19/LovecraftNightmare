
package lovecraftnightmare;


public class MonstruoKraken extends Monstruo{

    public MonstruoKraken(int nivel){
        this.fuerza= ((int)(Math.random()*16)+5)*nivel;
        this.defensa= ((int)((9*Math.random())+1))*nivel;
        this.vida= ((int)(25*Math.random())+10)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Kraken";
        this.nivel=nivel;
    }
    
    @Override
    public void accionMonstruo(Heroe heroe) {
    int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Kraken escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza*2;   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza*2;
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=(10-(((int)Math.random()*3)+1))*5-(heroe.getDefensa()+bonoDefensa);
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            }            
        }else{System.out.println("Kraken Escoge defensa");
            this.estadoDeDefensa=true;}     
    }
    
}
