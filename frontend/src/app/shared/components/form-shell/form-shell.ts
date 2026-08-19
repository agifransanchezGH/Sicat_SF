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
      :host {
        display: block;
        width: 100%;
      }

      .form-shell {
        display: flex;
        flex-direction: column;
        gap: 1.25rem;
        width: min(100%, 920px);
        margin: 2rem auto;
        padding: 2rem 1.5rem;
        border: 1px solid #dfe3e8;
        border-radius: 16px;
        background: #ffffff;
        box-shadow: 0 8px 24px rgba(15, 23, 42, 0.06);
      }

      h2 {
        margin: 0;
        font-size: clamp(1.8rem, 2vw, 2.3rem);
        font-weight: 700;
        color: #1f2937;
        text-align: center;
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
