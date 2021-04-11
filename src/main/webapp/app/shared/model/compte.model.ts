import { IPanier } from 'app/shared/model/panier.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { ISystemePaiement } from 'app/shared/model/systeme-paiement.model';

export interface ICompte {
  id?: number;
  name?: string;
  surname?: string;
  age?: number;
  accountID?: string;
  adress?: string;
  role?: string;
  courseId?: number;
  paniers?: IPanier[];
  rolesId?: number;
  cooperativeId?: number;
  restaurants?: IRestaurant[];
  systemePaiements?: ISystemePaiement[];
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public age?: number,
    public accountID?: string,
    public adress?: string,
    public role?: string,
    public courseId?: number,
    public paniers?: IPanier[],
    public rolesId?: number,
    public cooperativeId?: number,
    public restaurants?: IRestaurant[],
    public systemePaiements?: ISystemePaiement[]
  ) {}
}
