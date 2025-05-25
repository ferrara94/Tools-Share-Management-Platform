import { Page } from "@playwright/test";
import { Action, Actor } from "@testla/screenplay-playwright";
import { BrowseTheWeb } from "@testla/screenplay-playwright/web";

export class SetEmail extends Action{
    constructor(private readonly email: string) {
        super();
    }

    public static to(email: string): SetEmail {
        return new SetEmail(email);
    }

    public async performAs(actor: Actor): Promise<any> {
        const page: Page = BrowseTheWeb.as(actor).getPage();
        const emailInput = page.getByRole('textbox', { name: 'Email address' });

        await emailInput.focus();
        await emailInput.fill(this.email);
    }
    
}