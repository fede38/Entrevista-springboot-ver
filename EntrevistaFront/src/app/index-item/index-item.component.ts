import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-index-item',
  template: `
    <div>Folder > </div>
    <div *ngIf="items; else noItems">
      <div *ngFor="let item of items;">
        &#8211; {{ item['description'] }}
      </div>
    </div>
    <ng-template #noItems>There are no tasks in this folder, add some!</ng-template>
  `
})
export class IndexItemComponent implements OnInit {
  items = null;
  folderId = null;
  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe(params => this.folderId = (params["folderId"]));
    this.render()    
  }

  render() {

  }

}
