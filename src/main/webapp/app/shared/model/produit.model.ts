import { IPanier } from 'app/shared/model/panier.model';

export interface IProduit {
  id?: number;
  name?: string;
  price?: number;
  quantity?: number;
  restaurantId?: number;
  paniers?: IPanier[];
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public name?: string,
    public price?: number,
    public quantity?: number,
    public restaurantId?: number,
    public paniers?: IPanier[]
  ) {}
}
