import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListarTransacoesComponent} from "./components/Transacoes/listar-transacoes/listar-transacoes.component";
import {AuthGuard} from "./guard/auth.guard";

const routes: Routes = [
  {
    path: '',
    component: ListarTransacoesComponent,
    canActivate: [AuthGuard]
  },
  {path:'**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
