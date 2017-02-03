import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'error-form',
  template: `
      <div></div>
  `
})
export class ErrorFormComponent {
  
  validationSubscription: Subscription;
  @Input() form: NgForm;

  constructor(private http: Http) {}
  ngOnInit() {
    this.validationSubscription = this.http.validation$.subscribe(
      error => this.processValidation(error)
    );
  }
  ngOnDestroy() {
    this.validationSubscription.unsubscribe();
  }

  
  processValidation(errors: any) {
    for (let error of errors) {
      console.log(error);
      console.log(this.form.controls);
      if (this.form.controls[error.error_field]) {
        console.log(this.form.controls[error.error_field]);
        this.form.controls[error.error_field].setErrors({remote: error.error_description});
        
      }
    }
  }
 
}