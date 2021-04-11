import { IProduit } from 'app/shared/model/produit.model';

export interface IPanier {
  id?: number;
  nbElements?: number;
  price?: number;
  courseId?: number;
  produits?: IProduit[];
  courseId?: number;
  compteId?: number;
}

export class Panier implements IPanier {
  constructor(
    public id?: number,
    public nbElements?: number,
    public price?: number,
    public courseId?: number,
    public produits?: IProduit[],
    public courseId?: number,
    public compteId?: number
  ) {}
}
