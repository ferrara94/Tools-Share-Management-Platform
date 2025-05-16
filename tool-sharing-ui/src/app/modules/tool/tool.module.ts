import { APP_INITIALIZER, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToolRoutingModule } from './tool-routing.module';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { httpTokenInterceptor } from '../../services/interceptor/http-token.interceptor';
import { TokenService } from '../../services/token/token.service';
import { ToolService } from '../../services/services/tool.service';

@NgModule({
  declarations: [],
  imports: [CommonModule, ToolRoutingModule],
  providers: [
    ToolService,
    TokenService,
    provideHttpClient(withInterceptors([httpTokenInterceptor])),
  ],
})
export class ToolModule {}