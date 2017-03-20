describe('cepApp E2E test', () => {
  beforeEach(() => {
    browser.get('/');
  });

  it('should have a title', () => {
    expect(browser.getTitle()).toEqual('Cep App');
  });

  it('should have <tov-nav>', () => {
    expect(element(by.css('cep-app top-nav')).isPresent()).toBe(true);
  });

  it('should have <sidebar-menu>', () => {
    expect(element(by.css('cep-app sidebar-menu')).isPresent()).toBe(true);
  });

  it('should have a main title', () => {
    expect(element(by.css('#main h1')).getText()).toEqual('Cep App');
  });

  it('should have a main div', () => {
    expect(element(by.css('cep-app div#main')).isPresent()).toBe(true);
  });
});
