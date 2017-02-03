import { Injectable } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';

@Injectable()
export class NotificationService {
  validationSubscription: Subscription;
  constructor(private toastr: ToastsManager, private http: Http) {
    this.validationSubscription = this.http.validation$.subscribe(
      error => this.showValidationErrors(error)
    );
  }

  showValidationErrors(errors: any) {
    for (let error of errors) {
        this.error('Erro de validação! Campo: ' + error.error_field + ' , Descrição: ' + error.error_description);
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
