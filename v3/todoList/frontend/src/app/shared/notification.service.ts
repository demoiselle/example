import { Injectable } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { ExceptionService } from '@demoiselle/http';

@Injectable()
export class NotificationService {
  validationSubscription: Subscription;
  generalErrorsSubscription: Subscription;
  constructor(private toastr: ToastsManager, private http: Http, private exceptionService: ExceptionService) {
    this.validationSubscription = this.exceptionService.validation$.subscribe(
      error => this.showValidationErrors(error)
    );
    this.generalErrorsSubscription = this.exceptionService.generalErrors$.subscribe(
      error => this.showGeneralErrors(error)
    );
  }

 showValidationErrors(errors: any) {
    for (let error of errors) {
        this.error('Erro de validação! Campo: ' + error.error_field + ' , Descrição: ' + error.error_description);
    }
  }
  showGeneralErrors(errors: any) {

    for (let error of errors) {
      let description = '';
      if (typeof error.error_description === "string") {
        description = error.error_description;
      } else if (typeof error.error_description === "object" && error.error_description.error_code) {
        description = 'Código de erro: ' + error.error_description.error_code;
      }
      this.error('Erro: ' + error.error + ' ' + description);
    }
  }

  success(text: string) {
    this.toastr.success(text);
  }

  error(text: string) {
    this.toastr.error(text);
  }

  info(text: string) {
    this.toastr.info(text);
  }

  warning(text: string) {
    this.toastr.warning(text);
  }
}
