import { IProduit } from 'app/shared/model/produit.model';
import { ICompte } from 'app/shared/model/compte.model';
import { ICourse } from 'app/shared/model/course.model';

export interface IRestaurant {
  id?: number;
  name?: string;
  adress?: string;
  products?: string;
  produits?: IProduit[];
  comptes?: ICompte[];
  courses?: ICourse[];
  cooperativeId?: number;
}

export class Restaurant implements IRestaurant {
  constructor(
    public id?: number,
    public name?: string,
    public adress?: string,
    public products?: string,
    public produits?: IProduit[],
    public comptes?: ICompte[],
    public courses?: ICourse[],
    public cooperativeId?: number
  ) {}
}
