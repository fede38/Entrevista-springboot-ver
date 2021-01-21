import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddFolderComponent } from './add-folder/add-folder.component';
import { IndexFolderComponent } from './index-folder/index-folder.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { IndexItemComponent } from './index-item/index-item.component';

@NgModule({
  declarations: [
    AppComponent,
    AddFolderComponent,
    IndexFolderComponent,
    IndexItemComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: '', component: IndexFolderComponent},
      {path: ':folderId', component: IndexItemComponent},
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
