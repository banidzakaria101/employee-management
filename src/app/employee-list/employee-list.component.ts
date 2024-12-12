import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee.model';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit{
  employees: Employee[] = [];
  employeeDialog: boolean = false;
  currentEmployee: Employee = {
    firstName: '',
    lastName: '',
    email: '',
  };
  isEditing: boolean = false;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(){
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error('Error loading employees', err)
    });
  }

  openNewEmployeeDialog() {
    this.currentEmployee = {
      firstName: '',
      lastName: '',
      email: '',
    };
    this.isEditing = false;
    this.employeeDialog = true;
  }

  editEmployee() {
    if(this.isEditing && this.currentEmployee.id) {
      this.employeeService.updateEmployee(this.currentEmployee.id, this.currentEmployee).subscribe({
        next: () => {
          this.loadEmployees();
          this.employeeDialog = false;
        },
        error: (err) => console.error('Error updating employee', err)
      });
    }else {
      this.employeeService.addEmployee(this.currentEmployee).subscribe({
        next: () => {
          this.loadEmployees();
          this.employeeDialog = false;
        },
        error: (err) => console.error('Error adding employee', err)
      });
    }
  }

  isFomValid(): boolean {
    return !!(
      this.currentEmployee.firstName &&
      this.currentEmployee.lastName &&
      this.currentEmployee.email
    );
  }
}
