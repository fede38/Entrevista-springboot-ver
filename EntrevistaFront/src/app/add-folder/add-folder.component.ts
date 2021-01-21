import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { IndexFolderComponent } from '../index-folder/index-folder.component';

@Component({
  selector: 'app-add-folder',
  template: `
    <form [formGroup]="checkoutForm" (ngSubmit)="onSubmit()">
      <input type="text" id="folder-name" formControlName="description">
      <button class="button" type="submit">Add</button>
    </form>
  `,
})
export class AddFolderComponent implements OnInit {
  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private comp: IndexFolderComponent,
  ) {}

  checkoutForm = this.formBuilder.group({
    description: ''
  })

  ngOnInit() {}

  onSubmit(): void {
      let json = {"description": this.checkoutForm["value"]["description"]}
      this.http.post('http://localhost:9999/todofolders', json)
        .subscribe((resp:any) => {
          this.checkoutForm.reset()
          this.comp.ngOnInit()
        })
  }
}
