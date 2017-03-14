package controllers

import play.api.mvc.{Action, Controller}
import services.main

class EmployeeController extends Controller{

  def getAll = Action{
    implicit request =>
      println(main.listOfEmployees)
    Ok(views.html.index(main.listOfEmployees.toString()))
  }
}
