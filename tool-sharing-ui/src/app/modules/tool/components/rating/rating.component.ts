import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-rating',
  imports: [CommonModule],
  templateUrl: './rating.component.html',
  styleUrl: './rating.component.css'
})
export class RatingComponent {

  @Input() rating: number = 0;
  maxRating = 5;

  get fullStarts(): number {
    return Math.floor(this.rating);
  }

  get hasHalfStar(): boolean { //should change the type in the DB since now it is int
    return this.rating % 1 !== 0;
  }
  
  get emptyStars(): number {
    return this.maxRating - Math.ceil(this.rating);
  }

}
