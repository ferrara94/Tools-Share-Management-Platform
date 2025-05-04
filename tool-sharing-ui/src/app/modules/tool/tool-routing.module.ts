import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { ToolListComponent } from './pages/tool-list/tool-list.component';
import { BorrowedToolListComponent } from './pages/borrowed-tool-list/borrowed-tool-list.component';
import { ReturnedToolsComponent } from './pages/returned-tools/returned-tools.component';
import { MyToolsComponent } from './pages/my-tools/my-tools.component';
import { ManageToolComponent } from './pages/manage-tool/manage-tool.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: ToolListComponent
      },
      {
        path: 'my-tools',
        component: MyToolsComponent
      },
      {
        path: 'my-borrowed-tools',
        component: BorrowedToolListComponent
      },
      {
        path: 'my-returned-tools',
        component: ReturnedToolsComponent
      },
      {
        path: 'manage',
        component: ManageToolComponent
      },
      {
        path: 'manage/id',
        component: ManageToolComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ToolRoutingModule { }
