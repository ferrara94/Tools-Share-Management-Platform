import { Component, OnInit } from '@angular/core';
import { ToolService } from '../../../../services/services';
import { Router } from '@angular/router';
import { PageResponseToolResponse, ToolResponse } from '../../../../services/models';
import { CommonModule } from '@angular/common';
import { ToolCardComponent } from "../../components/tool-card/tool-card.component";

@Component({
  selector: 'app-tool-list',
  imports: [CommonModule, ToolCardComponent],
  templateUrl: './tool-list.component.html',
  styleUrl: './tool-list.component.css',
})
export class ToolListComponent implements OnInit {
  page = 0;
  size = 4;

  toolResponse: PageResponseToolResponse = {};
  errorMessage = "";
  message: string = '';
  alertLevel = 'success';
  

  constructor(private toolService: ToolService, private router: Router) {}
  ngOnInit(): void {
    this.findAllTools();
  }

  findAllTools() {
    this.toolService.findAllTools({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (response) => {
        this.toolResponse = response;
        this.errorMessage = "";
        console.log('response', this.toolResponse)
      },error: (err) => {
        if(err.status == 403){
          this.errorMessage = `No tools to be showed. 
          Try to log-in first if you haven't done yet`;
        }        
      }
    });
  }

  goToLogin(){
    this.errorMessage = '';
    this.router.navigate(['/login'])
  }

  goToFirstPage(){
    this.page = 0;
    this.findAllTools();
  }

  goToPreviousPage(){
    this.page--;
    this.findAllTools();
  }

  goToPage(page: number){
    this.page = page;
    this.findAllTools();
  }

  goToNextPage(){
    this.page++;
    this.findAllTools();
  }

  goToLastPage(){
    this.page = this.toolResponse.totalPages as number -1;
    this.findAllTools();
  }

  get isLastPage(): boolean {
    return this.page == this.toolResponse.totalPages as number -1;
  }

  borrowTool(tool: ToolResponse){
    this.message = '';
    this.toolService.borrowTool({
      'tool-id': tool.id as number
    }).subscribe({
      next: () => {
        this.alertLevel = 'success';
        this.message = 'Tool successfully added to your borrow-list'
      },
      error: (err) => {
        console.log("error borrowTool",err);
        this.alertLevel = 'error';
        this.message = err.error.error;
      }
    })
  }
}
