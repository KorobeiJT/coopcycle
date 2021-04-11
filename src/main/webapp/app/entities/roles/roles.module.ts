import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DmSharedModule } from 'app/shared/shared.module';
import { RolesComponent } from './roles.component';
import { RolesDetailComponent } from './roles-detail.component';
import { RolesUpdateComponent } from './roles-update.component';
import { RolesDeleteDialogComponent } from './roles-delete-dialog.component';
import { rolesRoute } from './roles.route';

@NgModule({
  imports: [DmSharedModule, RouterModule.forChild(rolesRoute)],
  declarations: [RolesComponent, RolesDetailComponent, RolesUpdateComponent, RolesDeleteDialogComponent],
  entryComponents: [RolesDeleteDialogComponent],
})
export class DmRolesModule {}
