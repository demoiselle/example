describe('TodoApp E2E test', () => {
  beforeEach(() => {
    browser.get('/');
  });

  it('should have a title', () => {
    expect(browser.getTitle()).toEqual('Todo App');
  });

  it('should have <tov-nav>', () => {
    expect(element(by.css('Todo-app top-nav')).isPresent()).toBe(true);
  });

  it('should have <sidebar-menu>', () => {
    expect(element(by.css('Todo-app sidebar-menu')).isPresent()).toBe(true);
  });

  it('should have a main title', () => {
    expect(element(by.css('#main h1')).getText()).toEqual('Todo App');
  });

  it('should have a main div', () => {
    expect(element(by.css('Todo-app div#main')).isPresent()).toBe(true);
  });
});
