
package lovecraftnightmare;

public class Arma extends Equipable{

   public Arma(int fuerza, int defensa, String nombre) {
        this.fuerza=fuerza;
        this.defensa=defensa;
        this.nombre=nombre;
    }


    
    @Override
 public void mostrarInfo(){
     System.out.println("Esta arma posee las sig caracteristicas: " 
             +"\nnombre: "+this.nombre
             +"\nFuerza: "+this.fuerza
             +"\nDefensa: "+this.defensa);
 }
 
 
}
