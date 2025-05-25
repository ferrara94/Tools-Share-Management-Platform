import { test } from '../fixture/register-fixture';

test.describe('Register Function', () => {

    test('should open the register form', async({page, registerForm}) => {

            const headerText: string = 'Create an account';
    
            await page.goto('');    
            await page.getByTestId('btn-register').click();

            await registerForm.isTheHeaderVisible();
            await registerForm.assertHeaderContains(headerText);
    });

    test('should fill in the register form', async({page, registerForm}) => {

            const name: string = 'Mark';
            const surname: string = 'Rossi';
            const email: string = 'mark@example.com';
            const password: string = 'password';

            await page.goto('');    
            await page.getByTestId('btn-register').click();

            await registerForm.isTheHeaderVisible();
            
            await registerForm.fillInTheForm(
                name,
                surname,
                email,
                password
            );

            await page.getByRole('button', { name: ' Register' }).click();
    });

});