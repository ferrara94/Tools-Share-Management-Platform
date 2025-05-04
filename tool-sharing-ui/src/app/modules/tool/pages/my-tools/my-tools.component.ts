import { Component, OnInit } from '@angular/core';
import { PageResponseToolResponse, ToolResponse } from '../../../../services/models';
import { ToolService } from '../../../../services/services';
import { Router, RouterModule } from '@angular/router';
import { ToolCardComponent } from "../../components/tool-card/tool-card.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-my-tools',
  imports: [RouterModule, CommonModule, ToolCardComponent],
  templateUrl: './my-tools.component.html',
  styleUrl: './my-tools.component.css'
})
export class MyToolsComponent implements OnInit {

  page = 0;
  size = 2;

  toolResponse: PageResponseToolResponse = {};
  errorMessage = "";
  

  constructor(private toolService: ToolService, private router: Router) {}
  ngOnInit(): void {
    this.findAllTools();
  }

  findAllTools() {
    this.toolService.findAllToolsByOwner({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (response) => {
        this.toolResponse = response;
        this.errorMessage = "";
        console.log('response', this.toolResponse)
      }     
    });
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

  archiveTool(tool: ToolResponse) {

  }

  editTool(tool: ToolResponse) {

  }

  shareTool(tool: ToolResponse) {

  }

}
