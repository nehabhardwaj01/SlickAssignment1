package components

import models.Dependent
import tables.DependentTable
import scala.concurrent.ExecutionContext.Implicits.global

trait DependentComponent extends DependentTable{
  this:DbProvider =>
  import driver.api._

  def create = db.run(dependentTableQuery.schema.create)

  def insert(dependent :Dependent) = db.run{
    dependentTableQuery += dependent
  }

  def deleteByAge(age : Int) ={
    val query= dependentTableQuery.filter(x => x.age < 16)
    val action = query.delete
    db.run(action)
  }

  def deleteById(id : Int) ={
    val query= dependentTableQuery.filter(x => x.depId === id)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = dependentTableQuery.filter(_.depId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(dependentTableQuery.to[List].result)
  }

  def insertOrUpdate(dep : Dependent) = {
    db.run(dependentTableQuery.insertOrUpdate(dep))
  }

}

object DependentComponent extends DependentComponent with MySqlDBProvider{

}
