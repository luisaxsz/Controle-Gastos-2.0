import { Component, OnInit } from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-transacao',
  template: `
    <div>
      <router-outlet></router-outlet>
    </div>
  `,
  standalone: true,
  imports: [
    RouterOutlet
  ]
})
export class TransacaoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
