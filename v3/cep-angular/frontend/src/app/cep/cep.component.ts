import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { CepService } from './cep.service';
import { Logradouro } from './cep.model';

@Component({
  selector: 'my-cep',
  templateUrl: './cep.component.html',
  styleUrls: [
    './cep.component.scss',
    './cep.component-old.css',
    '../shared/styles/material.css'
  ]
})
export class CepComponent implements OnInit {
  
  public log: Logradouro;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  @Output()
  public selectedUf;
  public ufs = [
    {ufeNo: 'loading', ufeSg: 'loading'}
  ];

  constructor(private service: CepService, private notificationService: NotificationService) {
    this.selectedUf = this.ufs[0].ufeNo;
  }

  ngOnInit() {
    this.log = new Logradouro();
    this.loadUf();
    //this.searchLocalidade('PR');
  }

  loadUf(): void {
    this.service.getUF().subscribe(
      (result) => {
        console.log('result');
        console.log(result);
        this.ufs = result;
        this.selectedUf = this.ufs[0].ufeNo;
      },
      (error) => {
        console.log('error');
        console.log(error);
      }
    );
  }

  searchCep(cep: string): void {
    console.log('CEP');
    console.log(cep);
    this.service.getByCEP(cep).subscribe(
      (result) => {
        this.log.rua = result.logNo;
        this.log.complemento = result.logComplemento;
        this.log.tipo = result.logTipoLogradouro;
        this.log.bairro = (result.baiNuSequencialIni) ? result.baiNuSequencialIni.baiNo : '';
        this.log.uf = result.ufeSg;
        this.ufs.forEach(element => {
          if (element.ufeSg == result.ufeSg) {
            this.selectedUf = element.ufeNo;
          }
        });
      },
      (error) => {
        console.log('error');
        console.log(error);
      }
    );
  }

  searchUF(uf: string): void {
    this.service.getByUF(uf).subscribe(
      (result) => {
        console.log('result localidade');
        console.log(result);
        this.log.rua = result.logNo;
        this.log.complemento = result.logComplemento;
        this.log.tipo = result.logTipoLogradouro;
        this.log.bairro = (result.baiNuSequencialIni) ? result.baiNuSequencialIni.baiNo : '';
        this.log.uf = result.ufeSg;
      },
      (error) => {
        console.log('error');
        console.log(error);
      }
    );
  }

  searchLocalidade(uf: string): void {
    this.service.getLocalidadeByUF(uf).subscribe(
      (result) => {
        console.log('result');
        console.log(result);
      },
      (error) => {
        console.log('error');
        console.log(error);
      }
    );
  }

  changeRole(event) {
    this.selectedUf = event.target.value;
  }
}
