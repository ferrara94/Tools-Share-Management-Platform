import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ToolRequest } from '../../../../services/models';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-manage-tool',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './manage-tool.component.html',
  styleUrl: './manage-tool.component.css'
})
export class ManageToolComponent {
  errorMsg: Array<string> = [];
  selectedPicture: string = '';
  placeholderPicture = 'assets/images/ImgPlaceholder.jpg';
  selectedToolPicture: any;

  toolRequest: ToolRequest = {
    name: '',
    description: '',
    condition: '',
    category: '',
    available: true
  };


  onFileSelected(file: any){
    this.selectedToolPicture = file.target.files[0];
    console.log('selected tool picture',this.selectedToolPicture);
    if(this.selectedToolPicture){
      const reader: FileReader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      };
      reader.readAsDataURL(this.selectedToolPicture);
    }
  }

  //minutes 10:08:00
}
