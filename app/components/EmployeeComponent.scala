package components

import com.google.inject.Inject
import models.Employee
import tables.EmployeeTable

class EmployeeComponent@Inject()(val dbProvider : MyDBProvider)  extends EmployeeTable{

  this:DbProvider =>
  import driver.api._

  def create = db.run(employeeTableQuery.schema.create)

  def insert(emp :Employee) = db.run{
    employeeTableQuery += emp
  }

  def delete(exp : Double) ={
    val query= employeeTableQuery.filter(x => x.experience < 4.00)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = employeeTableQuery.filter(_.empId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(employeeTableQuery.result)
  }

  def insertOrUpdate(emp : Employee) = {
    db.run(employeeTableQuery.insertOrUpdate(emp))
  }

  def drop = {
    db.run(employeeTableQuery.schema.drop)
    employeeTableQuery.schema.drop.statements.foreach(println)
  }

  def deleteAll = {
    db.run(employeeTableQuery.map(x => x).delete)
  }
}
