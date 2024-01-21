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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CadastroComponent,
    MensagensErroComponent,
    ConfirmaSenhaDirective,
    ContaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
