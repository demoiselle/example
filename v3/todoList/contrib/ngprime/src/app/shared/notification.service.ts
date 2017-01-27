import { Injectable } from '@angular/core';
//import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Injectable()
export class NotificationService {
  constructor() {
  }

  success(text: string) {
    //this.toastr.success(text);
  }

  error(text: string) {
    //this.toastr.error(text);
  }

  info(text: string) {
    ///this.toastr.info(text);
  }

  warning(text: string) {
    //this.toastr.warning(text);
  }
}
