import { Injectable } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { ExceptionService } from '@demoiselle/http';
import { Router } from '@angular/router';

@Injectable()
export class NotificationService {
  errorsSubscription: Subscription;
  constructor(private toastr: ToastsManager , private exceptionService: ExceptionService, private router: Router) {
    this.errorsSubscription = this.exceptionService.errors$.subscribe(
      error => this.handleError(error)
    );
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

  handleError(httpError: any) {

    if (httpError == null) {
      return;
    }

    if (httpError.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      this.error(`An error occurred: ${httpError.error.message}`);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      switch (httpError.status) {
        case 401:
        case 403:
          this.router.navigate(['/login']);
          break;
        case 412:
          for (const err of httpError.error) {
            const parts = err.error.split('.');
            err.error_method = parts[0] || null;
            err.error_entity = parts[1] || null;
            err.error_field = parts[2] || null;

            this.error('Erro de validação! Campo: ' + err.error_field + ' , Descrição: ' + err.error_description);
          }
          break;
        default:
          for (const error of httpError.error) {
            let description = '';
            if (typeof error.error_description === 'string') {
              description = error.error_description;
            } else if (typeof error.error_description === 'object' && error.error_description.error_code) {
              description = 'Código de erro: ' + error.error_description.error_code;
            }
            this.error('Erro: ' + error.error + ' ' + description);
          }

          break;
      }
    }
  }


}
