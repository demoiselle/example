import { Component, Input, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { ExceptionService } from '@demoiselle/http';

@Component({
  selector: 'error-field',
  template: `
      <div *ngIf="error" class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close" (click)="close()">
          <span aria-hidden="true">&times;</span>
        </button>
        <span>{{error}}</span>
      </div>
  `
})
export class ErrorFieldComponent implements OnInit {

  validationSubscription: Subscription;
  error:string;
  @Input() field: string;

  constructor(private http: Http, private exceptionService: ExceptionService) {}
  ngOnInit() {
    this.validationSubscription = this.exceptionService.validation$.subscribe(
      error => this.processValidation(error)
    );
  }
  ngOnDestroy() {
    this.validationSubscription.unsubscribe();
  }

  processValidation(errors: any) {
    for (let error of errors) {
      // search for the field error 
      if (error.error_field == this.field) {
        this.error = error.error_description || 'Erro!';
      }
    }
  }

  close() {
    this.error = null;
  }
}