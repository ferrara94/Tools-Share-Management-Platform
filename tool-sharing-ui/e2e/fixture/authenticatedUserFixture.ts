import { LoginForm } from "../page-object/login-form";
import { RegisterForm } from "../page-object/register-form";
import {test as base} from '@playwright/test';

type authenticatedUser = {
  loginForm: LoginForm;
  registerForm: RegisterForm;
  authenticatedUser: {
    loginForm: LoginForm;
    registerForm: RegisterForm;
  };
};

export const test = base.extend<authenticatedUser>({
  loginForm: async ({ page }, use) => {
    const loginForm = new LoginForm(page);
    await use(loginForm);
  },

  registerForm: async ({ page }, use) => {
    const registerForm = new RegisterForm(page);
    await use(registerForm);
  },

  authenticatedUser: async ({ registerForm, loginForm }, use) => {
    await use({ loginForm, registerForm });
  },
});