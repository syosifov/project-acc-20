import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Company } from '../common/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseUrl = 'http://localhost:8080/jpa/companies';

  constructor(private httpClient: HttpClient) { }

  getProduct(theCompanyId: number): Observable<Company> {

    // need to build URL based on product id
    const productUrl = `${this.baseUrl}/${theCompanyId}`;

    return this.httpClient.get<Company>(productUrl);
  }
}
