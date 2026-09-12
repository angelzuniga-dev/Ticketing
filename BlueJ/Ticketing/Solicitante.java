/**
 * Representa a un solicitante (cliente) que puede crear tickets de soporte.
 * Hereda de Persona.
 */
public class Solicitante extends Persona
{
    private String departamento;
    private String extension;

    public Solicitante(int idPersona, String nombre, String apellido, String correo,
                        String telefono, String departamento, String extension)
    {
        super(idPersona, nombre, apellido, correo, telefono);
        this.departamento = departamento;
        this.extension = extension;
    }

    @Override
    public void registrar()
    {
        super.registrar();
        System.out.println("  -> Registrado como Solicitante del departamento: " + departamento);
    }

    public Ticket crearTicket(int idTicket, String descripcion, String prioridad)
    {
        Ticket ticket = new Ticket(idTicket, descripcion, prioridad, this, null);
        System.out.println(nombre + " creo el ticket #" + idTicket);
        return ticket;
    }

    public String consultarEstado(Ticket ticket)
    {
        return "El ticket #" + ticket.getIdTicket() + " esta en estado: " + ticket.getEstado();
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public String getExtension()
    {
        return extension;
    }
}