import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "./guard/auth.guard";
import {TransacaoComponent} from "./components/transacao/transacao.component";

const routes: Routes = [
  {
    path: '',
    component: TransacaoComponent,
    canActivate: [AuthGuard],
    children:[{}]
  },
  {path:'**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
