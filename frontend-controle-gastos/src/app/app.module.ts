import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/Conta/login/login.component';
import { CadastroComponent } from './components/Conta/cadastro/cadastro.component';
import { FormsModule } from '@angular/forms';
import { MensagensErroComponent } from './components/mensagens-erro/mensagens-erro.component';
import { ConfirmaSenhaDirective } from './directive/confirma-senha.directive';
import { ContaComponent } from './components/Conta/conta/conta.component';
import { HttpClientModule } from '@angular/common/http';
import { AdicionarTransacaoComponent } from './components/Transacoes/adicionar-transacao/adicionar-transacao.component';
import { EditarTransacaoComponent } from './components/Transacoes/editar-transacao/editar-transacao.component';
import { ListarTransacoesComponent } from './components/Transacoes/listar-transacoes/listar-transacoes.component';
import { HeaderComponent } from './components/header/header.component';
import { EditarContaComponent } from './components/Conta/editar-conta/editar-conta.component';
import { TrasacoesComponent } from './components/Transacoes/trasacoes/trasacoes.component';
import { DeletarTransacaoComponent } from './components/Transacoes/deletar-transacao/deletar-transacao.component';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CadastroComponent,
    MensagensErroComponent,
    ConfirmaSenhaDirective,
    ContaComponent,
    AdicionarTransacaoComponent,
    EditarTransacaoComponent,
    ListarTransacoesComponent,
    HeaderComponent,
    EditarContaComponent,
    TrasacoesComponent,
    DeletarTransacaoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatTableModule, RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
