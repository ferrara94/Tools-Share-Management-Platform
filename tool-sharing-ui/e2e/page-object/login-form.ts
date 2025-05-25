import { Locator, Page, expect } from "@playwright/test";

export class LoginForm{
    constructor(private page: Page){

    }

      async fillInTheForm(email: string, password: string){
        await this.setEmailAddress(email);
        await this.setPassword(password);
      }

    async setEmailAddress(email: string){
        await this.page.getByRole('textbox', { name: 'Email address' }).focus();
        await this.page.getByRole('textbox', { name: 'Email address' }).fill(email);
    }

    async setPassword(password: string){
        await this.page.getByRole('textbox', { name: 'Password' }).focus();
        await this.page.getByRole('textbox', { name: 'Password' }).fill(password);
    }

    async assertEmailValue(email: string){
        const emailInput: Locator = this.page.getByRole('textbox', { name: 'Email address' });
        expect(emailInput).toHaveValue(email);
    }

    async assertPasswordValue(password: string){
        const passwordInput: Locator = this.page.getByRole('textbox', { name: 'Password' });
        expect(passwordInput).toHaveValue(password);
    }


}