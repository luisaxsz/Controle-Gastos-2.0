import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-mensagens-erro',
  templateUrl: './mensagens-erro.component.html',
  styleUrls: ['./mensagens-erro.component.css']
})
export class MensagensErroComponent implements OnInit {

  constructor() { }

  @Input() mensagem: string = ''

  ngOnInit(): void {
  }

}
