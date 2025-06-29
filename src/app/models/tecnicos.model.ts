export interface Tecnico {
    id: string;
    codigo: string;
    nombre: string;
    apellido: string;
    especialidad: string;
    foto: string;
}

export interface Ticket {
    id: number;
    fecha: Date;
    cantidad: number;
    type: string;
    status: string;
    file: string;
    tecnico: Tecnico;
}

export enum TicketType {
    INSTALACION = 0, MANTENIMIENTO = 1, VENTA = 2, COTIZACION = 3, DIAGNOSTICO = 4
}

export enum TicketStatus {
    PENDIENTE = 0, EN_PROCESO = 1, FINALIZADO = 2, CANCELADO = 3
}