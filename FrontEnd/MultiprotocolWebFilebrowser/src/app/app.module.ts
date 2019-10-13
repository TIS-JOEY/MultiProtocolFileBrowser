import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NasService } from './services/nas.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { FileManagerModule } from './components/file-manager/file-manager.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FileManagerModule,
    FlexLayoutModule,
    MatCardModule,
    MatButtonModule
  ],
  providers: [NasService],
  bootstrap: [AppComponent]
})
export class AppModule { }
