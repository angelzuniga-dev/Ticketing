import java.util.Date;

/**
 * Representa un ticket de soporte.
 *
 * NOTA: en Java una clase NO puede heredar de dos clases a la vez
 * (no existe herencia multiple de clases, a diferencia del diagrama UML).
 * Por eso Ticket no hace "extends Solicitante, Tecnico"; en su lugar los
 * relaciona por COMPOSICION, guardando una referencia a cada uno. Esta es
 * la forma correcta en Java de representar lo que el diagrama de clases
 * muestra como herencia multiple.
 */
public class Ticket
{
    private int idTicket;
    private String descripcion;
    private String estado;       // "Abierto", "Asignado", "Escalado", "Cerrado"
    private String prioridad;
    private Date fechaCreacion;
    private Date fechaCierre;
    private String fotografia;   // ruta o nombre del archivo de la fotografia adjunta

    private Solicitante solicitante;
    private Tecnico tecnico;

    public Ticket(int idTicket, String descripcion, String prioridad,
                  Solicitante solicitante, Tecnico tecnico)
    {
        this.idTicket = idTicket;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.solicitante = solicitante;
        this.tecnico = tecnico;
        this.estado = "Abierto";
        this.fechaCreacion = new Date();
    }

    public void crear()
    {
        this.estado = "Abierto";
        this.fechaCreacion = new Date();
        System.out.println("Ticket #" + idTicket + " creado: " + descripcion);
    }

    public boolean asignar(Tecnico tecnico)
    {
        try
        {
            if (estado.equals("Cerrado"))
            {
                throw new IllegalStateException("No se puede asignar un ticket que ya esta cerrado.");
            }
            if (tecnico == null)
            {
                throw new IllegalArgumentException("No se puede asignar un tecnico nulo.");
            }
            this.tecnico = tecnico;
            this.estado = "Asignado";
            System.out.println("Ticket #" + idTicket + " asignado a " + tecnico.getNombre());
            return true;
        }
        catch (IllegalStateException | IllegalArgumentException e)
        {
            System.out.println("Error al asignar ticket #" + idTicket + ": " + e.getMessage());
            return false;
        }
    }

    public boolean escalar()
    {
        try
        {
            if (estado.equals("Cerrado"))
            {
                throw new IllegalStateException("No se puede escalar un ticket que ya esta cerrado.");
            }
            this.estado = "Escalado";
            System.out.println("Ticket #" + idTicket + " escalado.");
            return true;
        }
        catch (IllegalStateException e)
        {
            System.out.println("Error al escalar ticket #" + idTicket + ": " + e.getMessage());
            return false;
        }
    }

    public boolean cerrar()
    {
        try
        {
            if (estado.equals("Cerrado"))
            {
                throw new IllegalStateException("El ticket #" + idTicket + " ya se encuentra cerrado.");
            }
            this.estado = "Cerrado";
            this.fechaCierre = new Date();
            System.out.println("Ticket #" + idTicket + " cerrado.");
            return true;
        }
        catch (IllegalStateException e)
        {
            System.out.println("Error al cerrar ticket #" + idTicket + ": " + e.getMessage());
            return false;
        }
    }

    public void adjuntarFotografia(String rutaImagen)
    {
        try
        {
            if (rutaImagen == null || rutaImagen.trim().isEmpty())
            {
                throw new IllegalArgumentException("La ruta de la fotografia no puede estar vacia.");
            }
            this.fotografia = rutaImagen;
            System.out.println("Fotografia adjuntada al ticket #" + idTicket + ": " + rutaImagen);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error al adjuntar fotografia en ticket #" + idTicket + ": " + e.getMessage());
        }
    }

    public int getIdTicket()
    {
        return idTicket;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getEstado()
    {
        return estado;
    }

    public String getPrioridad()
    {
        return prioridad;
    }

    public Date getFechaCreacion()
    {
        return fechaCreacion;
    }

    public Date getFechaCierre()
    {
        return fechaCierre;
    }

    public String getFotografia()
    {
        return fotografia;
    }

    public Solicitante getSolicitante()
    {
        return solicitante;
    }

    public Tecnico getTecnico()
    {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico)
    {
        this.tecnico = tecnico;
    }

    @Override
    public String toString()
    {
        return "Ticket #" + idTicket + " [" + estado + "] - " + descripcion;
    }
}