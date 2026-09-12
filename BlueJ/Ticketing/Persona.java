/**
 * Clase base que representa a una persona dentro del sistema.
 * Solicitante y Tecnico heredan de esta clase.
 */
public class Persona
{
    protected int idPersona;
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String telefono;

    public Persona(int idPersona, String nombre, String apellido, String correo, String telefono)
    {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public void registrar()
    {
        System.out.println("Persona registrada: " + nombre + " " + apellido + " (ID: " + idPersona + ")");
    }

    public void actualizarDatos(String correo, String telefono)
    {
        this.correo = correo;
        this.telefono = telefono;
        System.out.println("Datos actualizados para " + nombre + " " + apellido);
    }

    public boolean validarCorreo()
    {
        return correo != null && correo.contains("@");
    }

    public int getIdPersona()
    {
        return idPersona;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getTelefono()
    {
        return telefono;
    }

    @Override
    public String toString()
    {
        return nombre + " " + apellido;
    }
}