package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future

@Singleton
class HealthController @Inject() extends Controller {
  def health = Action.async { request =>
    Future.successful(Ok(Json.toJson(Map("status" -> "OK"))))
  }
}
