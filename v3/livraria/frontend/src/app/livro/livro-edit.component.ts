import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { LivroService } from './livro.service';
import { Livro } from './livro.model';

@Component({
  selector: 'my-livro-edit',
  templateUrl: './livro-edit.component.html'
})
export class LivroEditComponent implements OnInit {
  livro: Livro = new Livro();
  id: number;
  livroLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: LivroService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.livro.id = (<Livro> params).id;
        this.livro.description = (<Livro> params).description;
      }
    });
  }

  loadLivro() {
    this.service.get(this.id)
      .subscribe(
      (Livro: Livro) => {
        this.livro = Livro;
        this.livroLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(livro:Livro) {
    if (!livro.id) {
      delete livro.id;
      this.service.create(livro).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(livro).subscribe(
        (result) => {
          this.notificationService.success('Item alterado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível alterar!');
        }
      );
    }
  }
  
  goBack() {
    this.router.navigate(['livro']);
  }

}
