package models

case class Employee(empId : Int,name : String,experience : Double)

case class Dependent(depId : Int,name : String,dependentOn : Int,relation : String,age :Option[Int] )

case class Project(pId : Int,empId : Int,name : String,members : Int,lead :String)
