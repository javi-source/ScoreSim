package scoresim;

public class Equipo {

    private String nombre;
    private int defensa;
    private int medio;
    private int ataque;

    public Equipo(String nombre, int defensa, int medio, int ataque) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.medio = medio;
        this.ataque = ataque;

    }

    public String getNombre() { return nombre; }
    public int getDefensa() { return defensa; }
    public int getMedio() { return medio; }
    public int getAtaque() { return ataque; }

}
