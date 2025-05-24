
import {test as base} from '@playwright/test';
import { LoginForm } from '../page-object/login-form';

export const test = base.extend<{loginForm: LoginForm}>({
    loginForm: async ({page}, use) => {
        const loginForm = new LoginForm(page);
        await use(loginForm);
    },
});