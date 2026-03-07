package scoresim;

public class Equipo {
    private String nombre;
    private double defensa;
    private double medio;
    private double ataque;
    private double banquillo; // variable de "profundidad de plantilla"

    public Equipo(String nombre, double defensa, double medio, double ataque, double banquillo) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.medio = medio;
        this.ataque = ataque;
        this.banquillo = banquillo;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getDefensa() { return defensa; }
    public double getMedio() { return medio; }
    public double getAtaque() { return ataque; }
    public double getBanquillo() { return banquillo; }
}