package tables

import components.DbProvider
import models.Project

trait ProjectTable extends EmployeeTable{
  this:DbProvider =>
  import driver.api._

  class ProjectTable(tag  :Tag) extends Table[Project](tag,"projects"){
    val pId = column[Int]("p_id", O.PrimaryKey)
    val empId = column[Int]("emp_id")
    val name = column[String]("name")
    val members = column[Int]("team_members")
    val lead = column[String]("lead")

    def employeeProjectFK = foreignKey("employee_project_fk",empId,employeeTableQuery)(_.empId)

    def * = (pId,empId,name,members,lead) <>(Project.tupled,Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]
}
