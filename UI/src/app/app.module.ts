import { CheckListService } from './service/check-list.service';
import { FillSubChecklistComponent } from './component/fill-sub-checklist/fill-sub-checklist.component';
import { AuthguardGuard } from './service/authguard.guard';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';

import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDividerModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatStepperModule,
} from '@angular/material';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ToastrModule } from 'ngx-toastr';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material';
import { DialogZOneComponent } from './dialog/dialog-zone/dialog-zone.component';
import { MatMenuModule } from '@angular/material/menu';
import { HomeComponent } from './component/home/home.component';
import { AnalyticsComponent } from './component/analytics/analytics.component';
import { DisasterTrainigComponent } from './component/disaster-trainig/disaster-trainig.component';
import { TutorialsComponent } from './component/tutorials/tutorials.component';
import { SiteComponent } from './component/site/site.component';
import { RybComponent } from './component/ryb/ryb.component';
import { PopulationComponent } from './component/population/population.component';
import { ZonemarkComponent } from './component/zonemark/zonemark.component';
import { TutorialVideosComponent } from './component/tutorial-videos/tutorial-videos.component';
import { ChecklistComponent } from './component/checklist/checklist.component';
import { AddChecklistComponent } from './component/add-checklist/add-checklist.component';
import { AddChecklistTextareaComponent } from './component/add-checklist-textarea/add-checklist-textarea.component';
import { MatVideoModule } from 'mat-video';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationServiceService } from './service/registration-service.service';
import { DataService } from './service/data.service';
import { MapViewService } from './service/map-view.service';
import { DisasterTrainingService } from './service/disaster-training.service';
import { SelectLatLongComponent } from './dialog/select-lat-long/select-lat-long.component';
import {VolcanoZoneService} from './service/volcano-zone.service';
import { MessageFormComponent } from './component/message-form/message-form.component';
import { MessageItemComponent } from './component/message-item/message-item.component';
import { MessageListComponent } from './component/message-list/message-list.component';
import { RenderComponentComponent } from './component/render-component/render-component.component';
import {ChatbotServiceService} from './service/chatbot-service.service';
import { AddSubchecklistTextareaComponent } from './component/add-subchecklist-textarea/add-subchecklist-textarea.component';
import { AddChecklistTextarea1Component } from './component/add-checklist-textarea1/add-checklist-textarea1.component';
import { ChartModule } from 'angular-highcharts';

@NgModule({
  declarations: [
    AppComponent,
    MainNavComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    DialogZOneComponent,
    HomeComponent,
    AnalyticsComponent,
    DisasterTrainigComponent,
    TutorialsComponent,
    SiteComponent,
    RybComponent,
    PopulationComponent,
    ZonemarkComponent,
    TutorialVideosComponent,
    ChecklistComponent,
    AddChecklistComponent,
    AddChecklistTextareaComponent,
    SelectLatLongComponent,
    MessageFormComponent,
    MessageItemComponent,
    RenderComponentComponent,
    MessageListComponent,
    AddSubchecklistTextareaComponent,
    AddChecklistTextarea1Component,
    FillSubChecklistComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatDialogModule,
    MatMenuModule,
    MatRadioModule,
    MatVideoModule,
    MatSelectModule,
    HttpClientModule,
    MatCheckboxModule,
    MatTooltipModule,
    MatCardModule,
    ChartModule,
    ToastrModule.forRoot({
      timeOut: 2500,
      tapToDismiss: true,
    }),
  ],
  providers: [CheckListService,AuthguardGuard,RegistrationServiceService, DataService, DisasterTrainingService, MapViewService,VolcanoZoneService,ChatbotServiceService],
  bootstrap: [AppComponent],
  entryComponents: [DialogZOneComponent, SelectLatLongComponent, FillSubChecklistComponent]
})
export class AppModule { }
