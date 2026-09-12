import java.util.List;

/**
 * Simulador automatico del sistema de tickets: registra usuarios, crea
 * tickets, los asigna, escala, cierra, muestra la bitacora y las metricas,
 * y ademas prueba el manejo de errores. Todo ocurre en secuencia, sin pedir
 * datos por teclado.
 *
 * En BlueJ: crea un objeto Simulador y llama a ejecutar() desde el banco
 * de objetos, o corre main() directamente.
 */
public class Simulador
{
    private GestorTicket gestor;

    public Simulador()
    {
        this.gestor = new GestorTicket();
    }

    public void ejecutar()
    {
        separador("REGISTRO DE USUARIOS");
        Solicitante ana = new Solicitante(1, "Ana", "Perez", "ana.perez@correo.com",
                "5555-1111", "Contabilidad", "101");
        Solicitante carlos = new Solicitante(2, "Carlos", "Lopez", "carlos.lopez@correo.com",
                "5555-3333", "Ventas", "205");
        Tecnico luis = new Tecnico(3, "Luis", "Garcia", "luis.garcia@correo.com",
                "5555-2222", "Redes", 2);
        Tecnico marta = new Tecnico(4, "Marta", "Ramirez", "marta.ramirez@correo.com",
                "5555-4444", "Soporte de Software", 1);

        ana.registrar();
        carlos.registrar();
        luis.registrar();
        marta.registrar();

        separador("CREACION DE TICKETS");
        Ticket ticket1 = gestor.crearTicket(ana, "No hay acceso a internet", "Alta");
        Ticket ticket2 = gestor.crearTicket(carlos, "Error al generar factura", "Media");
        ticket1.adjuntarFotografia("evidencia_falla_red.png");

        separador("ASIGNACION DE TICKETS");
        gestor.asignarTicket(ticket1, luis);
        gestor.asignarTicket(ticket2, marta);

        separador("ESCALAMIENTO DE TICKET");
        gestor.escalarTicket(ticket1);

        separador("CIERRE DE TICKETS");
        gestor.cerrarTicket(ticket2);
        gestor.cerrarTicket(ticket1);

        separador("TICKETS ACTIVOS RESTANTES");
        List<Ticket> activos = gestor.getTicketsActivos();
        if (activos.isEmpty())
        {
            System.out.println("No quedan tickets activos.");
        }
        else
        {
            for (Ticket t : activos)
            {
                System.out.println(t);
            }
        }

        separador("PRUEBA DE MANEJO DE ERRORES");
        System.out.println("Intentando cerrar de nuevo el ticket #" + ticket1.getIdTicket() + " (ya esta cerrado):");
        gestor.cerrarTicket(ticket1);

        System.out.println("Intentando asignar un tecnico nulo al ticket #" + ticket2.getIdTicket() + ":");
        gestor.asignarTicket(ticket2, null);

        System.out.println("Intentando adjuntar una fotografia con ruta vacia:");
        ticket2.adjuntarFotografia("");

        separador("BITACORA COMPLETA");
        gestor.getBitacora().exportarReporte();

        separador("METRICAS FINALES");
        gestor.generarMetricas();
    }

    private void separador(String titulo)
    {
        System.out.println();
        System.out.println("========== " + titulo + " ==========");
    }

    public static void main(String[] args)
    {
        Simulador simulador = new Simulador();
        simulador.ejecutar();
    }
}