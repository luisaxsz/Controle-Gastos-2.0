import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TransacoesService } from '../../../services/transacoes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-transacao',
  templateUrl: './adicionar-transacao.component.html',
  styleUrls: ['./adicionar-transacao.component.css'],
})
export class AdicionarTransacaoComponent implements OnInit {

  id = this.route.snapshot.paramMap.get('id');

  constructor(
    private serviceTransacao: TransacoesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {}

  adicionarTransacao(form: NgForm) {
    if (form.valid) {
      this.serviceTransacao
        .adicionarTransacao(form.value, parseInt(this.id!))
        .subscribe(() =>
          this.router.navigate([
            'telaPrincipal/transacoes/',
            parseInt(this.id!),
          ])
        );
    }
  }

  cancelar(): void{
    this.router.navigate(['telaPrincipal/transacoes', this.id])
  }
}
