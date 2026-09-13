using System;
using System.Collections.Generic; 
using System.Linq; 

namespace TicketsSoporte.logica
{
    public class GestorTicket
    {
        public List<Tecnico> lstTecnicos { get; set; }
        public List<Solicitante> lstSolicitantes { get; set; }
        public List<Ticket> lstTickets { get; set; }

        // El constructor y los métodos ahora están DENTRO de la clase
        public GestorTicket()
        {
            lstTecnicos = new List<Tecnico>();
            lstSolicitantes = new List<Solicitante>();
            lstTickets = new List<Ticket>();
        }
        
        public Ticket CrearTicket(int intNumero, string srtTitulo, string strDescripcion, string strCategoria, string strPrioridad, Solicitante objSolicitante, Tecnico objTecnico)
        {
            // Validaciones

            Ticket objTicket = new Ticket(intNumero, srtTitulo, strDescripcion, strCategoria, strPrioridad, objSolicitante, objTecnico);
            
            objTicket.AsignarTecnico(objTecnico);
            
            lstTickets.Add(objTicket); 
            
            return objTicket;
        }
        
        public Ticket BuscarTicket(int intNumero)
        {
            return lstTickets.FirstOrDefault(t => t.intNumero == intNumero);
        }
    } 
}