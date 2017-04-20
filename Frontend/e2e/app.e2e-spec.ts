import { ACoffeeScalePage } from './app.po';

describe('acoffee-scale App', () => {
  let page: ACoffeeScalePage;

  beforeEach(() => {
    page = new ACoffeeScalePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
