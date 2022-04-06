
package lovecraftnightmare;

public class Escudo extends Equipable{
 
    
    
    public Escudo(int fuerza, int defensa, String nombre) {
        this.fuerza=fuerza;
        this.defensa=defensa;
        this.nombre=nombre;
    }
 
 
  @Override
    public void mostrarInfo(){
     System.out.println("Este escudo posee las sig caracteristicas: " 
            +"\nnombre: "+this.nombre
             +"\nFuerza: "+this.fuerza
             +"\nDefensa: "+this.defensa);
 }
    
      
    
}
