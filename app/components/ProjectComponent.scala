package components

import models.Project
import tables.ProjectTable


trait ProjectComponent extends ProjectTable {
  this:DbProvider =>
  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(prj :Project) = db.run(
    projectTableQuery += prj
  )

  def delete(exp : Double) ={
    val query= projectTableQuery.filter(x => x.name startsWith("n"))
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = projectTableQuery.filter(_.pId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(projectTableQuery.result)
  }

  def insertOrUpdate(prj : Project) = {
    db.run(projectTableQuery.insertOrUpdate(prj))
  }
}

object ProjectComponent extends ProjectComponent with MySqlDBProvider{

}