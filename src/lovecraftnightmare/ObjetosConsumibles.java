
package lovecraftnightmare;

public abstract class ObjetosConsumibles {
    
protected int vida;    
protected int fuerza;
protected String nombre;

    

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

        
    
    public abstract void mostrarInfo();

    

}
