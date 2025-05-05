import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ToolRequest } from '../../../../services/models';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ToolService } from '../../../../services/services';

@Component({
  selector: 'app-manage-tool',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './manage-tool.component.html',
  styleUrl: './manage-tool.component.css',
})
export class ManageToolComponent implements OnInit {
  errorMsg: Array<string> = [];
  selectedPicture: string = '';
  placeholderPicture = 'assets/images/ImgPlaceholder.jpg';
  selectedToolPicture: any;

  toolRequest: ToolRequest = {
    name: '',
    description: '',
    condition: '',
    category: '',
    available: true,
  };

  constructor(
    private toolService: ToolService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const toolId = this.activatedRoute.snapshot.params['id'];
    console.log("toolId",toolId)
    if (toolId) {
      this.toolService
        .findToolById({
          'tool-id': toolId,
        })
        .subscribe({
          next: (tool) => {
            this.toolRequest = {
              name: tool.name as string,
              description: tool.description as string,
              condition: tool.condition as string,
              category: tool.category as string,
              available: true,
            };
            this.selectedPicture = 'assets/images/' + tool.name?.replace(" ", "") + '.jpg';
          },
        });
    }
  }

  onFileSelected(file: any) {
    this.selectedToolPicture = file.target.files[0];
    console.log('selected tool picture', this.selectedToolPicture);
    if (this.selectedToolPicture) {
      const reader: FileReader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      };
      reader.readAsDataURL(this.selectedToolPicture);
    }
  }

  saveTool() {
    this.toolService
      .saveTool({
        body: this.toolRequest,
      })
      .subscribe({
        next: (toolId) => {
          console.log('tool with toolId ' + toolId + 'added');
          console.log('picture', this.selectedPicture);
          console.log('picture2', this.selectedToolPicture);
          this.toolService
            .uploadToolCoverPicture({
              'tool-id': toolId,
              body: {
                file: this.selectedToolPicture,
              },
            })
            .subscribe({
              next: () => {
                this.router.navigate(['/tools/my-tools']);
              },
            });
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        },
      });
  }
}
