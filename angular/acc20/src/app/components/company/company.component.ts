import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { Company } from 'src/app/common/company';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  private company: Company = new Company();

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  doLoad() {
    this.companyService.getProduct(2).subscribe(
      data => {
        this.company = data;
        console.log(data);
      }
    );
  }



}
