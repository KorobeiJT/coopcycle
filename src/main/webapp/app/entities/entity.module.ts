import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'roles',
        loadChildren: () => import('./roles/roles.module').then(m => m.DmRolesModule),
      },
      {
        path: 'compte',
        loadChildren: () => import('./compte/compte.module').then(m => m.DmCompteModule),
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.DmProduitModule),
      },
      {
        path: 'panier',
        loadChildren: () => import('./panier/panier.module').then(m => m.DmPanierModule),
      },
      {
        path: 'restaurant',
        loadChildren: () => import('./restaurant/restaurant.module').then(m => m.DmRestaurantModule),
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.DmCourseModule),
      },
      {
        path: 'systeme-paiement',
        loadChildren: () => import('./systeme-paiement/systeme-paiement.module').then(m => m.DmSystemePaiementModule),
      },
      {
        path: 'cooperative',
        loadChildren: () => import('./cooperative/cooperative.module').then(m => m.DmCooperativeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class DmEntityModule {}
