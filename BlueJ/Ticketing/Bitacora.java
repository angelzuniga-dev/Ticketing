import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Registra el historial de acciones realizadas sobre los tickets.
 */
public class Bitacora
{
    private List<String> eventos;

    public Bitacora()
    {
        this.eventos = new ArrayList<>();
    }

    public void registrarEvento(int idTicket, String accion, String usuario)
    {
        String registro = new Date() + " | Ticket #" + idTicket + " | " + accion + " | Usuario: " + usuario;
        eventos.add(registro);
        System.out.println("Bitacora: " + registro);
    }

    public List<String> consultarHistorial(int idTicket)
    {
        List<String> resultado = new ArrayList<>();
        String marca = "Ticket #" + idTicket + " ";
        for (String evento : eventos)
        {
            if (evento.contains(marca))
            {
                resultado.add(evento);
            }
        }
        return resultado;
    }

    public void exportarReporte()
    {
        System.out.println("=== Reporte de Bitacora (" + eventos.size() + " eventos) ===");
        for (String evento : eventos)
        {
            System.out.println(evento);
        }
    }

    public int getTotalEventos()
    {
        return eventos.size();
    }
}