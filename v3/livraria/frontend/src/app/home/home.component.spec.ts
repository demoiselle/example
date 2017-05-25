/*
import { TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { HttpModule } from '@angular/http';

import { SecurityModule } from '@demoiselle/security';

import { HomeComponent } from './home.component';

describe('[Unit Test] - Home Component', () => {
  const html = '<dml-home></dml-home>';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SecurityModule, HttpModule],
      declarations: [HomeComponent, TestComponent]
    });
    TestBed.overrideComponent(TestComponent, { set: { template: html } });
  });

  it('should have text home', () => {
    const fixture = TestBed.createComponent(TestComponent);
    fixture.detectChanges();
    expect(fixture.nativeElement.children[0].textContent).toContain('Home');
  });

});

@Component({ selector: 'my-test', template: '' })
class TestComponent { }
*/