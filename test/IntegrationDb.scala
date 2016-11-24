import org.scalatest.{BeforeAndAfterAll, Suite}
import java.util.Calendar
import java.util.concurrent.TimeUnit
import play.api.Mode
import play.api.db.DBApi
import play.api.inject.guice.GuiceApplicationBuilder

trait IntegrationDb extends BeforeAndAfterAll { this: Suite =>
  lazy val appBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector = appBuilder.injector()
  lazy val databaseApi = injector.instanceOf[DBApi]
  lazy val db = databaseApi.database("default")
  lazy val connection = db.getConnection()
    def cleanup() = {
    connection.close()
    db.shutdown()
    databaseApi.shutdown()
  }

  override def afterAll() = {
    cleanup()
  }

  def nowAsTimestamp(daysDiff: Int = 0) = {
    val utilDate = new java.util.Date()
    val now = Calendar.getInstance()
    now.setTime(utilDate)
    now.add(Calendar.DATE, daysDiff)
    val nowDiff = new java.sql.Timestamp(now.getTimeInMillis())
    nowDiff
  }
}

