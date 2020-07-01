import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/sample/api/v1/employees';
  constructor( private http : HttpClient) { }

  getEmployeeById( id :number) :Observable<any>{
    return this.http.get(this.baseUrl+"/"+id);
  }
  getEmployeesList() : Observable<any>{
    return this.http.get(this.baseUrl);
  }

  deleteEmployee(id :number) :Observable<any>{
    return this.http.delete(this.baseUrl+"/"+id);
  }

  createEmployee(employee : Object) :Observable<Object>{
    return this.http.post(this.baseUrl, employee);
  }

  updateEmployee(id: number, value: any): Observable<Object> {
    return this.http.put(this.baseUrl+"/"+id, value);
  }
}
