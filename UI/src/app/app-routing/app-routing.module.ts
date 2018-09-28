import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { LoginComponent } from '../login/login.component';
import { HomeComponent } from '../component/home/home.component';
import { AnalyticsComponent } from '../component/analytics/analytics.component';
import { TutorialsComponent } from '../component/tutorials/tutorials.component';
import { SiteComponent } from '../component/site/site.component';
import { RybComponent } from '../component/ryb/ryb.component';
import { PopulationComponent } from '../component/population/population.component';
import { DisasterTrainigComponent } from '../component/disaster-trainig/disaster-trainig.component';
import { ZonemarkComponent } from '../component/zonemark/zonemark.component';
import { TutorialVideosComponent } from './../component/tutorial-videos/tutorial-videos.component';
import { ChecklistComponent } from './../component/checklist/checklist.component';
import { AddChecklistComponent } from './../component/add-checklist/add-checklist.component';
import { RegisterComponent } from '../register/register.component';
import { AuthguardGuard } from './../service/authguard.guard';

const routes: Routes = [
    {
        path: 'dashboard', component: DashboardComponent, canActivate: [AuthguardGuard], children: [
            { path: 'home', component: HomeComponent , canActivate: [AuthguardGuard]},
            { path: 'analytics', component: AnalyticsComponent,  canActivate: [AuthguardGuard]},
            { path: 'disaster', component: DisasterTrainigComponent,  canActivate: [AuthguardGuard]},
            { path: 'site', component: SiteComponent, canActivate: [AuthguardGuard] },
            { path: 'ryb', component: RybComponent, canActivate: [AuthguardGuard] },
            { path: 'population', component: PopulationComponent,canActivate: [AuthguardGuard] },
            { path: 'zonemark', component: ZonemarkComponent, canActivate: [AuthguardGuard] },
            { path: 'tutorialVideos', component: TutorialVideosComponent, canActivate: [AuthguardGuard] },
            { path: 'checklist', component: ChecklistComponent, canActivate: [AuthguardGuard] },
            { path: 'addChecklist', component: AddChecklistComponent, canActivate: [AuthguardGuard] },
        ]
    },
    { path: 'login', component: LoginComponent },
    { path: 'registration', component: RegisterComponent },
    { path: '**', redirectTo: '/login' },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})
export class AppRoutingModule { }