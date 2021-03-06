import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICooperative, Cooperative } from 'app/shared/model/cooperative.model';
import { CooperativeService } from './cooperative.service';
import { CooperativeComponent } from './cooperative.component';
import { CooperativeDetailComponent } from './cooperative-detail.component';
import { CooperativeUpdateComponent } from './cooperative-update.component';

@Injectable({ providedIn: 'root' })
export class CooperativeResolve implements Resolve<ICooperative> {
  constructor(private service: CooperativeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICooperative> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cooperative: HttpResponse<Cooperative>) => {
          if (cooperative.body) {
            return of(cooperative.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cooperative());
  }
}

export const cooperativeRoute: Routes = [
  {
    path: '',
    component: CooperativeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dmApp.cooperative.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CooperativeDetailComponent,
    resolve: {
      cooperative: CooperativeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dmApp.cooperative.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CooperativeUpdateComponent,
    resolve: {
      cooperative: CooperativeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dmApp.cooperative.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CooperativeUpdateComponent,
    resolve: {
      cooperative: CooperativeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dmApp.cooperative.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
