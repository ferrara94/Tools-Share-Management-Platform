import { test, expect } from '@playwright/test';

test.describe('Screenshot - Login Page', () => {
  test('should create a login page screenshot', async ({ page }) => {
    await page.goto('');

    const loginHeading = page.getByRole('heading', { name: 'Login' });
    await expect(loginHeading).toBeVisible();

    await page.screenshot({
      path: './e2e/screenshots/test-results/login-screenshot.png',
      fullPage: true,
    });
  });

  test('should compare the actual page with previus version of it', async ({
    page,
  }) => {
    await page.goto('');
    const loginHeading = page.getByRole('heading', { name: 'Login' });
    await expect(loginHeading).toBeVisible();

    await expect(page).toHaveScreenshot('login-screenshot.png');
  });
});
