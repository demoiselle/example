import { TestBed, inject } from '@angular/core/testing';
import { provideRoutes } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { SidebarMenuComponent } from './shared/sidebar-menu/sidebar-menu.component';
import { TopNavComponent } from './shared/top-nav/top-nav.component';
import { AppComponent } from './app.component';

describe('MyApp Unit Test', () => {
  // provide our implementations or mocks to the dependency injector
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [AppComponent, SidebarMenuComponent, TopNavComponent],
      providers: [provideRoutes([])]
    });
  });

  it('should write your unit test here', () => {
    expect(true).toBe(false);
  });
});
