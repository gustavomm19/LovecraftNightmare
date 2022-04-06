
package lovecraftnightmare;

public class Armadura extends Equipable{
  
    
    
    public Armadura(int fuerza, int defensa, String nombre) {
        this.fuerza=fuerza;
        this.defensa=defensa;
        this.nombre=nombre;
    } 

    public void mostrarInfo(){
     System.out.println("Esta armadura posee las sig caracteristicas: " 
             +"\nnombre: "+this.nombre
             +"\nFuerza: "+this.fuerza
             +"\nDefensa: "+this.defensa);
 }     
}
