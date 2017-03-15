package controllers

import play.api.mvc.{Action, Controller}
import services.main

class EmployeeController extends Controller{

  def getAll = Action{
    //implicit request =>
      println(main.listOfEmployees.head.name)
    Ok(views.html.index(getAllEmployee(main.listOfEmployees)))
  }

  def getAllEmployee(list : List[models.Employee]) : String = {
    list.toString()
  }
}
