import { Page } from "@playwright/test";

export class LoginForm{
    constructor(private page: Page){

    }

    async setEmailAddress(email: string){
        await this.page.getByRole('textbox', { name: 'Email address' }).focus();
        await this.page.getByRole('textbox', { name: 'Email address' }).fill(email);
    }

    async setPassword(password: string){
        await this.page.getByRole('textbox', { name: 'Password' }).focus();
        await this.page.getByRole('textbox', { name: 'Password' }).fill(password);
    }


}