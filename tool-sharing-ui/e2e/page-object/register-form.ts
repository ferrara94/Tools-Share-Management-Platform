import { Locator, Page, expect } from '@playwright/test';

export class RegisterForm {
  constructor(private page: Page) {}

  async fillInTheForm(firstName: string, lastName: string, email: string, password: string){
    await this.page.getByRole('textbox', { name: 'Firstname' }).fill(firstName);
    await this.page.getByRole('textbox', { name: 'Lastname' }).fill(lastName);
    await this.page.getByRole('textbox', { name: 'Email address' }).fill(email);
    await this.page.getByRole('textbox', { name: 'Password' }).fill(password);
  }

  async isTheHeaderVisible() {
    await this.page.getByRole('heading', { name: 'Create an account' }).isVisible();
  }

  async assertHeaderContains(text: string) {
    this.isTheHeaderVisible();
    const heading: Locator = this.page.getByRole('heading');
    await heading.focus();
    await expect(heading).toHaveText(text);
  }
}
