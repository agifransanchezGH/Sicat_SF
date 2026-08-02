import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form-shell',
  standalone: true,
  imports: [CommonModule],
  template: `
    <section class="form-shell">
      <h2 *ngIf="title">{{ title }}</h2>
      <ng-content></ng-content>
      <p class="feedback" *ngIf="feedback">{{ feedback }}</p>
    </section>
  `,
  styles: [
    `
      .form-shell {
        display: flex;
        flex-direction: column;
        gap: 1rem;
        padding: 1rem;
        border: 1px solid #d0d7de;
        border-radius: 8px;
        background: #fff;
      }
      .feedback {
        margin: 0;
        color: #0a7d3d;
        font-weight: 600;
      }
    `
  ]
})
export class FormShellComponent {
  @Input() title = '';
  @Input() feedback: string | null = null;
}
