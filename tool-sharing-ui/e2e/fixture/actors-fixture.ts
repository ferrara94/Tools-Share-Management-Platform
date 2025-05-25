import { Browser, test as base } from '@playwright/test';
import { Actor } from '@testla/screenplay-playwright';
import { BrowseTheWeb } from '@testla/screenplay-playwright/web';

type Actors = {
    ADMIN: Actor;
    USER: Actor;
}

const createActors = async (
    browser: Browser,
    actorName: string
) => {
    const context = await browser.newContext();
    const page = await context.newPage();

    const actor = Actor.named(actorName).can(BrowseTheWeb.using(page));

    return {
        actor,
        cleanup: async () => {
            await context.close();
        }
    };
}; 

export const test = base.extend<Actors>({
    ADMIN: async ({browser}, use) => {
        const {actor, cleanup} = await createActors(browser, 'ADMIN');
        await use(actor);
        await cleanup();
    },
    USER: async ({ browser }, use) => {
        const { actor, cleanup } = await createActors(browser, 'USER');
        await use(actor);
        await cleanup();
    },
});

export { expect } from '@playwright/test';
