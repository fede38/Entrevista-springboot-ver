import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-index-folder',
  template: `
    <div>Folders</div>
    <div *ngIf="folders; else noFolders">
      <div *ngFor="let folder of folders;">
        &#8211; {{ folder['description'] }}
        <a [routerLink]="[folder.id]">View Items</a>
        <span (click)="deleteFolder(folder.id)">Remove</span>
      </div>
    </div>
    <ng-template #noFolders>There are no folders yet, create one!</ng-template>
    <app-add-folder></app-add-folder>
  `
})
export class IndexFolderComponent implements OnInit {
  folders = null;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.render()
  }

  render() {
    this.folders = []
    this.http.get('http://localhost:9999/todofolders')
      .subscribe((resp:any) => {
        if ("_embedded" in resp) {
          var toDoFolders = resp["_embedded"]["toDoFolders"]
          for(let i = 0; i < toDoFolders.length; i++) {
            this.folders.push(toDoFolders[i])
          }
        }
      })
  }

  deleteFolder(id) {
    this.http.delete('http://localhost:9999/todofolders/'+ id)
      .subscribe((resp:any) => {
        this.render()
      })
  }
}
