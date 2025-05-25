import { Page } from "@playwright/test";
import { Action, Actor } from "@testla/screenplay-playwright";
import { BrowseTheWeb } from "@testla/screenplay-playwright/web";

export class ClickOnSignIn extends Action{
    constructor() {
        super();
    }

    public static now(): ClickOnSignIn {
        return new ClickOnSignIn();
    }

    public async performAs(actor: Actor): Promise<void> {
        const page: Page = BrowseTheWeb.as(actor).getPage();

        const signInButton = page.getByTestId('btn-login');

        await signInButton.focus();
        await signInButton.click();
    }
}