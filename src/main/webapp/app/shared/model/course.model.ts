import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface ICourse {
  id?: number;
  timeRequired?: number;
  panierId?: number;
  panierId?: number;
  compteId?: number;
  restaurants?: IRestaurant[];
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public timeRequired?: number,
    public panierId?: number,
    public panierId?: number,
    public compteId?: number,
    public restaurants?: IRestaurant[]
  ) {}
}
