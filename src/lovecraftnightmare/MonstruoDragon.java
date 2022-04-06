
package lovecraftnightmare;

public class MonstruoDragon extends Monstruo{

public MonstruoDragon(int nivel){
        this.nivel=nivel;
        this.fuerza= ((int)(4*Math.random())+10)*nivel;
        this.defensa= ((int)(15*Math.random()+1)*nivel);
        this.vida= ((int)(20*Math.random())+20)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Dragon";
        
    }
    
@Override
    public void accionMonstruo (Heroe heroe) {
        int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Dragon escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza*2;   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza*2;
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=((10-(((int)Math.random()*3)+1))*5)-(heroe.getDefensa()+bonoDefensa);
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            
        }            
    }else{System.out.println("Dragon Escoge defensa");
          this.estadoDeDefensa=true;} 
}


    
}
