package io.digitalstate.stix.validator;

import io.digitalstate.stix.bundle.Bundle;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.datamarkings.MarkingDefinition;
import io.digitalstate.stix.json.StixParserValidationException;
import io.digitalstate.stix.json.StixParsers;
import io.digitalstate.stix.sdo.objects.*;
import io.digitalstate.stix.sro.objects.Relationship;
import io.digitalstate.stix.sro.objects.Sighting;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.io.IOException;
import java.util.Optional;

public class ValidatorVerticle extends AbstractVerticle {

    private JsonObject generateErrorResponse(Exception ex) {
        JsonObject object = new JsonObject();
        object.put("result", "invalid");
        object.put("message", ex.getMessage());

        return object;
    }

    private JsonObject generateValidationErrorResponse(StixParserValidationException ex) {
        JsonObject object = new JsonObject();
        object.put("result", "invalid");
        JsonArray validationErrors = new JsonArray();
        ex.getConstraintValidations().forEach(violation -> {
            JsonObject error = new JsonObject();
            error.put("type", ((BundleableObject) violation.getRootBean()).getType());
            error.put("id", ((BundleableObject) violation.getRootBean()).getId());
            error.put("path", violation.getPropertyPath().toString());
            error.put("value", violation.getInvalidValue());
            error.put("message", violation.getMessage());

            validationErrors.add(error);
        });

        object.put("errors", validationErrors);

        return object;
    }

    private JsonObject generateValidResponse() {
        JsonObject object = new JsonObject();
        object.put("result", "valid");

        return object;
    }

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer(new HttpServerOptions());
        Router router = Router.router(vertx);


        router.route(HttpMethod.POST, "/api/validation/sdo/attack-pattern")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();

            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();

            try {
                AttackPattern parseObject = (AttackPattern) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/campaign")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Campaign parsedObject = (Campaign) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/course-of-action")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                CourseOfAction parsedObject = (CourseOfAction) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/identity")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Identity parsedObject = (Identity) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/indicator")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Indicator parsedObject = (Indicator) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/intrusion-set")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Indicator parsedObject = (Indicator) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/malware")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Malware parsedObject = (Malware) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/observed-data")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                ObservedData parsedObject = (ObservedData) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/report")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Report parsedObject = (Report) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/threat-actor")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                ThreatActor parsedObject = (ThreatActor) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/tool")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Tool parsedObject = (Tool) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sdo/vulnerability")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Vulnerability parsedObject = (Vulnerability) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sro/relationship")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Relationship parsedObject = (Relationship) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/sro/sighting")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Sighting parsedObject = (Sighting) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/data-marking/marking-definition")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                MarkingDefinition parsedObject = (MarkingDefinition) StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/bundle")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                Bundle parsedBundle = (Bundle) StixParsers.parseBundle(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        router.route(HttpMethod.POST, "/api/validation/bundleable-object")
                .handler(BodyHandler.create())
                .handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            String body = routingContext.getBodyAsString();
            try {
                BundleableObject parsedObject = StixParsers.parseObject(body);
                response.setStatusCode(200);
                response.end(generateValidResponse().toBuffer());
            } catch (StixParserValidationException ex) {
                response.setStatusCode(422);
                response.end(generateValidationErrorResponse(ex).toBuffer());
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatusCode(422);
                response.end(generateErrorResponse(ex).toBuffer());
            } catch (Exception ex){
                response.setStatusCode(422);
                ex.printStackTrace();
                response.end(generateErrorResponse(ex).toBuffer());
            }
        });

        int port = Optional.ofNullable(config().getInteger("port"))
                .orElse(8080);

        server.requestHandler(router).listen(port);
    }
}