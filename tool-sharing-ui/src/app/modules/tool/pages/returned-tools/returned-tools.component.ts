import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {
  BorrowedToolResponse,
  PageResponseBorrowedToolResponse,
} from '../../../../services/models';
import { ToolService } from '../../../../services/services';

@Component({
  selector: 'app-returned-tools',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './returned-tools.component.html',
  styleUrl: './returned-tools.component.css',
})
export class ReturnedToolsComponent implements OnInit {
  //the object is of this type since it is equal, even if it serves a different purpose
  returnedTools: PageResponseBorrowedToolResponse = {};

  page = 0;
  size = 4;
  message = '';
  alertLevel = 'success';

  ngOnInit(): void {
      this.findAllReturnedBorrowedTools();
  }

  constructor(private toolService: ToolService) {}

  findAllReturnedBorrowedTools() {
    this.toolService
      .findAllReturnedTools({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (resp) => {
          this.returnedTools = resp;
        },
      });
  }

  approveToolReturn(tool: BorrowedToolResponse) {
    if (!tool.returned) {
      this.alertLevel = 'error';
      this.message = 'Tool is not returned yet';
      return;
    }

    this.toolService
      .findToolByName({ toolName: tool.name as string })
      .subscribe({
        next: (resp) => {
          this.toolService
            .approveReturnBorrowTool({ 'tool-id': resp.id as number })
            .subscribe({
              next: (toolReturnedId) => {
                this.alertLevel = 'success';
                this.message =
                  'Tool return approved with ID: ' + toolReturnedId;

                this.findAllReturnedBorrowedTools();
              },
              error: (err) => {
                this.alertLevel = 'error';
                this.message = err.error?.error ?? 'Approval failed.';
              },
            });
        },
      });

  }
}
