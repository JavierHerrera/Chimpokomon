package chimpokomon.com.example.pep.chimpokomon;


public class Characters {
    private int imagen;
    private String nombre;
    private int visitas;
    private int tipo_elemento;

    public Characters(int imagen, String nombre, int visitas, int tipo_elemento) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.visitas = visitas;
        this.tipo_elemento = tipo_elemento;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVisitas() {
        return visitas;
    }

    public int getType() {
        return tipo_elemento;
    }
}


