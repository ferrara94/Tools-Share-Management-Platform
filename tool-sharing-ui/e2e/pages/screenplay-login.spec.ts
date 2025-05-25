import { test } from "../fixture/actors-fixture";
import { SeeErrorMessage } from "../screenplay/questions/SeeErrorMessage";
import { Login } from "../screenplay/tasks/FillLoginForm";
import { expect } from '@playwright/test'


test('USER cannot log in with bad credentials', async ({ USER }) => {
    const email: string = 'john@gmail.com';
    const password: string = 'password';

    await USER.attemptsTo(
        Login.withCredentials(email, password)
    );

    const isErrorVisible = await USER.asks(SeeErrorMessage.containing('Bad credentials'));
    await expect(isErrorVisible).toBe(true);

});