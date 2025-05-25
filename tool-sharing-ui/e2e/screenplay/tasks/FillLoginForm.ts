
import { Task, Actor } from '@testla/screenplay';
import { SetEmail } from '../actions/SetEmail';
import { Navigate } from '@testla/screenplay-playwright/web';
import { SetPassword } from '../actions/SetPassword';
import { ClickOnSignIn } from '../actions/ClickOnSignIn';

const LOGIN_URL = 'http://localhost:4200';

export class Login extends Task{
    private constructor(
        private email: string,
        private password: string
    ){
        super();
    }

    public static withCredentials(email: string, password: string): Login {
        return new Login(email, password);
    }

    public async performAs(actor: Actor): Promise<void> {
        await actor.attemptsTo(
            Navigate.to(LOGIN_URL),
            SetEmail.to(this.email),
            SetPassword.to(this.password),
            ClickOnSignIn.now()
        );
    }

}