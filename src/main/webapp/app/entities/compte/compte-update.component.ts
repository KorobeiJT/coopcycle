import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICompte, Compte } from 'app/shared/model/compte.model';
import { CompteService } from './compte.service';
import { ICourse } from 'app/shared/model/course.model';
import { CourseService } from 'app/entities/course/course.service';
import { IRoles } from 'app/shared/model/roles.model';
import { RolesService } from 'app/entities/roles/roles.service';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { CooperativeService } from 'app/entities/cooperative/cooperative.service';

type SelectableEntity = ICourse | IRoles | ICooperative;

@Component({
  selector: 'jhi-compte-update',
  templateUrl: './compte-update.component.html',
})
export class CompteUpdateComponent implements OnInit {
  isSaving = false;
  courses: ICourse[] = [];
  roles: IRoles[] = [];
  cooperatives: ICooperative[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    surname: [null, [Validators.required]],
    age: [null, [Validators.min(0), Validators.max(120)]],
    accountID: [null, [Validators.required, Validators.maxLength(16)]],
    adress: [null, [Validators.required]],
    role: [null, [Validators.required]],
    courseId: [],
    rolesId: [],
    cooperativeId: [],
  });

  constructor(
    protected compteService: CompteService,
    protected courseService: CourseService,
    protected rolesService: RolesService,
    protected cooperativeService: CooperativeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compte }) => {
      this.updateForm(compte);

      this.courseService
        .query({ filter: 'compte-is-null' })
        .pipe(
          map((res: HttpResponse<ICourse[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICourse[]) => {
          if (!compte.courseId) {
            this.courses = resBody;
          } else {
            this.courseService
              .find(compte.courseId)
              .pipe(
                map((subRes: HttpResponse<ICourse>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICourse[]) => (this.courses = concatRes));
          }
        });

      this.rolesService.query().subscribe((res: HttpResponse<IRoles[]>) => (this.roles = res.body || []));

      this.cooperativeService.query().subscribe((res: HttpResponse<ICooperative[]>) => (this.cooperatives = res.body || []));
    });
  }

  updateForm(compte: ICompte): void {
    this.editForm.patchValue({
      id: compte.id,
      name: compte.name,
      surname: compte.surname,
      age: compte.age,
      accountID: compte.accountID,
      adress: compte.adress,
      role: compte.role,
      courseId: compte.courseId,
      rolesId: compte.rolesId,
      cooperativeId: compte.cooperativeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compte = this.createFromForm();
    if (compte.id !== undefined) {
      this.subscribeToSaveResponse(this.compteService.update(compte));
    } else {
      this.subscribeToSaveResponse(this.compteService.create(compte));
    }
  }

  private createFromForm(): ICompte {
    return {
      ...new Compte(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      surname: this.editForm.get(['surname'])!.value,
      age: this.editForm.get(['age'])!.value,
      accountID: this.editForm.get(['accountID'])!.value,
      adress: this.editForm.get(['adress'])!.value,
      role: this.editForm.get(['role'])!.value,
      courseId: this.editForm.get(['courseId'])!.value,
      rolesId: this.editForm.get(['rolesId'])!.value,
      cooperativeId: this.editForm.get(['cooperativeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompte>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
