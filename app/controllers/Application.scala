package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}

object Application extends Controller {

  def index = Action {
    implicit request =>
      Ok(views.html.index())
  }


  def graph = Action {
    implicit request =>
      val json2 = """{
                    | "name": "DataModel",
                    | "children": [
                    |  {
                    |   "name": "Customer",
                    |   "children": [
                    |     {
                    |     "name": "Name",
                    |     "children": [
                    |      {"name": "Warren Buffet", "size": 3534},
                    |      {"name": "LLOYDS", "size": 5731},
                    |      {"name": "Steve Jobs", "size": 7840},
                    |      {"name": "DELL", "size": 5914},
                    |      {"name": "James Bond", "size": 3416}
                    |     ]
                    |     }
                    |   ]
                    |  },
                    |  {
                    |     "name": "Account",
                    |     "children": [
                    |      {"name": "100391", "size": 3938},
                    |      {"name": "100134", "size": 3812},
                    |      {"name": "10084", "size": 6714},
                    |      {"name": "100311", "size": 743}
                    |     ]
                    |  },
                    |
                    |    {
                    |     "name": "Currency",
                    |     "children": [
                    |      {"name": "AED", "size": 7074},
                    |      {"name": "AUD", "size": 7074},
                    |      {"name": "CAD", "size": 7074},
                    |      {"name": "CHF", "size": 7074},
                    |      {"name": "DKK", "size": 7074},
                    |      {"name": "EUR", "size": 7074},
                    |      {"name": "GBP", "size": 7074},
                    |      {"name": "HKD", "size": 7074},
                    |      {"name": "INR", "size": 7074},
                    |      {"name": "JPY", "size": 7074},
                    |      {"name": "KWD", "size": 7074},
                    |      {"name": "LBP", "size": 7074},
                    |      {"name": "LKR", "size": 7074},
                    |      {"name": "NPR", "size": 7074},
                    |      {"name": "NZD", "size": 7074},
                    |      {"name": "PHP", "size": 7074},
                    |      {"name": "PLN", "size": 7074},
                    |      {"name": "SAR", "size": 7074},
                    |      {"name": "SEK", "size": 7074},
                    |      {"name": "SGD", "size": 7074},
                    |      {"name": "THB", "size": 7074},
                    |      {"name": "TWD", "size": 7074},
                    |      {"name": "USD", "size": 7074},
                    |      {"name": "XAG", "size": 7074},
                    |      {"name": "XAU", "size": 7074},
                    |      {"name": "ZAR", "size": 7074}
                    |     ]
                    |  }
                    | ]
                    |}
                    | """.stripMargin
      Ok(json2)
  }



