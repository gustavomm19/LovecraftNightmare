package lovecraftnightmare;

public class Personaje {
   protected int nivel;
   protected int vida;
   protected int maxVida;
   protected int fuerza;
   protected int defensa;
   protected String nombre;
   protected boolean estadoDeDefensa;
   
   
//getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMaxVida() {
        return maxVida;
    }

    public void setMaxVida(int maxVida) {
        this.maxVida = maxVida;
    }
    
    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefenza(int defensa) {
        this.defensa = defensa;
    }

    public boolean isEstadoDeDefensa() {
        return estadoDeDefensa;
    }

    public void setEstadoDeDefensa(boolean estadoDeDefensa) {
        this.estadoDeDefensa = estadoDeDefensa;
    }
    
    
    public void sufrirDano(int dano){
    if(dano>0){
    this.vida-=dano;
        System.out.println(nombre+" ha sufrido " +dano+" puntos de daño");     
    }else{System.out.println(nombre+" ha defendido exitosamente");}
    
    }

 
   
   public boolean vivo(){
   return this.vida>0;
   }
}
