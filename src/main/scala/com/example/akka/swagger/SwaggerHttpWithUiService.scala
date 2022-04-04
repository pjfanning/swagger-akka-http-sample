package com.example.akka.swagger

import akka.http.javadsl.server.Rejections
import akka.http.scaladsl.model.StatusCodes.PermanentRedirect
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import akka.util.ByteString
import com.example.akka.swagger.SwaggerHttpWithUiService.swaggerUi
import com.github.swagger.akka.SwaggerHttpService
import org.webjars.{MultipleMatchesException, NotFoundException, WebJarAssetLocator}

import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
 * SwaggerHttpService along with swagger-ui.
 *
 * Swagger-UI is loaded as webjar, via [[org.webjars.WebJarAssetLocator]] and served via akka-http.
 */
trait SwaggerHttpWithUiService extends SwaggerHttpService {
  val webJarAssetLocator = new WebJarAssetLocator()

  /**
   * Serve files from webjar.
   * Inspired heavily by
   * [[https://github.com/ThoughtWorksInc/akka-http-webjars/blob/master/src/main/scala/com/thoughtworks/akka/http/WebJarsSupport.scala akka-http-webjars]] but with some
   * changes
   *
   * @param webJarName
   * @return
   */
  def webJars(webJarName: String, indexContent: Future[HttpEntity.Strict]): Route = {
      concat(
        pathEnd {
          redirect(s"/${apiDocsPath}/", PermanentRedirect) // A missing / at the end would break all relative paths in the index.html, so redirect to /
        },
        pathSingleSlash {
          complete(indexContent)
        },
        extractUnmatchedPath { path =>
          Try(webJarAssetLocator.getFullPath(webJarName, path.toString)) match {
            case Success(fullPath) =>
              getFromResource(fullPath)
            case Failure(_: NotFoundException) =>
              reject
            case Failure(e: MultipleMatchesException) =>
              reject(Rejections.validationRejection(e.getMessage))
            case Failure(e) => failWith(e)
          }
        })
  }

  /**
   * Content of the index.html for the swagger site, based on the original one included in the
   * [[https://github.com/webjars/swagger-ui swagger-ui webjar]].
   *
   * The default index [[https://github.com/swagger-api/swagger-ui/blob/master/dist/index.html index.html]] is an example for the default petstore example of Swagger,
   The example html is turned into the page for the configured service, using poor-man's templating e.g. regexps
   * @return
   */
  def swaggerIndex(classLoader: ClassLoader = _defaultClassLoader): Future[HttpEntity.Strict] = {
    Future.fromTry(Try {
      val fullPath = webJarAssetLocator.getFullPath(swaggerUi, "index.html")
      val url = classLoader.getResource(fullPath)
      /**
       * Since the index.html from Swagger is relatively simple, we can get away with regular expressions.
       * I wouldn't use regexp for any generic html content.
       */
      val content = Source.fromURL(url).mkString
        .replaceFirst("https://petstore.swagger.io/v2/swagger.json", s"/${apiDocsPath}/swagger.json") // Update url to the swagger file
      HttpEntity.Strict(ContentTypes.`text/html(UTF-8)`, ByteString(content))
    })
  }

  val swaggerUiRoute = {
    pathPrefix(apiDocsPath) {
       webJars(swaggerUi, swaggerIndex())
    }
  }

  override val routes = super.routes ~ swaggerUiRoute
}

object SwaggerHttpWithUiService {
  val swaggerUi = "swagger-ui" // Name of the webjar
}


