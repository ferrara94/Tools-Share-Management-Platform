import { expect } from '@playwright/test'
import { LoginForm } from '../page-object/login-form';
import { test } from '../fixture/login-fixture';

test.describe('Login Function', () => {
    
    test('should show errors when login attempt fails', async({page}) => {

        await page.goto('');
        
        await expect(page.locator('[data-testid=errorMessageArea]')).not.toBeVisible();

        await page.locator('[data-testid=btn-login]').click();
        //await page.getByTestId('btn-login').click();

        await expect(page.locator('[data-testid=errorMessageArea]')).toBeVisible();
    });

    test('should fill in the form with bad credentials', async({page, loginForm}) => {

        await page.goto('');

        await expect(page.locator('[data-testid=errorMessageArea]')).not.toBeVisible();

        await loginForm.setEmailAddress('john@gmail.com');
        await loginForm.setPassword('password');

        await page.getByTestId('btn-login').click();

        await expect(page.locator('[data-testid=errorMessageArea]')).toBeVisible();
        await expect(page.locator('[data-testid=errorMessageArea]')).toContainText(' Bad credentials');

    });


});