  def graphAccount = Action {
    implicit request =>
      val json = """{
                   | "name": "Accounts", "id" : 0,
                   | "children": [
                   |      {"name": "100391", "id" : 100391, "type": "ACC", "children": [{"name": "CHF","id" : 10, "type": "CCY", "size": 3534},{"name": "Warren Buffet", "id" : 930,"type": "CUST", "size": 3534} ]},
                   |      {"name": "100134", "id" : 100134, "type": "ACC", "children": [{"name": "USD","id" : 12, "type": "CCY", "size": 3534},{"name": "Warren Buffet", "id" : 30,"type": "CUST", "size": 3534} ]},
                   |      {"name": "10084", "id" : 10084, "type": "ACC", "children": [{"name": "GBP","id" : 11, "type": "CCY", "size": 3534},{"name": "Steve Jobs", "id" : 31,"type": "CUST", "size": 7840} ]},
                   |      {"name": "100311", "id" : 100311, "type": "ACC", "children": [{"name": "USD","id" : 12, "type": "CCY", "size": 3534},{"name": "DELL", "id" : 32,"type": "CUST", "size": 5914} ]},
                   |  { "name": "Customers", "id" : 2,
                   |   "children": [
                   |     {"name": "Name", "id" : 3,
                   |      "children": [
                   |       {"name": "Warren Buffet", "id" : 1130,"type": "CUST", "size": 3534},
                   |       {"name": "Steve Jobs", "id" : 1131,"type": "CUST", "size": 7840},
                   |       {"name": "DELL", "id" : 1132,"type": "CUST", "size": 5914}
                   |      ]
                   |     }
                   |   ]
                   |  },
                   |  { "name": "Currencies", "id" : 1,
                   |      "children": [
                   |       {"name": "CHF","id" : 3310, "type": "CCY", "size": 7074},
                   |       {"name": "GBP","id" : 3311, "type": "CCY", "size": 7074},
                   |       {"name": "USD","id" : 3312, "type": "CCY", "size": 7074}
                   |      ]
                   |  }
                   | ]
                   |}
                   | """.stripMargin
      Ok(json)
  }

def graphCustomer = Action {
    implicit request =>
      val json = """{
                   |    "name": "Customers", "id" : 2,
                   |    "children": [
                   |        { "name": "Name", "id" : 3,
                   |            "children": [
                   |                {"name": "Warren Buffet", "id" : 630,"type": "CUST", "children": [{"name": "100391", "id" : 10391,"type": "ACC", "size": 3938},{"name": "100134", "id": 10134, "type": "ACC", "size": 3812}]},
                   |                {"name": "Steve Jobs", "id" : 631,"type": "CUST", "children": [{"name": "10084", "id" : 1084,"type": "ACC", "size": 3938}]},
                   |                {"name": "DELL", "id" : 362,"type": "CUST", "children": [{"name": "100311", "id" : "10311","type": "ACC", "size": 6714}]}]
                   |        },
                   |        { "name": "Accounts", "id" : 0,
                   |            "children": [
                   |                {"name": "100391", "id" : 10033391,"type": "ACC", "children": [{"name": "CHF","id" : 4610, "type": "CCY", "size": 3534},{"name": "Warren Buffet", "id" : 6630,"type": "CUST", "size": 3534}]},
                   |                {"name": "100134", "id" : 10033134,"type": "ACC", "children": [{"name": "USD","id" : 4612, "type": "CCY", "size": 3534},{"name": "Warren Buffet", "id" : 6530,"type": "CUST", "size": 3534}]},
                   |                {"name": "10084", "id" : 1003384,"type": "ACC", "children": [{"name": "GBP","id" : 4511, "type": "CCY", "size": 3534},{"name": "Steve Jobs", "id" : 6631,"type": "CUST", "size": 7840}]},
                   |                {"name": "100311", "id" : 10036511,"type": "ACC", "children": [{"name": "USD","id" : 45143442, "type": "CCY", "size": 3534},{"name": "DELL", "id" : 6632, "type": "CUST", "size": 5914} ]}]
                   |        },
                   |        { "name": "Currencies", "id" : 1,
                   |            "children": [
                   |                {"name": "GBP","id" : 1211, "type": "CCY", "size": 7074},
                   |                {"name": "CHF","id" : 1210, "type": "CCY", "size": 7074},
                   |                {"name": "USD","id" : 1212, "type": "CCY", "size": 7074}
                   |            ]
                   |        }
                   |    ]
                   |} """.stripMargin
      Ok(json)
  }

  def graphCurrency = Action {
    implicit request =>
      val json = """{
                    "name": "Currencies", "id" : 1,
                   |    "children": [
                   |        {"name": "CHF", "type": "CCY", "id" : 10, "children": [{"name": "100391","id" : 100391, "type": "ACC", "size": 3938}]},
                   |        {"name": "GBP", "type": "CCY", "id" : 11,"children": [{"name": "10084", "id" : 10084,"type": "ACC", "size": 3938}]},
                   |        {"name": "USD", "type": "CCY", "id" : 12,"children": [{"name": "100311", "id" : 100311,"type": "ACC", "size": 6714}, {"name": "100134", "id" : 100314,"type": "ACC", "size": 3812} ]}
                   |
                   |    ]
                   |}""".stripMargin
      Ok(json)
  }


  def bullets = Action {
    implicit request =>
      val json = """[
                    |  {"title":"Revenue","subtitle":"US$, in thousands","ranges":[150,225,300],"measures":[220,270],"markers":[250]},
                    |  {"title":"Profit","subtitle":"%","ranges":[20,25,30],"measures":[21,23],"markers":[26]},
                    |  {"title":"Order Size","subtitle":"US$, average","ranges":[350,500,600],"measures":[100,320],"markers":[550]},
                    |  {"title":"New Customers","subtitle":"count","ranges":[1400,2000,2500],"measures":[1000,1650],"markers":[2100]},
                    |  {"title":"Satisfaction","subtitle":"out of 5","ranges":[3.5,4.25,5],"measures":[3.2,4.7],"markers":[4.4]}
                    |] """.stripMargin
      Ok(json)
  }

  def version = Action {
    val version = Play.current.configuration.getString("firmenich.version").get
    val name = Play.current.configuration.getString("firmenich.appName").get
    val json: JsValue = Json.arr(Json.obj("Software" -> name),Json.obj("version" -> version))
    Ok(json)
  }

}

