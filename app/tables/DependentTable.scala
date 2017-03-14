package tables

import components.DbProvider
import models.Dependent

trait DependentTable extends EmployeeTable{

  this:DbProvider =>
  import driver.api._

  class DependentTable(tag  :Tag) extends Table[Dependent](tag,"employee_dependent"){
    val depId = column[Int]("dep_id",O.PrimaryKey)
    val name = column[String]("name")
    val dependentOn = column[Int]("dependent_on")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")

    def employeeDepdndentFK = foreignKey("employee_dependent_fk",dependentOn,queryObj)(_.empId)

    def * = (depId,name,dependentOn,relation,age) <>(Dependent.tupled,Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}
