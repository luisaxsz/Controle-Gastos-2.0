import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './components/Conta/cadastro/cadastro.component';
import { LoginComponent } from './components/Conta/login/login.component';
import { TelaPrincipalComponent } from './components/tela-principal/tela-principal.component';
import { UserGuardGuard } from './components/Conta/user-guard.guard';

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
  component: TelaPrincipalComponent,
  canActivate: [UserGuardGuard]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
