import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  BorrowedToolResponse,
  FeedbackRequest,
  PageResponseBorrowedToolResponse,
} from '../../../../services/models';
import { FeedbackService, ToolService } from '../../../../services/services';
import { FormsModule } from '@angular/forms';
import { RatingComponent } from '../../components/rating/rating.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-borrowed-tool-list',
  imports: [CommonModule, FormsModule, RatingComponent, RouterModule],
  templateUrl: './borrowed-tool-list.component.html',
  styleUrl: './borrowed-tool-list.component.css',
})
export class BorrowedToolListComponent implements OnInit {
  feedbackRequest: FeedbackRequest = {
    comment: '',
    toolId: 0,
  };
  page = 0;
  size = 4;
  selectedTool: BorrowedToolResponse | undefined = undefined;

  constructor(
    private toolService: ToolService,
    private feedbackService: FeedbackService
  ) {}

  ngOnInit(): void {
    this.findAllBorrowedTools();
  }
  borrowedTools: PageResponseBorrowedToolResponse = {};

  returnBorrowedTool(tool: BorrowedToolResponse) {
    this.selectedTool = tool;
    this.feedbackRequest.toolId = tool.id as number;
  }

  findAllBorrowedTools() {
    this.toolService
      .findAllBorrowedTools({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (resp) => {
          console.log('borrowed', resp);
          this.borrowedTools = resp;
        },
      });
  }

  returnTool(withFeedback: boolean) {
    this.toolService
      .findToolByName({
        toolName: this.selectedTool?.name as string,
      })
      .subscribe({
        next: (tool) => {
          this.toolService
            .returnBorrowTool({
              'tool-id': tool.id as number,
            })
            .subscribe({
              next: () => {
                if (withFeedback) {
                  this.giveFeedback();
                }
                this.selectedTool = undefined;
                this.findAllBorrowedTools();
              },
            });
        },
        error: (err) => console.error('Tool not found', err),
      });
  }

  giveFeedback() {
    this.feedbackService
      .saveFeedback({
        body: this.feedbackRequest,
      })
      .subscribe({
        next: () => {},
      });
  }
}
