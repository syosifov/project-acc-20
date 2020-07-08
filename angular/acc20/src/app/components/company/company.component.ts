import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { Company } from 'src/app/common/company';
import { GetResponseCompanies } from "src/app/services/company.service";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  private getResponseCompanies: GetResponseCompanies;

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  doLoad() {
    this.companyService.getCompanies().subscribe(
      data => {
        this.getResponseCompanies = data;
        console.log(data);
      }
    );
  }



}
