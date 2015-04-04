package controllers

import models.{QueryExpression}
import parsers.{Expression, QueryParser}

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(expressionForm))
  }

  val expressionForm : Form[QueryExpression] = Form(
        mapping ("expression" -> text)(QueryExpression.apply)(QueryExpression.unapply)
  )

  val expressionWrites = new Writes[QueryExpression] {
    def writes(exp : QueryExpression) = Json.obj(
      "expression" -> exp.expression
    )
  }

  def parseExpr = Action { implicit request =>
    val expression: QueryExpression = expressionForm.bindFromRequest.get
    val parsed: Option[Expression] = new QueryParser().parse(expression.expression)

    if(parsed.isEmpty)
      Redirect(routes.Application.index)
    else
      Ok(parsed.get.toNode)
  }

}