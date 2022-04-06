/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftnightmare;

/**
 *
 * @author Gustavo
 */
public class MonstruoGrifo extends Monstruo{

    public MonstruoGrifo (int nivel){
        this.fuerza= ((int)(12*Math.random())+5)*nivel;
        this.defensa= ((int)(8*Math.random()+5)*nivel);
        this.vida= ((int)(5*Math.random())+10)*nivel;
        this.maxVida=vida;
        this.estadoDeDefensa=false;
        this.nombre="Grifo";
        this.nivel=nivel;
    }
    
    @Override
    public void accionMonstruo(Heroe heroe) {
        int aleatorio=(int) ((Math.random()*10)+1);
        if (aleatorio<=6 && aleatorio>=1) {
            System.out.println("Grifo escoge ataque");
            if (heroe.isEstadoDeDefensa()==false){
             int dano=this.fuerza+(this.fuerza/2);   
                heroe.sufrirDano(dano);
            }else{
            int ataque=this.fuerza+(this.fuerza/2);
            int bonoDefensa=heroe.getEscudo().getDefensa() + heroe.getArma().getDefensa() + heroe.getArmadura().getDefensa();
                int porcentajeDano=(10-(((int)Math.random()*3)+1))*5-(heroe.getDefensa()+bonoDefensa);
            int dano=(int)(ataque*(porcentajeDano*0.01));
            heroe.sufrirDano(dano);
            }
        }else{System.out.println("Grifo Escoge defensa");
          this.estadoDeDefensa=true;} 
    }

}
