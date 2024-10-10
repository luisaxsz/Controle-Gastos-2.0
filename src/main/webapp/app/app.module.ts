import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {MensagensErroComponent} from './components/mensagens-erro/mensagens-erro.component';
import {ConfirmaSenhaDirective} from './directive/confirma-senha.directive';
import {HttpClientModule} from '@angular/common/http';
import {AdicionarTransacaoComponent} from './components/Transacoes/adicionar-transacao/adicionar-transacao.component';
import {EditarTransacaoComponent} from './components/Transacoes/editar-transacao/editar-transacao.component';
import {ListarTransacoesComponent} from './components/Transacoes/listar-transacoes/listar-transacoes.component';
import {HeaderComponent} from './components/header/header.component';
import {TrasacoesComponent} from './components/Transacoes/trasacoes/trasacoes.component';
import {DeletarTransacaoComponent} from './components/Transacoes/deletar-transacao/deletar-transacao.component';
import {MatMenuModule} from '@angular/material/menu';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {environment} from "../environments/environment";

export function initializeKeycloak(
  keycloak: KeycloakService
) {
  return () =>
    keycloak.init({
      config: {
        url: environment.keycloak.config.url,
        realm: environment.keycloak.config.realm,
        clientId: environment.keycloak.config.clientId,
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    MensagensErroComponent,
    ConfirmaSenhaDirective,
    AdicionarTransacaoComponent,
    EditarTransacaoComponent,
    ListarTransacoesComponent,
    HeaderComponent,
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
    MatTableModule,
    KeycloakAngularModule,
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
