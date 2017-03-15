package components

//import com.google.inject.Inject
import com.google.inject.{Singleton, Inject}
import slick.jdbc.{PostgresProfile, JdbcProfile, MySQLProfile}

class MyDBProvider @Inject()(configuration : play.api.Configuration) extends DbProvider{


  val driver = if(configuration.underlying.getString("DB_TYPE") == "MySql"){
                  MySQLProfile  }
                else{
                  PostgresProfile  }

  import driver.api._

  val db = if(configuration.underlying.getString("DB_TYPE") == "MySql"){
    Database.forConfig("mySqlDB")
  }
  else{
    Database.forConfig("myPostgresDB")
  }


}
