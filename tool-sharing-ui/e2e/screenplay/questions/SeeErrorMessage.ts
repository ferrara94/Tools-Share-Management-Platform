import { Actor, Question } from "@testla/screenplay-playwright";
import { BrowseTheWeb } from "@testla/screenplay-playwright/web";

export class SeeErrorMessage extends Question<boolean> {
    constructor(private expectedText: string) {
        super();
    }

    public static containing(text: string): SeeErrorMessage {
        return new SeeErrorMessage(text);
    }

    public async answeredBy(actor: Actor): Promise<boolean> {
        const page = BrowseTheWeb.as(actor).getPage();
        const locator = page.locator('[data-testid=errorMessageArea]');
        await locator.waitFor({ state: 'visible' });
        const visible = await locator.isVisible();
        const text = await locator.textContent();
        return visible && text?.includes(this.expectedText) === true;
    }
}