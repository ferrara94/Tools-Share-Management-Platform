import {test as base} from '@playwright/test';
import { RegisterForm } from '../page-object/register-form';

export const test = base.extend<{registerForm: RegisterForm}>({
    registerForm: async ({page}, use) => {
        const registerForm = new RegisterForm(page);
        await use(registerForm);
    },
});