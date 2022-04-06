package lovecraftnightmare;

public class MonstruoYeti extends Monstruo{

    public MonstruoYeti(int nivel){
        this.fuerza= ((int)(Math.random()*4)+15)*nivel;
        this.defensa= ((int)(2*Math.random()+1))*nivel;
        this.vida= ((int)(Math.random()*15)+8)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Yeti";
        this.nivel=nivel;
    }    
    
    
    @Override
    public void accionMonstruo(Heroe heroe) {
    int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Yeti escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza+7;   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza+7;
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=(int) ((10-((Math.random()*3)+1))*5-(heroe.getDefensa()+bonoDefensa));
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            }            
        }else{System.out.println("Yeti Escoge defensa");
            this.estadoDeDefensa=true;}
    }
    
}
