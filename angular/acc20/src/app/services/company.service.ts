import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Company } from '../common/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  getCompanies(): Observable<GetResponseCompanies> {

    // need to build URL based on product id
    //const productUrl = `${this.baseUrl}/${theCompanyId}`;

    return this.httpClient.get<GetResponseCompanies>(this.baseUrl+"/companies");
  }
}

export interface GetResponseCompanies{
  _embedded: {
    companies: Company[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
