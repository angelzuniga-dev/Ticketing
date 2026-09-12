/**
 * Clase de flujo que llama a los metodos de las demas clases para
 * simular el ciclo de vida completo de un ticket:
 * registrar -> crear -> adjuntar fotografia -> asignar -> escalar -> cerrar -> metricas.
 *
 * En BlueJ se puede crear un objeto de esta clase y llamar a
 * ejecutarFlujoCompleto() desde el banco de objetos, o correr main().
 */
public class FlujoTicket
{
    private GestorTicket gestor;

    public FlujoTicket()
    {
        this.gestor = new GestorTicket();
    }

    public void ejecutarFlujoCompleto()
    {
        Solicitante solicitante = new Solicitante(1, "Ana", "Perez", "ana.perez@correo.com",
                "5555-1111", "Contabilidad", "101");
        Tecnico tecnico = new Tecnico(2, "Luis", "Garcia", "luis.garcia@correo.com",
                "5555-2222", "Redes", 2);

        solicitante.registrar();
        tecnico.registrar();

        Ticket ticket = gestor.crearTicket(solicitante, "No hay acceso a internet", "Alta");
        ticket.adjuntarFotografia("evidencia_falla.png");

        gestor.asignarTicket(ticket, tecnico);
        gestor.escalarTicket(ticket);
        gestor.cerrarTicket(ticket);

        gestor.generarMetricas();

        System.out.println();
        System.out.println("Intentando cerrar de nuevo el mismo ticket (para probar el manejo de errores):");
        gestor.cerrarTicket(ticket);
    }

    public static void main(String[] args)
    {
        FlujoTicket flujo = new FlujoTicket();
        flujo.ejecutarFlujoCompleto();
    }
}