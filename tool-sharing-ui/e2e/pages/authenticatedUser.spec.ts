import { expect } from '@playwright/test'
import { test } from '../fixture/authenticatedUserFixture';

test.describe('Register and Login', () => {
        test('should register and login in with a disabled account', async({page, authenticatedUser}) => {
           
            const name: string = 'Lucas';
            const surname: string = 'Sperling';
            const email: string = 'lucas@example.com';
            const password: string = 'password';

            await page.goto('');    
            await page.getByTestId('btn-register').click();

            await authenticatedUser.registerForm.isTheHeaderVisible();

            await authenticatedUser.registerForm.fillInTheForm(
                name,
                surname,
                email,
                password
            );

            await page.getByTestId('btn-register').focus();
            await page.getByTestId('btn-register').click();

            await page.goto('');

            await expect(page.locator('[data-testid=errorMessageArea]')).not.toBeVisible();

            await authenticatedUser.loginForm.fillInTheForm(
                email,surname
            );

            await page.getByTestId('btn-login').click();

            await expect(page.locator('[data-testid=errorMessageArea]')).toBeVisible();
            await expect(page.locator('[data-testid=errorMessageArea]')).toContainText(' User is disabled');

        });
    
});