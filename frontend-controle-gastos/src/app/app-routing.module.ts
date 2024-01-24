import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './components/Conta/cadastro/cadastro.component';
import { LoginComponent } from './components/Conta/login/login.component';
import { UserGuardGuard } from './components/Conta/user-guard.guard';
import { EditarContaComponent } from './components/Conta/editar-conta/editar-conta.component';
import { ContaComponent } from './components/Conta/conta/conta.component';
import { ListarTransacoesComponent } from './components/Transacoes/listar-transacoes/listar-transacoes.component';

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
  component: ContaComponent,
  canActivate: [UserGuardGuard],
  children:[
    {
      path:'editarConta/:id',
      component: EditarContaComponent
    },
    {
      path:'transacoes',
      component:ListarTransacoesComponent
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
