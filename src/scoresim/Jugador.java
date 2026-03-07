package scoresim;

public class Jugador {
    private int id;
    private String nombre;
    private String apellido;
    private String posicion;
    private int valoracionGlobal; // El OVR
    private String nacionalidad;

    // Constructor completo
    public Jugador(int id, String nombre, String apellido, String posicion, int valoracionGlobal, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.posicion = posicion;
        this.valoracionGlobal = valoracionGlobal;
        this.nacionalidad = nacionalidad;
    }

    // Getters (necesarios para que el Simulador lea los datos)
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getPosicion() { return posicion; }
    public int getValoracionGlobal() { return valoracionGlobal; }
    public String getNacionalidad() { return nacionalidad; }
}