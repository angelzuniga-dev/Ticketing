import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora que gestiona el flujo del proceso de tickets:
 * creacion, asignacion, escalamiento, cierre y generacion de metricas.
 */
public class GestorTicket
{
    private List<Ticket> ticketsActivos;
    private Bitacora bitacora;
    private int contadorId;

    public GestorTicket()
    {
        this.ticketsActivos = new ArrayList<>();
        this.bitacora = new Bitacora();
        this.contadorId = 1;
    }

    public Ticket crearTicket(Solicitante solicitante, String descripcion, String prioridad)
    {
        Ticket ticket = new Ticket(contadorId, descripcion, prioridad, solicitante, null);
        contadorId++;
        ticketsActivos.add(ticket);
        bitacora.registrarEvento(ticket.getIdTicket(), "Creacion", solicitante.getNombre());
        return ticket;
    }

    public void asignarTicket(Ticket ticket, Tecnico tecnico)
    {
        try
        {
            boolean exito = ticket.asignar(tecnico);
            if (exito)
            {
                bitacora.registrarEvento(ticket.getIdTicket(), "Asignacion", tecnico.getNombre());
            }
        }
        catch (Exception e)
        {
            System.out.println("No se pudo completar la asignacion del ticket #" + ticket.getIdTicket() + ": " + e.getMessage());
        }
    }

    public void escalarTicket(Ticket ticket)
    {
        try
        {
            boolean exito = ticket.escalar();
            if (exito)
            {
                bitacora.registrarEvento(ticket.getIdTicket(), "Escalamiento", "Sistema SLA");
            }
        }
        catch (Exception e)
        {
            System.out.println("No se pudo completar el escalamiento del ticket #" + ticket.getIdTicket() + ": " + e.getMessage());
        }
    }

    public void cerrarTicket(Ticket ticket)
    {
        try
        {
            boolean exito = ticket.cerrar();
            if (exito)
            {
                ticketsActivos.remove(ticket);
                bitacora.registrarEvento(ticket.getIdTicket(), "Cierre", "Tecnico");
            }
        }
        catch (Exception e)
        {
            System.out.println("No se pudo completar el cierre del ticket #" + ticket.getIdTicket() + ": " + e.getMessage());
        }
    }

    public void generarMetricas()
    {
        System.out.println("=== Reporte de Metricas ===");
        System.out.println("Tickets activos: " + ticketsActivos.size());
        System.out.println("Eventos registrados en bitacora: " + bitacora.getTotalEventos());
    }

    public List<Ticket> getTicketsActivos()
    {
        return ticketsActivos;
    }

    public Bitacora getBitacora()
    {
        return bitacora;
    }
}