package lovecraftnightmare;

public class MonstruoHidra extends Monstruo{

public MonstruoHidra(int nivel){
        this.fuerza= ((int)(Math.random()*10)+20)*nivel;
        this.defensa= ((int)((15*Math.random())+10))*nivel;
        this.vida= ((int)(Math.random()*15)+20)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Hidra";
        this.nivel=nivel;
    }    
    
        
    
    @Override
    public void accionMonstruo(Heroe heroe) {
    int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Hidra escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza/2;   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza/2;
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=(10-(((int)Math.random()*3)+1))*5-(heroe.getDefensa()+bonoDefensa);
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            
        }            
    }else{System.out.println("Hidra Escoge defensa");
            this.estadoDeDefensa=true;}     
    }
    
}
