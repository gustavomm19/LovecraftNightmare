package lovecraftnightmare;


public abstract class Monstruo extends Personaje{

  public abstract void accionMonstruo(Heroe heroe);
    
//calcular la experiencia recibida
  
public    int darExperiencia() {
int experiencia= (nivel*2)*(int)((Math.random()*10)+1);
     System.out.println("Recibes "+experiencia+" puntos de experiencia");
return experiencia;    }
    
    
   
    
}
