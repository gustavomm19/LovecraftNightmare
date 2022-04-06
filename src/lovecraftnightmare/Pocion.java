
package lovecraftnightmare;

public class Pocion extends ObjetosConsumibles{
    
public Pocion(int vida, int fuerza, String nombre) {
        this.vida = vida;
        this.fuerza = fuerza;
        this.nombre = nombre;
    }
    
  
public void beber(Heroe heroe){
//Si la pocion aumenta la vida
if(this.vida>0){
    if(this.vida + heroe.getVida()>= heroe.getMaxVida()){
    heroe.setVida(heroe.getMaxVida());
    System.out.println("Vida recuperada al maximo");
    }else{
    heroe.setVida(vida+heroe.getVida());
    System.out.println("Recuperaste "+vida+" puntos de vida");
    }
}
//Si la pocion aumenta la fuerza
if(this.fuerza>0){
    
    heroe.setFuerza(fuerza+heroe.getFuerza());
    System.out.println("Aumentaste "+fuerza+" puntos de fuerza");    
}
}   
    
    
    
    @Override
     public  void mostrarInfo() {
        System.out.println("Esta pocion aumenta los siguientes atributos " 
             +"\nNombre: "+this.nombre
             +"\nVida: "+this.vida
             +"\nFuerza: "+this.fuerza
             );
    }
    
}
