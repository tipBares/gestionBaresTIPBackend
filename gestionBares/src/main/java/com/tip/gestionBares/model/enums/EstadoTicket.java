package com.tip.gestionBares.model.enums;

public enum EstadoTicket {
    PENDIENTE,      // El pedido entró a la cocina
    PREPARANDO,     // El cocinero empezó a trabajar
    LISTO,          // El plato está en el mostrador para retirar
    ENTREGADO,      // El mozo ya lo llevó a la mesa
    CANCELADO       // Por si hubo un error
}
