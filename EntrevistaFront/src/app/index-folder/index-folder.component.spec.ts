import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexFolderComponent } from './index-folder.component';

describe('IndexFolderComponent', () => {
  let component: IndexFolderComponent;
  let fixture: ComponentFixture<IndexFolderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndexFolderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexFolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
