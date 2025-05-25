import { Page } from "@playwright/test";
import { Action, Actor } from "@testla/screenplay-playwright";
import { BrowseTheWeb } from "@testla/screenplay-playwright/web";

export class SetPassword extends Action{
    constructor(private readonly password: string) {
        super();
    }

    public static to(email: string): SetPassword {
        return new SetPassword(email);
    }

    public async performAs(actor: Actor): Promise<any> {
        const page: Page = BrowseTheWeb.as(actor).getPage();
        const passwordInput = page.getByRole('textbox', { name: 'Password' });

        await passwordInput.focus();
        await passwordInput.fill(this.password);
    }
}
