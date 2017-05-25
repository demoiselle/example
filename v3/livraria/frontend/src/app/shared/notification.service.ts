import { Injectable } from '@angular/core';
import { ToastyService, /*ToastOptions, ToastData*/ } from 'ng2-toasty';
import { ToastCommunicationService } from './toast-communication.service';
import { Subject }  from 'rxjs/Subject';
import { Http } from '@angular/http';
import { Observable, Subscription } from 'rxjs';
import { ExceptionService } from '@demoiselle/http';

@Injectable()
export class NotificationService {
  validationSubscription: Subscription;
  generalErrorsSubscription: Subscription;

  themes = [{
    name: 'Default Theme',
    code: 'default'
  }, {
    name: 'Material Design',
    code: 'material'
  }, {
    name: 'Bootstrap 3',
    code: 'bootstrap'
  }];

  types = [{
    name: 'Default',
    code: 'default',
  }, {
    name: 'Info',
    code: 'info'
  }, {
    name: 'Success',
    code: 'success'
  }, {
    name: 'Wait',
    code: 'wait'
  }, {
    name: 'Error',
    code: 'error'
  }, {
    name: 'Warning',
    code: 'warning'
  }];

  positions = [{
    name: 'Top Left',
    code: 'top-left',
  }, {
    name: 'Top Center',
    code: 'top-center',
  }, {
    name: 'Top Right',
    code: 'top-right',
  }, {
    name: 'Bottom Left',
    code: 'bottom-left',
  }, {
    name: 'Bottom Center',
    code: 'bottom-center',
  }, {
    name: 'Bottom Right',
    code: 'bottom-right',
  }, {
    name: 'Center Center',
    code: 'center-center',
  }];

  position: string = this.positions[2].code;
  
  options = {
    title: 'Notificação',
    msg: 'mensagem aqui',
    showClose: true,
    timeout: 5000,
    theme: this.themes[1].code,
    type: this.types[0].code
  };
  
  constructor(private toastyService: ToastyService,
              /*private toastOptions: ToastOptions, private toastData: ToastData,*/
              private toastCommunicationService: ToastCommunicationService,
              private http: Http, private exceptionService: ExceptionService) {
    this.validationSubscription = this.exceptionService.validation$.subscribe(
      error => this.showValidationErrors(error)
    );
    this.generalErrorsSubscription = this.exceptionService.generalErrors$.subscribe(
      error => this.showGeneralErrors(error)
    );
    this.changePosition(this.position);
  }

  getToastOptions(title, msg) {
    let interval = 1000;
    let seconds = this.options.timeout / 1000;
    let subscription: Subscription;

    let toastOptions = {
      title: title,
      msg: msg,
      showClose: this.options.showClose,
      timeout: this.options.timeout,
      theme: this.options.theme,
      position: this.position,
      onAdd: (toast) => {
        console.log('Toast ' + toast.id + ' has been added!');
        // Run the timer with 1 second iterval
        let observable = Observable.interval(interval).take(seconds);
        // Start listen seconds bit
        subscription = observable.subscribe((count: number) => {
          // Update title
          //toast.title = this.getTitle(seconds - count - 1 || 0);
          // Update message
          //toast.msg = this.getMessage(seconds - count - 1 || 0);
        });
      },
      onRemove: function(toast) {
        console.log('Toast ' + toast.id + ' has been removed!');
        // Stop listenning
        subscription.unsubscribe();
      }
    };
    return toastOptions;
  }

  getTitle(num: number): string {
      return 'Countdown: ' + num;
  }

  getMessage(num: number): string {
      return 'Seconds left: ' + num;
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
    this.options.type = this.types[2].code;
    this.toastyService.success(this.getToastOptions('Sucesso', text));
  }

  error(text: string) {
    this.options.type = this.types[4].code;
    this.toastyService.error(this.getToastOptions('Erro', text));
  }

  info(text: string) {
    this.options.type = this.types[1].code;
    this.toastyService.info(this.getToastOptions('Informação', text));
  }

  warning(text: string) {
    this.options.type = this.types[5].code;
    this.toastyService.warning(this.getToastOptions('Aviso', text));
  }

  wait(text: string) {
    this.options.type = this.types[3].code;
    this.toastyService.warning(this.getToastOptions('Aguarde', text));
  }

  changePosition(position) {
    this.position = position;
    // Update position of the Toasty Component
    this.toastCommunicationService.setPosition(this.position);
  }
}
