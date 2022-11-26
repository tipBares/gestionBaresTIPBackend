package com.tip.gestionBares.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

public class TicketProductoId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "idTicket")
    private Long idTicket;

	@Column(name = "idProducto")
    private Long idProducto;

    public TicketProductoId() {

    }
    
    public TicketProductoId(Long idTicket, Long idProducto) {
        this.idTicket = idTicket;
        this.idProducto = idProducto;
    }

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	} 
    
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) 
            return false;

        TicketProductoId that = (TicketProductoId) o;
        return Objects.equals(idProducto, that.idProducto) && 
               Objects.equals(idTicket, that.idTicket);
    }

    @Override   
    public int hashCode() {
        return Objects.hash(idTicket, idProducto);
    }

}
