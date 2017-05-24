import { TestBed, inject } from '@angular/core/testing';
import { provideRoutes } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { SidebarMenuComponent } from './shared/sidebar-menu/sidebar-menu.component';
import { TopNavComponent } from './shared/top-nav/top-nav.component';
import { AppComponent } from './app.component';

describe('TodoApp Unit Test', () => {
  // provide our implementations or mocks to the dependency injector
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [AppComponent, SidebarMenuComponent, TopNavComponent],
      providers: [provideRoutes([])]
    });
  });

  it('nada a testar em app.component', () => {
    expect(true).toBe(true);
  });
});