import { Component, ElementRef } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './app-sidebar.component.html'
})
export class AppSidebar {
  /**
   * app-routing.module children routes for automatic addition to the navigation
   */
  public routes: any[] = [];

  constructor(private el: ElementRef, private router: Router) {

    for (let i = 0; i < this.router.config.length; i++) {
      let rootRoute = this.router.config[i];

      // add root routes (not lazy loaded module)
      if (rootRoute.data && rootRoute.data.showInSidebar) {
          this.routes.push(rootRoute);
      }

      // add children routes (lazy loaded module)
      if (rootRoute.children && rootRoute.children.length) {
        for (let j = 0; j < rootRoute.children.length; j++) {
          if (rootRoute.children[j].data && rootRoute.children[j].data.showInSidebar) {
            this.routes.push(rootRoute.children[j]);
          }
        }
      }

    }
  }

  //wait for the component to render completely
  ngOnInit(): void {
    var nativeElement: HTMLElement = this.el.nativeElement,
    parentElement: HTMLElement = nativeElement.parentElement;
    // move all children out of the element
    while (nativeElement.firstChild) {
      parentElement.insertBefore(nativeElement.firstChild, nativeElement);
    }
    // remove the empty element(the host)
    parentElement.removeChild(nativeElement);
  }
}
