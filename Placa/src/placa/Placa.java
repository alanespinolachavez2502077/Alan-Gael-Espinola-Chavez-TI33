package placa;

public class Placa extends Persona {

    private String placa;
    private String modelo;
    private String descripcion;
    private String estado;
    private String status;

    public Placa() {
        super();
    }

    public Placa(int id, String nombre, String placa, String modelo, String descripcion, String estado, String status) {
        super(id, nombre);
        this.placa = placa;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}