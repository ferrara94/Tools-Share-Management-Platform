import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ToolResponse } from '../../../../services/models';
import { CommonModule } from '@angular/common';
import { RatingComponent } from "../rating/rating.component";

@Component({
  selector: 'app-tool-card',
  imports: [CommonModule, RatingComponent],
  templateUrl: './tool-card.component.html',
  styleUrl: './tool-card.component.css'
})
export class ToolCardComponent {

  _tool: ToolResponse = {};
  _toolPicture: string | undefined;
  _manage: boolean = false;

  @Input()
  set manage(value: boolean) {
    this._manage = value;
  }

  get manage(): boolean {
    return this._manage;
  }

  @Input()
  set tool(value: ToolResponse) {
    this._tool = value;
  }

  get tool(): ToolResponse {
    return this._tool;
  }

  get toolPicture(): string | undefined {
    const imgName = this._tool?.name;
    if(imgName) {
      return 'assets/images/' + imgName.replace(' ','') + '.jpg';
    }
    return '';
  }

  @Output() private share: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();
  @Output() private addToWaitingList: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();
  @Output() private borrow: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();
  @Output() private archive: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();
  @Output() private details: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();
  @Output() private edit: EventEmitter<ToolResponse> = new EventEmitter<ToolResponse>();


  onShowDetails(){
    this.details.emit(this._tool);
  }

  onBorrow(){
    this.borrow.emit(this._tool)
  }

  onAddToWaitingList(){
    this.addToWaitingList.emit(this._tool)
  }

  onEdit(){
    this.edit.emit(this._tool)
  }

  onShare(){
    this.share.emit(this._tool)
  }

  onArchive(){
    this.archive.emit(this._tool)
  }

}
