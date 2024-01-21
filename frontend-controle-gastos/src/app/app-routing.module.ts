import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './components/Conta/cadastro/cadastro.component';
import { LoginComponent } from './components/Conta/login/login.component';
import { TelaPrincipalComponent } from './components/tela-principal/tela-principal.component';

const routes: Routes = [{
  path:'',
  pathMatch: 'full',
  redirectTo: 'cadastro'
},
{
  path: 'cadastro',
  component: CadastroComponent
},
{
  path: 'login',
  component: LoginComponent
},
{
  path: 'telaPrincipal',
  component: TelaPrincipalComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
