/**
 * Representa a un tecnico de soporte que atiende y resuelve tickets.
 * Hereda de Persona.
 */
public class Tecnico extends Persona
{
    private String especialidad;
    private int nivelSoporte;
    private boolean disponible;

    public Tecnico(int idPersona, String nombre, String apellido, String correo,
                    String telefono, String especialidad, int nivelSoporte)
    {
        super(idPersona, nombre, apellido, correo, telefono);
        this.especialidad = especialidad;
        this.nivelSoporte = nivelSoporte;
        this.disponible = true;
    }

    @Override
    public void registrar()
    {
        super.registrar();
        System.out.println("  -> Registrado como Tecnico, especialidad: " + especialidad);
    }

    public void recibirTicket(Ticket ticket)
    {
        ticket.setTecnico(this);
        this.disponible = false;
        System.out.println(nombre + " recibio el ticket #" + ticket.getIdTicket());
    }

    public void resolverTicket(Ticket ticket)
    {
        boolean exito = ticket.cerrar();
        if (exito)
        {
            this.disponible = true;
            System.out.println(nombre + " resolvio el ticket #" + ticket.getIdTicket());
        }
    }

    public void escalarTicket(Ticket ticket)
    {
        boolean exito = ticket.escalar();
        if (exito)
        {
            System.out.println(nombre + " escalo el ticket #" + ticket.getIdTicket());
        }
    }

    public String getEspecialidad()
    {
        return especialidad;
    }

    public int getNivelSoporte()
    {
        return nivelSoporte;
    }

    public boolean isDisponible()
    {
        return disponible;
    }

    public void actualizarDisponibilidad(boolean disponible)
    {
        this.disponible = disponible;
    }
}