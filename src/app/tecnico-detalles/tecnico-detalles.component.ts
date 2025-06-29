import { Component } from '@angular/core';
import { Ticket } from '../models/tecnicos.model';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { TecnicosService } from '../services/tecnicos.service';
import { Tecnico } from '../models/tecnicos.model'; // Adjust the import path as necessary

@Component({
  selector: 'app-tecnico-detalles',
  templateUrl: './tecnico-detalles.component.html',
  styleUrl: './tecnico-detalles.component.css'
})
export class TecnicoDetallesComponent {
  tecnico!: Tecnico;
  tecnicoCodigo!: string;
  ticketsTecnico!: Array<Ticket>;
  ticketsDataSource!: MatTableDataSource<Ticket>;

  public displayedColumns: string[] = ['id', 'fecha', 'cantidad', 'type', 'status', 'nombre'];

  constructor(private activatedRoute: ActivatedRoute, private tecnicosService: TecnicosService, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.tecnicoCodigo = params['codigo'];
      this.tecnicosService.getTicketsDeTecnico(this.tecnicoCodigo).subscribe({
        next: value => {
          this.ticketsTecnico = value;
          this.ticketsDataSource = new MatTableDataSource<Ticket>(this.ticketsTecnico);
        },
        error: err => {
          console.error('Error al obtener los tickets del técnico', err);
        }
      });
    });
  }

  agregaTicket() {
    this.router.navigateByUrl(`admin/new-ticket/${this.tecnicoCodigo}`);

  }
}

