import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';

@Directive({
  selector: '[appConfirmaSenhaValidator]',
  providers:[{
    provide: NG_VALIDATORS,
    useExisting: ConfirmaSenhaDirective,
    multi: true
  }]
})
export class ConfirmaSenhaDirective implements Validator {

  constructor() { }
  validate(control: AbstractControl<any, any>): ValidationErrors | null {
    const senha = control.parent?.get('senha')?.value;
    const confirmaSenha = control.parent?.get('confirmarSenha')?.value;

    if(senha == confirmaSenha){
      return null;
    }else{
      return {appConfirmaSenhaValidator: true}
    }
  }
}
