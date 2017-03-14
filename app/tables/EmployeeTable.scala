package tables

import components.DbProvider
import models.Employee

trait EmployeeTable{
  this:DbProvider =>
  import driver.api._

  class EmployeeTable(tag  :Tag) extends Table[Employee](tag,"employee"){
    val empId = column[Int]("emp_id",O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (empId,name,experience) <>(Employee.tupled,Employee.unapply)
  }

  val queryObj = TableQuery[EmployeeTable]
}
